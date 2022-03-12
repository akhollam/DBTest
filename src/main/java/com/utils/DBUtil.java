package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DBUtil.class);
	
	static Properties properties = new Properties();

	static {

		try {
			
			LOGGER.info("initializing db propertis file.");
			
			InputStream inputStream = ClassLoader.getSystemResourceAsStream("db.properties");
			properties.load(inputStream);
			
			LOGGER.debug("file loaded successfully. ");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws SQLException {

		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");

		LOGGER.trace("get connection called. ");
		
		return DriverManager.getConnection(url, user, password);

	}

}
