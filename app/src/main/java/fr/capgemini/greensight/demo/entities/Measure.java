// Package

package fr.capgemini.greensight.demo.entities;


// Dependencies

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 */

@Entity
@Table(name = "measures")

public class Measure
{
	// Attributes

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String pollutant;
	private String region;
	private Date datetime;
	private Integer value;
	private String comment;

	
	/**
	 *
	 */

	public Measure()
	{
	}
	
	
	/**
	 *
	 */
	
	public Measure(Integer id, String pollutant, String region, Date datetime, Integer value, String comment)
	{
		this.id = id;
		this.pollutant = pollutant;
		this.region = region;
		this.datetime = datetime;
		this.value = value;
		this.comment = comment;
	}

	
	/**
	 *
	 */
	
	public Integer getId()
	{
		return id;
	}

	
	/**
	 *
	 */
	
	public void setId(Integer id)
	{
		this.id = id;
	}

	
	/**
	 *
	 */
	
	public String getPollutant()
	{
		return pollutant;
	}

	
	/**
	 *
	 */
	
	public void setPollutant(String pollutant)
	{
		this.pollutant = pollutant;
	}

	
	/**
	 *
	 */
	
	public String getRegion()
	{
		return region;
	}

	
	/**
	 *
	 */
	
	public void setRegion(String region)
	{
		this.region = region;
	}

	
	/**
	 *
	 */
	
	public Date getDatetime()
	{
		return datetime;
	}

	
	/**
	 *
	 */
	
	public void setDatetime(Date datetime)
	{
		this.datetime = datetime;
	}

	
	/**
	 *
	 */
	
	public Integer getValue()
	{
		return value;
	}

	
	/**
	 *
	 */
	
	public void setValue(Integer value)
	{
		this.value = value;
	}

	
	/**
	 *
	 */
	
	public String getComment()
	{
		return comment;
	}

	
	/**
	 *
	 */
	
	public void setComment(String comment)
	{
		this.comment = comment;
	}
}
