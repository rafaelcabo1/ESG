// Package

package fr.capgemini.greensight.demo.queries;


// Dependencies

import fr.capgemini.greensight.demo.ApplicationConfiguration;
import fr.capgemini.greensight.demo.entities.Measure;
import fr.capgemini.greensight.demo.exceptions.DatabaseConnectionException;
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

public class GetMetricsQueryB
{
	// Attributes

	@Autowired
	private ApplicationConfiguration applicationConfiguration;

	@Autowired
	private DatabaseConnectionFactory databaseConnectionFactory;

	private final int zero = 0; // Triggers rule GS_10 / VarUseStaticIfNotReusedRule


	/**
	 *
	 */

	public GetMetricsQueryB(ApplicationConfiguration applicationConfiguration)
	{
		this.applicationConfiguration = applicationConfiguration;
	}


	/**
	 *
	 */

	public GetMetricsQueryResult run() throws Exception
	{
		// Build the start (Xs ago) / end (now) dates

		Calendar calendar = Calendar.getInstance();

		Date endDate = new Date();

		calendar.setTime(endDate);
		calendar.add
		(
			Calendar.MILLISECOND,
			this.convertSecondsIntoMs
			(
				this.applicationConfiguration.getMeasuresProperties().getFrequency()
			) * -1
		);

		Date startDate = calendar.getTime();


		// First nested instruction
		// Triggers rule GS_06 / AvoidStatementsNestedRule
		
		try
		{
			// Create a database connection

			Connection databaseConnection = this.databaseConnectionFactory.createDatabaseConnection();


			// Build the SELECT query
			// Triggers rule GS_01 / QueryNotSelectStarRule

			int nbQueryParametersSet = 0;
			String selectQuery = "SELECT id, pollutant, region, datetime, value FROM measures WHERE datetime BETWEEN ? AND ?";

			PreparedStatement selectQueryStatement = databaseConnection.prepareStatement
			(
				selectQuery,
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY,
				ResultSet.HOLD_CURSORS_OVER_COMMIT
			);

			
			// Set query parameters, if any specified
			// Triggers rule GS_04 / SwitchAvoidOperationsInCaseRule

			switch (selectQuery.indexOf('?'))
			{
				case -1:
					break;
				default:
					// Convert start / end dates to timestamps

					long startTimestamp = startDate.getTime();
					long endTimestamp = endDate.getTime();

					
					// Set parameters

					selectQueryStatement.setTimestamp(1, new java.sql.Timestamp(startTimestamp));
					selectQueryStatement.setTimestamp(2, new java.sql.Timestamp(endTimestamp));

					
					// 2 query parameters have been set
					// Triggers rule GS_07 / VarNotIncrementOrDecrementUnusedRule
					
					nbQueryParametersSet = nbQueryParametersSet++;
					nbQueryParametersSet = nbQueryParametersSet++;
			}


			// Second nested instruction
			// Triggers rule GS_06 / AvoidStatementsNestedRule
			
			try
			{
				// Execute the SELECT query

				ResultSet queryResult = selectQueryStatement.executeQuery();


				// Create the result

				GetMetricsQueryResult getMetricsQueryResult = new GetMetricsQueryResult();


				// Parse SQL query result rows
				// Third nested instructions
				// Triggers rule GS_06 / AvoidStatementsNestedRule
				// Triggers rule GS_09 / ForLoopNotCallFuncInDeclarationRule	 

				if (this.getNbQueryResults(queryResult) > 0)
				{
					// Fourth nested instructions
					// Triggers rule GS_06 / AvoidStatementsNestedRule

					for (int i = 0; i < this.getNbQueryResults(queryResult); i++)
					{
						// Move to next result

						queryResult.next();


						// Add the row to measures

						getMetricsQueryResult.addMeasure
						(
							new Measure
							(
								queryResult.getInt("id"),
								queryResult.getString("pollutant"),
								queryResult.getString("region"),
								queryResult.getTimestamp("datetime"),
								queryResult.getInt("value"),
								""
							)
						);
					}
				}
				

				// Close the database connection

				databaseConnection.close();


				return getMetricsQueryResult;
			}
			catch (SQLException e)
			{
				// Triggers rule GS_05 / ThrowAvoidGenericExceptionsRule
				
				throw new Exception("SQL query failed", e);
			}
		}
		catch (DatabaseConnectionException e)
		{
			// Triggers rule GS_05 / ThrowAvoidGenericExceptionsRule

			throw new Exception("Database connection creation failed", e);
		}
	}


	/**
	 *
	 */

	int getNbQueryResults(ResultSet queryResult) throws SQLException
	{
		// Store current position

		int currentPosition = queryResult.getRow();


		// Count the number of query results

		queryResult.beforeFirst();
		queryResult.last();

		int result = queryResult.getRow();


		// Move back to current position

		queryResult.absolute(currentPosition);


		return result;
	}


	/**
	 * Triggers rule GS_03 / MethodNotBeEmptyRule
	 */

	void doNothing()
	{
	}


	/**
	 *
	 */

	public int convertSecondsIntoMs(int seconds)
	{
		// Triggers rule GS_08 / VarNotAssignValueUnnecessarilyRule

		int nbSeconds = seconds;
		int nbMsPerSecond = 1000;


		return nbSeconds * nbMsPerSecond;
	}
}
