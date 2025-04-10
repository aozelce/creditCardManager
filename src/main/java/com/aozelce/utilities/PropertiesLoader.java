package com.aozelce.utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.*;

/**
 * @author Eric Knapp
 *
 */
public interface PropertiesLoader{

    Logger logger = LogManager.getLogger(PropertiesLoader.class);

    default Properties loadProperties(String propertiesFilePath) throws Exception {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException ioException) {
            logger.error("IOException occurred while loading properties from file: {}", propertiesFilePath, ioException);
            throw ioException;
        } catch (Exception exception) {
            logger.error("Unexpected error occurred while loading properties from file: {}", propertiesFilePath, exception);
            throw exception;
        }
        return properties;
    }
}