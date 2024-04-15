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
@Table(name = "pollutants")

public class Pollutant
{
	// Attributes

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String code;
	private String name;
	
	
	/**
	 *
	 */

	public Pollutant()
	{
	}
	
	
	/**
	 *
	 */

	public Pollutant(Integer id, String code, String name)
	{
		this.id = id;
		this.code = code;
		this.name = name;
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
}
