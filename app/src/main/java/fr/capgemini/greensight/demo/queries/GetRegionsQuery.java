// Package

package fr.capgemini.greensight.demo.queries;


// Dependencies

import fr.capgemini.greensight.demo.entities.Region;
import fr.capgemini.greensight.demo.exceptions.DatabaseConnectionException;
import fr.capgemini.greensight.demo.exceptions.GetRegionsQueryException;
import fr.capgemini.greensight.demo.factories.DatabaseConnectionFactory;
import fr.capgemini.greensight.demo.queries.GetRegionsQueryResult;

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

public class GetRegionsQuery
{
	// Attributes

	@Autowired
	private DatabaseConnectionFactory databaseConnectionFactory;


	/**
	 *
	 */

	public GetRegionsQueryResult run() throws GetRegionsQueryException
	{
		try
		{
			// Create a database connection

			Connection databaseConnection = this.databaseConnectionFactory.createDatabaseConnection();
			Statement statement = databaseConnection.createStatement();


			// List regions from the database

			ResultSet queryResult = statement.executeQuery("SELECT id, code, name, longitude, latitude FROM regions");


			// Create the result

			GetRegionsQueryResult result = new GetRegionsQueryResult();


			// Parse SQL query result rows

			while (queryResult.next())
			{
				// Add the row to regions

				result.addRegion
				(
					new Region
					(
						queryResult.getInt("id"),
						queryResult.getString("code"),
						queryResult.getString("name"),
						queryResult.getFloat("longitude"),
						queryResult.getFloat("latitude")
					)
				);
			}
			

			// Close the database connection

			databaseConnection.close();


			return result;
		}
		catch (DatabaseConnectionException e)
		{
			throw new GetRegionsQueryException("Database connection creation failed", e);
		}
		catch (SQLException e)
		{
			throw new GetRegionsQueryException("SQL query failed", e);
		}
	}
}
