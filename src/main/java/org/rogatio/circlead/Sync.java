/*
 * Circlead - Develop and structure evolutionary Organisations
 * 
 * @author Matthias Wegner
 * @version 0.1
 * @since 01.07.2018
 * 
 */
package org.rogatio.circlead;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rogatio.circlead.control.Repository;
import org.rogatio.circlead.control.synchronizer.SynchronizerResult;
import org.rogatio.circlead.control.synchronizer.atlassian.AtlassianSynchronizer;
import org.rogatio.circlead.control.synchronizer.file.FileSynchronizer;
import org.rogatio.circlead.model.Parameter;
import org.rogatio.circlead.model.WorkitemType;
import org.rogatio.circlead.model.work.Rolegroup;
import org.rogatio.circlead.util.ExcelUtil;
import org.rogatio.circlead.view.report.OverviewReport;
import org.rogatio.circlead.view.report.PersonListReport;
import org.rogatio.circlead.view.report.TeamCategoryReport;
import org.rogatio.circlead.view.report.ReworkReport;
import org.rogatio.circlead.view.report.RoleHolderReport;
import org.rogatio.circlead.view.report.RolegroupReport;
import org.rogatio.circlead.view.report.RolegroupSummaryReport;
import org.rogatio.circlead.view.report.ValidationReport;

/**
 * Synchronize-Starter.
 */
public class Sync {
	// TODO RecurrenceRule for person in team
	// TODO Comment for person in team
	// TODO Validation role status vs. person-holder status (i.e. paused, closed)
	// TODO Validation "empty" (<10 chars) text in role
	// TODO Excel-Exporter Persons, Category-Teams, ...
	// TODO FileSynchronizer-Image-Missing-Validator, Wrong-Image-Size (User)
	// TODO Atlassian User-Check (for Image-Usage or User-Link)
	// TODO Velocity als Render-Changer nutzen
	// TODO Activity-Process-Builder (yWorks) erzeugen

	/** The Constant REPORTS. */
	public static final boolean REPORTS = false;

	/** The Constant HOWTOS. */
	public static final boolean HOWTOS = false;

	/** The Constant ROLES. */
	public static final boolean ROLES = false;

	/** The Constant ROLEGROUPS. */
	public static final boolean ROLEGROUPS = false;

	/** The Constant PERSONS. */
	public static final boolean PERSONS = false;

	/** The Constant ACTIVITIES. */
	public static final boolean ACTIVITIES = false;

	public static final boolean TEAMS = true;

	public static final boolean WRITE_UPDATE = false;

	/** The Constant logger. */
	final static Logger LOGGER = LogManager.getLogger(Sync.class);

	/**
	 * The main method of the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		List<SynchronizerResult> results = null;

		Repository repository = Repository.getInstance();

		/*
		 * Add both syncronizers. One for atlassian-confluence for space 'CIRCLEAD' and
		 * one for the Filesystem in folder 'data'
		 */
		FileSynchronizer fsynchronizer = new FileSynchronizer("data");
		AtlassianSynchronizer asynchronizer = new AtlassianSynchronizer("CIRCLEAD");
		repository.addSynchronizer(asynchronizer);
		repository.addSynchronizer(fsynchronizer);

		/*
		 * Delete all workitem-folders, so the merging is not needed because confluence
		 * is in lead of the data. Gives speed to the data-synchronization.
		 */
		fsynchronizer.deleteAll();

		/* Loads all data (from confluence, because the folders are emtpy) */
		if (ROLES) {
			repository.loadRoles();
		}
		if (ROLEGROUPS) {
			repository.loadRolegroups();
		}
		if (PERSONS) {
			repository.loadPersons();
		}
		if (ACTIVITIES) {
			repository.loadActivities();
		}
		if (TEAMS) {
			repository.loadTeams();
		}

		/* Loads the index of howtos and reports from both interfaces */
		if (HOWTOS) {
			repository.loadIndexHowTos();
		}

		if (REPORTS) {
			repository.loadIndexReports();
		}

		/*
		 * Re-Render loaded data back to set interfaces. Update pages in confluence and
		 * writes html-pages to local folder 'web'
		 */
		if (WRITE_UPDATE) {
			results = repository.updateWorkitems();
		}

		if (REPORTS) {
			/* Add report-handler */
			/*
			 * if (repository.getRolegroups().size() > 0) { List<Rolegroup> rolegroups =
			 * repository.getRolegroups(); for (Rolegroup rolegroup : rolegroups) {
			 * repository.addReport(new RolegroupReport(rolegroup)); } }
			 */
			repository.addReport(new RoleHolderReport());
			repository.addReport(new OverviewReport());
			repository.addReport(new ValidationReport());
			repository.addReport(new ReworkReport());
			repository.addReport(new PersonListReport());
			repository.addReport(new RolegroupSummaryReport());
			repository.addReport(new TeamCategoryReport("Gebetsstunde"));
			// repository.addReport(new RoleIssueReport());

			/* Rewrite Reports */
			repository.addReports();
			results = repository.updateReports();
		}

//		repository.writeExcel("Mitarbeiterliste", WorkitemType.PERSON, null);
//		repository.writeExcel("Rollen", WorkitemType.ROLE, null);
//		repository.writeExcel("Rollengruppen", WorkitemType.ROLEGROUP, null);

		PrayHourExporter phe = new PrayHourExporter();
		phe.addSheet(PrayHourExporter.MODE_EXTERN);
		phe.addSheet(PrayHourExporter.MODE_INTERN);
		phe.addSheet(PrayHourExporter.MODE_NEED);
		phe.addSheet(PrayHourExporter.MODE_DETAIL);
		phe.export("Gebetsstundenübersicht");
		
	}

}
