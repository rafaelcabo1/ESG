// Package

package fr.capgemini.greensight.demo.queries;


// Dependencies

import fr.capgemini.greensight.demo.entities.Measure;

import java.lang.StringBuilder;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */

public class GetMetricsQueryResult
{
	// Attributes

	private List<Measure> measures;


	/**
	 *
	 */

	public GetMetricsQueryResult()
	{
		this.measures = new ArrayList<Measure>();
	}


	/**
	 *
	 */

	public void addMeasure(Measure measure)
	{
		// Add measure to the list

		this.measures.add(measure);
	}


	/**
	 *
	 */

	public List<Measure> getMeasures()
	{
		return this.measures;
	}


	/**
	 *
	 */

	public String toString()
	{
		// Create a string builder
		// This is where measures will be put

		StringBuilder stringBuilder = new StringBuilder();


		// Parse each measure
		
		for (Measure measure : this.measures)
		{
			// Convert it into a metric

			stringBuilder
			.append(measure.getPollutant())
			.append("{region=\"").append(measure.getRegion()).append("\"}")
			.append(" ")
			.append(measure.getValue())
			.append(" ")
			.append(measure.getDatetime().getTime())
			.append("\n");
		}


		// Build the result

		return stringBuilder.toString();
	}
}
