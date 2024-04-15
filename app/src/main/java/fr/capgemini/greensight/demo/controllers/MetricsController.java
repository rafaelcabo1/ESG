//Package

package fr.capgemini.greensight.demo.controllers;


// Dependencies

import fr.capgemini.greensight.demo.exceptions.GetMetricsQueryException;
import fr.capgemini.greensight.demo.queries.GetMetricsQueryA;
import fr.capgemini.greensight.demo.queries.GetMetricsQueryB;
import fr.capgemini.greensight.demo.queries.GetMetricsQueryC;
import fr.capgemini.greensight.demo.queries.GetMetricsQueryResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 */

@RestController

public class MetricsController
{
	// Attributes

	@Autowired
	private GetMetricsQueryA getMetricsQueryA;

	@Autowired
	private GetMetricsQueryB getMetricsQueryB;

	@Autowired
	private GetMetricsQueryC getMetricsQueryC;


	/**
	 *
	 */

	@GetMapping
	(
		value = "/metrics/a",
		produces = MediaType.TEXT_PLAIN_VALUE
	)

	public ResponseEntity<String> getMetricsA()
	{
		try
		{
			// Run the query

			GetMetricsQueryResult getMetricsQueryResult = this.getMetricsQueryA.run();


			return new ResponseEntity
			(
				getMetricsQueryResult.toString(),
				HttpStatus.OK
			);
		}
		catch (GetMetricsQueryException e)
		{
			return new ResponseEntity
			(
				e.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}


	/**
	 *
	 */

	@GetMapping
	(
		value = "/metrics/b",
		produces = MediaType.TEXT_PLAIN_VALUE
	)

	public ResponseEntity<String> getMetricsB()
	{
		try
		{
			// Run the query

			GetMetricsQueryResult getMetricsQueryResult = this.getMetricsQueryB.run();


			return new ResponseEntity
			(
				getMetricsQueryResult.toString(),
				HttpStatus.OK
			);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return new ResponseEntity
			(
				e.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}

	
	/**
	 *
	 */

	@GetMapping
	(
		value = "/metrics/c",
		produces = MediaType.TEXT_PLAIN_VALUE
	)

	public ResponseEntity<String> getMetricsC()
	{
		try
		{
			// Run the query

			GetMetricsQueryResult getMetricsQueryResult = this.getMetricsQueryC.run();


			return new ResponseEntity
			(
				getMetricsQueryResult.toString(),
				HttpStatus.OK
			);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return new ResponseEntity
			(
				e.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}
}
