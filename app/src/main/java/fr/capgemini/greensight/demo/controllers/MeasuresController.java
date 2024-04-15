//Package

package fr.capgemini.greensight.demo.controllers;


// Dependencies

import fr.capgemini.greensight.demo.ApplicationConfiguration;
import fr.capgemini.greensight.demo.commands.CreateMeasuresCommand;
import fr.capgemini.greensight.demo.commands.CreateMeasuresCommandResult;
import fr.capgemini.greensight.demo.entities.Measure;
import fr.capgemini.greensight.demo.exceptions.CreateMeasuresCommandException;
import fr.capgemini.greensight.demo.properties.MeasuresProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 */

@RestController

public class MeasuresController
{
	// Attributes

	@Autowired
	private CreateMeasuresCommand createMeasuresCommand;


	/**
	 *
	 */

	@PostMapping(value = "/measures")

	public ResponseEntity<String> postMeasures()
	{
		try
		{
			// Run the command

			CreateMeasuresCommandResult createMeasuresCommandResult = this.createMeasuresCommand.run();


			return new ResponseEntity
			(
				"",
				HttpStatus.OK
			);
		}
		catch (CreateMeasuresCommandException e)
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
