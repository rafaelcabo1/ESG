//Package

package fr.capgemini.greensight.demo.controllers;


// Dependencies

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 */

@RestController

public class ReadyController
{
	/**
	 *
	 */

	@GetMapping
	(
		value = "/ready",
		produces = MediaType.TEXT_PLAIN_VALUE
	)

	public ResponseEntity<String> getReady()
	{
		return new ResponseEntity
		(
			"App is ready",
			HttpStatus.OK
		);
	}
}
