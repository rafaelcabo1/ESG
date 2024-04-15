// Package

package fr.capgemini.greensight.demo.entities;


// Dependencies

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 */

@Entity
@Table(name = "regions")

public class Region
{
	// Attributes

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String code;
	private String name;
	private float longitude;
	private float latitude;
	
	
	/**
	 *
	 */

	public Region()
	{
	}
	
	
	/**
	 *
	 */

	public Region(Integer id, String code, String name, float longitude, float latitude)
	{
		this.id = id;
		this.code = code;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
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

	public String getCode()
	{
		return code;
	}

	
	/**
	 *
	 */

	public void setCode(String code)
	{
		this.code = code;
	}

	
	/**
	 *
	 */

	public String getName()
	{
		return name;
	}

	
	/**
	 *
	 */

	public void setName(String name)
	{
		this.name = name;
	}

	
	/**
	 *
	 */

	public float getLongitude()
	{
		return longitude;
	}

	
	/**
	 *
	 */

	public void setLongitude(float longitude)
	{
		this.longitude = longitude;
	}

	
	/**
	 *
	 */

	public float getLatitude()
	{
		return latitude;
	}

	
	/**
	 *
	 */

	public void setLatitude(float latitude)
	{
		this.latitude = latitude;
	}
}
