/*
 * Circlead - Develop and structure evolutionary Organisations
 * 
 * @author Matthias Wegner
 * @version 0.1
 * @since 01.07.2018
 * 
 */
package org.rogatio.circlead.util;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kjetland.jackson.jsonSchema.JsonSchemaGenerator;

/**
 * The Class FileUtil is a simple helper for File-IO-Handling.
 */
public class FileUtil {

	/** The Constant logger. */
	final static Logger LOGGER = LogManager.getLogger(FileUtil.class);

	/**
	 * Delete recursively a directory.
	 *
	 * @param f            the f
	 * @throws Exception             the exception
	 */
	public static void deleteRecursive(File f) throws Exception {
		try {
			// check for directory
			if (f.isDirectory()) {
				// list content of directory
				for (File c : f.listFiles()) {
					deleteRecursive(c);
				}
			}
			// delete, whatever is found
			if (!f.delete()) {
				throw new Exception("Delete command returned false for file: " + f);
			}
		} catch (Exception e) {
			throw new Exception("Failed to delete the folder: " + f, e);
		}
	}

	/**
	 * Write json-schema from object. Could be used for schema-validation of the workitem-json-format (FileSynchronizer)
	 *
	 * @param dir
	 *            the dir
	 * @param name
	 *            the name
	 * @param clazz
	 *            the clazz
	 */
	@SuppressWarnings("unchecked")
	public static void writeSchema(File dir, String name, @SuppressWarnings("rawtypes") Class clazz) {
		ObjectMapper schemaMapper = new ObjectMapper();
		schemaMapper.enable(SerializationFeature.INDENT_OUTPUT);
		JsonSchemaGenerator jsonSchemaGenerator = new JsonSchemaGenerator(schemaMapper);
		JsonNode jsonSchema = jsonSchemaGenerator.generateJsonSchema(clazz);

		File fs = new File(dir.toString() + File.separatorChar + name + ".schema.json");
		if (fs.exists()) {
			// delete schema if it already exists
			fs.delete();
		}
		try {
			// create new empty file
			fs.createNewFile();
		} catch (IOException e) {
			LOGGER.error(e);
		}
		try {
			// write schema-data to new created file
			schemaMapper.writeValue(fs, jsonSchema);
			LOGGER.info("Schema '" + name + "' written.");
		} catch (JsonGenerationException e) {
			LOGGER.error(e);
		} catch (JsonMappingException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}
}
