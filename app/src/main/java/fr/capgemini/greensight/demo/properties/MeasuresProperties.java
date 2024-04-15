// Package

package fr.capgemini.greensight.demo.properties;


// Dependencies

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 *
 */

@Configuration
@ConfigurationProperties(prefix = "measures")

public class MeasuresProperties
{
	// Attributes
	
	private Integer duration;
	private Integer frequency;


	/**
	 *
	 */

	public Integer getDuration()
	{
		return this.duration;
	}


	/**
	 *
	 */

	public Integer getFrequency()
	{
		return this.frequency;
	}


	/**
	 *
	 */

	public void setDuration(Integer duration)
	{
		this.duration = duration;
	}


	/**
	 *
	 */

	public void setFrequency(Integer frequency)
	{
		this.frequency = frequency;
	}
}
