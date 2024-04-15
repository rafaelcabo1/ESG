// Package

package fr.capgemini.greensight.demo.queries;


// Dependencies

import fr.capgemini.greensight.demo.entities.Pollutant;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 */

public class GetPollutantsQueryResult
{
	// Attributes

	private List<Pollutant> pollutants;


	/**
	 *
	 */

	public GetPollutantsQueryResult()
	{
		this.pollutants = new ArrayList<Pollutant>();
	}


	/**
	 *
	 */

	public void addPollutant(Pollutant pollutant)
	{
		// Add pollutant to the list

		this.pollutants.add(pollutant);
	}


	/**
	 *
	 */

	public List<Pollutant> getPollutants()
	{
		return this.pollutants;
	}


	/**
	 *
	 */

	public String toString()
	{
		// Create a JSON array
		// This is where pollutants will be put

		JSONArray jsonArray = new JSONArray();


		// Parse each pollutant
		
		for (Pollutant pollutant : this.pollutants)
		{
			// Turn the pollutant into a JSON object

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("code", pollutant.getCode());
			jsonObject.put("name", pollutant.getName());


			// Add it to the JSON array

			jsonArray.put(jsonObject);
		}


		// Build the result

		return jsonArray.toString();
	}
}
