// Package

package fr.capgemini.greensight.demo;


// Dependencies

import fr.capgemini.greensight.demo.properties.DatabaseProperties;
import fr.capgemini.greensight.demo.properties.MeasuresProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 */

@Component

public class ApplicationConfiguration
{
	// Attributes

	@Autowired
	private DatabaseProperties databaseProperties;

	@Autowired
	private MeasuresProperties measuresProperties;


	/**
	 *
	 */

	public DatabaseProperties getDatabaseProperties()
	{
		return this.databaseProperties;
	}


	/**
	 *
	 */

	public MeasuresProperties getMeasuresProperties()
	{
		return this.measuresProperties;
	}
}
