// Package

package fr.capgemini.greensight.demo.queries;


// Dependencies

import fr.capgemini.greensight.demo.ApplicationConfiguration;
import fr.capgemini.greensight.demo.entities.Measure;
import fr.capgemini.greensight.demo.exceptions.DatabaseConnectionException;
import fr.capgemini.greensight.demo.exceptions.GetMetricsQueryException;
import fr.capgemini.greensight.demo.factories.DatabaseConnectionFactory;
import fr.capgemini.greensight.demo.queries.GetMetricsQueryResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 */

@Component

public class GetMetricsQueryA
{
	// Attributes

	@Autowired
	private ApplicationConfiguration applicationConfiguration;

	@Autowired
	private DatabaseConnectionFactory databaseConnectionFactory;


	/**
	 *
	 */

	public GetMetricsQueryResult run() throws GetMetricsQueryException
	{
		try
		{
			// Build the start (Xs ago) / end (now) dates

			Calendar calendar = Calendar.getInstance();

			Date endDate = new Date();

			calendar.setTime(endDate);
			calendar.add(Calendar.SECOND, this.applicationConfiguration.getMeasuresProperties().getFrequency() * -1);
			
			Date startDate = calendar.getTime();


			// Create a database connection

			Connection databaseConnection = this.databaseConnectionFactory.createDatabaseConnection();


			// Build the SELECT query

			String selectQuery = "SELECT id, pollutant, region, datetime, value FROM measures WHERE datetime BETWEEN ? AND ?";

			PreparedStatement selectQueryStatement = databaseConnection.prepareStatement(selectQuery);

			selectQueryStatement.setTimestamp(1, new java.sql.Timestamp(startDate.getTime()));
			selectQueryStatement.setTimestamp(2, new java.sql.Timestamp(endDate.getTime()));


			// Execute the SELECT query

			ResultSet selectQueryResult = selectQueryStatement.executeQuery();


			// Create the result

			GetMetricsQueryResult getMetricsQueryResult = new GetMetricsQueryResult();


			// Parse SQL query result rows

			while (selectQueryResult.next())
			{
				// Add the row to measures

				getMetricsQueryResult.addMeasure
				(
					new Measure
					(
						selectQueryResult.getInt("id"),
						selectQueryResult.getString("pollutant"),
						selectQueryResult.getString("region"),
						selectQueryResult.getTimestamp("datetime"),
						selectQueryResult.getInt("value"),
						""
					)
				);
			}
			

			// Close the database connection

			databaseConnection.close();


			return getMetricsQueryResult;
		}
		catch (DatabaseConnectionException e)
		{
			throw new GetMetricsQueryException("Database connection creation failed", e);
		}
		catch (SQLException e)
		{
			throw new GetMetricsQueryException("SQL query failed", e);
		}
	}
}
