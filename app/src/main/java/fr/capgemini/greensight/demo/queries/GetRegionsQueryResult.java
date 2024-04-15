// Package

package fr.capgemini.greensight.demo.queries;


// Dependencies

import fr.capgemini.greensight.demo.entities.Region;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 */

public class GetRegionsQueryResult
{
	// Attributes

	private List<Region> regions;


	/**
	 *
	 */

	public GetRegionsQueryResult()
	{
		this.regions = new ArrayList<Region>();
	}


	/**
	 *
	 */

	public void addRegion(Region region)
	{
		// Add region to the list

		this.regions.add(region);
	}


	/**
	 *
	 */

	public List<Region> getRegions()
	{
		return this.regions;
	}


	/**
	 *
	 */

	public String toString()
	{
		// Create a JSON array
		// This is where regions will be put

		JSONArray jsonArray = new JSONArray();


		// Parse each region
		
		for (Region region : this.regions)
		{
			// Turn the region into a JSON object

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("key", region.getCode());
			jsonObject.put("name", region.getName());
			jsonObject.put("longitude", region.getLongitude());
			jsonObject.put("latitude", region.getLatitude());


			// Add it to the JSON array

			jsonArray.put(jsonObject);
		}


		// Build the result

		return jsonArray.toString();
	}
}
