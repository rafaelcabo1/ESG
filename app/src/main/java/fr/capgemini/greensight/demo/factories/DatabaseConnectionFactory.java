// Package

package fr.capgemini.greensight.demo.factories;


// Dependencies

import fr.capgemini.greensight.demo.ApplicationConfiguration;
import fr.capgemini.greensight.demo.exceptions.DatabaseConnectionException;

import java.lang.ClassNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 */

@Component

public class DatabaseConnectionFactory
{
	// Attributes

	@Autowired
	private ApplicationConfiguration applicationConfiguration;


	/**
	 *
	 */

	public Connection createDatabaseConnection() throws DatabaseConnectionException
	{
		try
		{		
			// Load the PostgreSQL driver class

			Class.forName("org.postgresql.Driver");

			
			// Return a database connection

			return DriverManager.getConnection
			(
				this.applicationConfiguration.getDatabaseProperties().getUrl(),
				this.applicationConfiguration.getDatabaseProperties().getUsername(),
				this.applicationConfiguration.getDatabaseProperties().getPassword()
			);
		}
		catch (ClassNotFoundException e)
		{
			throw new DatabaseConnectionException("PostgreSQL driver not found", e);
		}
		catch (SQLException e)
		{
			throw new DatabaseConnectionException("Connection to PostgreSQL database has failed", e);
		}
	}
}
