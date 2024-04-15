// Package

package fr.capgemini.greensight.demo.properties;


// Dependencies

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 *
 */

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")

public class DatabaseProperties
{
	// Attributes
	
	private String url;
	private String username;
	private String password;


	/**
	 *
	 */

	public String getUrl()
	{
		return this.url;
	}


	/**
	 *
	 */

	public String getUsername()
	{
		return this.username;
	}


	/**
	 *
	 */

	public String getPassword()
	{
		return this.password;
	}


	/**
	 *
	 */

	public void setUrl(String url)
	{
		this.url = url;
	}


	/**
	 *
	 */

	public void setUsername(String username)
	{
		this.username = username;
	}


	/**
	 *
	 */

	public void setPassword(String password)
	{
		this.password = password;
	}
}
