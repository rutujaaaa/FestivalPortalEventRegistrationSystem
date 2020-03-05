package com.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * 
 * A helper class that centralizes the management of data connections in the underlying database. <br/>
 * 
 * 
 */
public class FERSDataConnection {

	//LOGGER for logging connection details
	private static Logger log = Logger.getLogger(FERSDataConnection.class);
	
	// New instance of Connection
	private static Connection connection = null;

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * 
	 * Open connection to access the underlying database. <br/>
	 * 
	 * @return Connection
	 *  
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * 
	 */

	public static Connection createConnection() throws ClassNotFoundException,
			SQLException {
		System.out.print("Connected\n");
		Class.forName("com.mysql.jdbc.Driver");
		System.out.print("Connected1 \n");
		log.info("----Connecting established with MYSQL database----");
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/festivaldatabase", "root", "rutujad605");
		
		System.out.print("Connected Created \n");
		
		log.info("----Connection established with MYSQL database----");
		return connection;
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * 
	 * Close connection accessing the underlying database. <br/>
	 * 
	 * @return Connection
	 *  
	 * @throws SQLException
	 * 
	 */

	public static void closeConnection() throws SQLException {
		log.info("----Connection closed with MYSQL database----");
		connection.close();
	}
}
