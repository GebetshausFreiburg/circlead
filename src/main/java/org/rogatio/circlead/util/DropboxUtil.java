package org.rogatio.circlead.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rogatio.circlead.SyncCirclead;

import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.util.IOUtil.ProgressListener;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.DbxTeamClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.team.TeamFolderListResult;
import com.dropbox.core.v2.team.TeamFolderMetadata;
import com.dropbox.core.v2.team.TeamGetInfoResult;
import com.dropbox.core.v2.team.TeamMemberInfo;
import com.dropbox.core.v2.team.TeamMemberProfile;
import com.dropbox.core.v2.team.TokenGetAuthenticatedAdminResult;
import com.dropbox.core.v2.users.FullAccount;

public class DropboxUtil {

	final static Logger LOGGER = LogManager.getLogger(DropboxUtil.class);

	/**
	 * @see https://blogs.dropbox.com/developers/2014/05/generate-an-access-token-for-your-own-account/
	 * @param authFile
	 * @return
	 */
	public static DbxClientV2 getClient(String authFile) {

		/**
		 * Needs to load an auth-file 'name.credentials' with the json structure {
		 * "key": "", "secret": "", "access_token" : "" }
		 */

		DbxAuthInfo authInfo;
		try {
			authInfo = DbxAuthInfo.Reader.readFromFile(authFile);
		} catch (JsonReader.FileLoadException ex) {
			System.err.println("Error loading <auth-file>: " + ex.getMessage());
			return null;
		}

		DbxRequestConfig requestConfig = new DbxRequestConfig("circlead-cloud");
		DbxClientV2 dbxClient = new DbxClientV2(requestConfig, authInfo.getAccessToken(), authInfo.getHost());

		// Make the /account/info API call.
		FullAccount dbxAccountInfo;
		try {
			dbxAccountInfo = dbxClient.users().getCurrentAccount();
		} catch (DbxException ex) {
			LOGGER.error("Error making API call: " + ex.getMessage());
			return null;
		}

		LOGGER.info("Load Dropbox-Client '" + dbxAccountInfo.getTeam().getName() + "' for user '"
				+ dbxAccountInfo.getName().getDisplayName() + "'");

//		dbxClient.files().upload(path)

//	        System.out.println(dbxClient.files().listRevisions("/Wiki/data/database/xwiki_db.script").toStringMultiline());

//	        System.out.println(dbxClient.files().getMetadata("/Wiki/data/database/xwiki_db.script").toStringMultiline());

		return dbxClient;

	}

	public static DbxTeamClientV2 getTeamClient(String authFile) {

		/**
		 * Needs to load an auth-file 'name.credentials' with the json structure {
		 * "key": "", "secret": "", "access_token" : "" }
		 */

		DbxAuthInfo authInfo;
		try {
			authInfo = DbxAuthInfo.Reader.readFromFile(authFile);
		} catch (JsonReader.FileLoadException ex) {
			System.err.println("Error loading <auth-file>: " + ex.getMessage());
			return null;
		}

		DbxRequestConfig requestConfig = new DbxRequestConfig("circlead-cloud");
		DbxTeamClientV2 dbxClient = new DbxTeamClientV2(requestConfig, authInfo.getAccessToken(), authInfo.getHost());

		// Make the /account/info API call.
		TeamGetInfoResult info;
		try {
			info = dbxClient.team().getInfo();
		} catch (DbxException ex) {
			LOGGER.error("Error making API call: " + ex.getMessage());
			return null;
		}

		LOGGER.info("Load Dropbox-Team-Client for '" + info.getName() + "'");

		return dbxClient;

	}

	private static void printProgress(long uploaded, long size) {
		LOGGER.info("Uploaded "+uploaded+" / "+size+" bytes ("+100 * (uploaded / (double) size)+"%)" );
	}

	/**
	 * @see https://github.com/dropbox/dropbox-sdk-java/blob/master/examples/upload-file/src/main/java/com/dropbox/core/examples/upload_file/Main.java
	 * @param dbxClient
	 * @param localFile
	 * @param dropboxPath
	 */
	public static void uploadFile(DbxClientV2 dbxClient, File localFile, String dropboxPath) {
		try (InputStream in = new FileInputStream(localFile)) {
			ProgressListener progressListener = l -> printProgress(l, localFile.length());

			FileMetadata metadata = dbxClient.files().uploadBuilder(dropboxPath).withMode(WriteMode.ADD)
					.withClientModified(new Date(localFile.lastModified())).uploadAndFinish(in, progressListener);

			LOGGER.info("File '"+metadata.getName()+"' uploaded to '"+dropboxPath+"'");
		} catch (UploadErrorException ex) {
			LOGGER.error("Error uploading to Dropbox: " + ex.getMessage());
			System.exit(1);
		} catch (DbxException ex) {
			LOGGER.error("Error uploading to Dropbox: " + ex.getMessage());
			System.exit(1);
		} catch (IOException ex) {
			LOGGER.error("Error reading from file \"" + localFile + "\": " + ex.getMessage());
			System.exit(1);
		}
	}

	public static void uploadFileToTeamFolder(DbxTeamClientV2 dbxClient, File localFile, String targetPath, String displayUserName) {
		try {
//			List<TeamFolderMetadata> folders = dbxClient.team().teamFolderList().getTeamFolders();
//			for (TeamFolderMetadata teamFolderMetadata : folders) {
//				System.out.println(teamFolderMetadata);
//				if (teamFolderMetadata.getName().equals(teamFolder)) {
//					teamFolderId = teamFolderMetadata.getTeamFolderId();		
//				}
//			}
			
			String memberId = null;
			List<TeamMemberInfo> members = dbxClient.team().membersList().getMembers();
			for (TeamMemberInfo teamMemberInfo : members) {
			//	System.out.println(teamMemberInfo);
				if (teamMemberInfo.getProfile().getName().getDisplayName().equals(displayUserName)) {
					memberId = teamMemberInfo.getProfile().getTeamMemberId();
				}
			}
			
			DbxClientV2 client = dbxClient.asMember(memberId);
			uploadFile(client, localFile, targetPath);
		} catch (DbxException e) {
			LOGGER.error(e);
		}

	}

}