// Package

package fr.capgemini.greensight.demo.queries;


// Dependencies

import fr.capgemini.greensight.demo.entities.Pollutant;
import fr.capgemini.greensight.demo.exceptions.DatabaseConnectionException;
import fr.capgemini.greensight.demo.exceptions.GetPollutantsQueryException;
import fr.capgemini.greensight.demo.factories.DatabaseConnectionFactory;
import fr.capgemini.greensight.demo.queries.GetPollutantsQueryResult;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 */

@Component

/**
 *
 */

public class GetPollutantsQuery
{
	// Attributes

	@Autowired
	private DatabaseConnectionFactory databaseConnectionFactory;


	/**
	 *
	 */

	public GetPollutantsQueryResult run() throws GetPollutantsQueryException
	{
		try
		{
			// Create a database connection

			Connection databaseConnection = this.databaseConnectionFactory.createDatabaseConnection();
			Statement statement = databaseConnection.createStatement();


			// List pollutants from the database

			ResultSet queryResult = statement.executeQuery("SELECT id, code, name FROM pollutants");


			// Create the result

			GetPollutantsQueryResult result = new GetPollutantsQueryResult();


			// Parse SQL query result rows

			while (queryResult.next())
			{
				// Add the row to pollutants

				result.addPollutant
				(
					new Pollutant
					(
						queryResult.getInt("id"),
						queryResult.getString("code"),
						queryResult.getString("name")
					)
				);
			}
			

			// Close the database connection

			databaseConnection.close();


			return result;
		}
		catch (DatabaseConnectionException e)
		{
			throw new GetPollutantsQueryException("Database connection creation failed", e);
		}
		catch (SQLException e)
		{
			throw new GetPollutantsQueryException("SQL query failed", e);
		}
	}
}
