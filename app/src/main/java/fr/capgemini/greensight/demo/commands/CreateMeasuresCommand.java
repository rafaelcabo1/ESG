// Package

package fr.capgemini.greensight.demo.commands;


// Dependencies

import fr.capgemini.greensight.demo.commands.CreateMeasuresCommandResult;
import fr.capgemini.greensight.demo.entities.Measure;
import fr.capgemini.greensight.demo.exceptions.CreateMeasuresCommandException;
import fr.capgemini.greensight.demo.exceptions.DatabaseConnectionException;
import fr.capgemini.greensight.demo.exceptions.MeasureFactoryException;
import fr.capgemini.greensight.demo.factories.DatabaseConnectionFactory;
import fr.capgemini.greensight.demo.factories.MeasureFactory;

import java.lang.StringBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 */

@Component

public class CreateMeasuresCommand
{
	// Attributes

	@Autowired
	private DatabaseConnectionFactory databaseConnectionFactory;

	@Autowired
	private MeasureFactory measureFactory;


	/**
	 *
	 */

	public CreateMeasuresCommandResult run() throws CreateMeasuresCommandException
	{
		try
		{
			// Create measures

			List<Measure> measures = this.measureFactory.createMeasures();


			// Create a database connection

			Connection databaseConnection = this.databaseConnectionFactory.createDatabaseConnection();


			// Purge measures table

			Statement truncateQueryStatement = databaseConnection.createStatement();
			truncateQueryStatement.executeUpdate("TRUNCATE TABLE measures RESTART IDENTITY");


			// Database cannot handle a single INSERT
			// Hence we must determine how many INSERT batches need to be made

			int nbMeasures = measures.size();
			int nbMeasuresPerBatch = 500;
			int nbBatches = nbMeasures / nbMeasuresPerBatch;

			if (nbMeasures - (nbBatches * nbMeasuresPerBatch) > 0)
			{
				// Add additional batch for remainder

				nbBatches++;
			}


			// Perform each batch

			for (int b = 0; b < nbBatches; b++)
			{
				// Determine how many measures
				// Will be processed in this batch

				int nbMeasuresRemaining = nbMeasures - (b * nbMeasuresPerBatch);
				int nbMeasuresInBatch = Math.min(nbMeasuresPerBatch, nbMeasuresRemaining);
				
				
				// Build SELECT query

				String[] values = new String[nbMeasuresInBatch];
				Arrays.fill(values, "(?, ?, ?, ?)");

				String insertQuery = "INSERT INTO measures(pollutant, region, datetime, value) VALUES " + String.join(", ", values);

				PreparedStatement insertQueryStatement = databaseConnection.prepareStatement(insertQuery);


				// Parse each measure in batch

				int p = 1;
				int rangeStart = b * nbMeasuresPerBatch;
				int rangeEnd = rangeStart + nbMeasuresInBatch;

				for (int m = rangeStart; m < rangeEnd; m++)
				{
					// Get the current measure

					Measure measure = measures.get(m);


					// Add measure data to SELECT query parameters

					insertQueryStatement.setString(p++, measure.getPollutant());
					insertQueryStatement.setString(p++, measure.getRegion());
					insertQueryStatement.setTimestamp(p++, new java.sql.Timestamp(measure.getDatetime().getTime()));
					insertQueryStatement.setInt(p++, measure.getValue());
				}


				// Batch is ready
				// Execute the INSERT query

				insertQueryStatement.executeUpdate();
			}


			// Build the UPDATE query

			String updateQuery = "UPDATE measures SET comment = ?";
			String measureComment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam fringilla aliquam tellus, non porttitor orci bibendum eget. Vivamus vel sem ut purus tempor imperdiet. In hac habitasse platea dictumst. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Nulla condimentum, urna at efficitur ullamcorper, orci orci luctus nunc, non tincidunt felis leo scelerisque justo. Phasellus suscipit facilisis nulla. Sed blandit, velit at aliquet bibendum, risus ipsum lacinia eros, in scelerisque odio metus euismod lectus. Phasellus maximus dolor ac nisl ultrices, id sollicitudin ligula maximus. Sed at lacus a ex porttitor ullamcorper. Aliquam condimentum, sem non faucibus ultricies, ipsum quam rutrum nulla, et pharetra nulla quam eu massa. Donec mattis blandit sagittis. Etiam et mattis elit. Curabitur enim metus, maximus quis felis non, aliquam mollis sem. Pellentesque vehicula purus ac interdum rhoncus. Suspendisse luctus ligula a eros aliquet bibendum pulvinar sit amet libero. Duis tempor magna eget mauris varius, tincidunt feugiat metus interdum.";

			PreparedStatement updateQueryStatement = databaseConnection.prepareStatement(updateQuery);

    		updateQueryStatement.setString(1, measureComment);


			// Execute the UPDATE query

			updateQueryStatement.executeUpdate();


			return new CreateMeasuresCommandResult();
		}
		catch (DatabaseConnectionException e)
		{
			throw new CreateMeasuresCommandException("Database connection creation failed", e);
		}
		catch (MeasureFactoryException e)
		{
			throw new CreateMeasuresCommandException("Measures creation failed", e);
		}
		catch (SQLException e)
		{
			throw new CreateMeasuresCommandException("SQL query failed", e);
		}
	}
}
