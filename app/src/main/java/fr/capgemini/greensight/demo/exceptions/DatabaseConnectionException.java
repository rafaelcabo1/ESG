// Package

package fr.capgemini.greensight.demo.exceptions;


/**
 *
 */

public class DatabaseConnectionException extends Exception
{
	/**
	 *
	 */
	
	public DatabaseConnectionException(String message, Throwable origin)
	{
		super(message, origin);
	}
}
