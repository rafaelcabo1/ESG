// Package

package fr.capgemini.greensight.demo.factories;


// Dependencies

import fr.capgemini.greensight.demo.ApplicationConfiguration;
import fr.capgemini.greensight.demo.entities.Measure;
import fr.capgemini.greensight.demo.entities.Pollutant;
import fr.capgemini.greensight.demo.entities.Region;
import fr.capgemini.greensight.demo.exceptions.GetPollutantsQueryException;
import fr.capgemini.greensight.demo.exceptions.GetRegionsQueryException;
import fr.capgemini.greensight.demo.exceptions.MeasureFactoryException;
import fr.capgemini.greensight.demo.queries.GetPollutantsQuery;
import fr.capgemini.greensight.demo.queries.GetPollutantsQueryResult;
import fr.capgemini.greensight.demo.queries.GetRegionsQuery;
import fr.capgemini.greensight.demo.queries.GetRegionsQueryResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 */

@Component

public class MeasureFactory
{
	// Attributes

	@Autowired
	private ApplicationConfiguration applicationConfiguration;

	@Autowired
	private GetPollutantsQuery getPollutantsQuery;

	@Autowired
	private GetRegionsQuery getRegionsQuery;


	/**
	 *
	 */

	public List<Measure> createMeasures() throws MeasureFactoryException
	{
		try
		{
			// Start date is now

			Date currentDate = new Date();


			// End date is now + duration (hours)

			Calendar calendar = Calendar.getInstance();

			calendar.setTime(currentDate);
			calendar.add(Calendar.HOUR, this.applicationConfiguration.getMeasuresProperties().getDuration());

			Date endDate = calendar.getTime();


			// Get the list of pollutants

			GetPollutantsQueryResult getPollutantsQueryResult = this.getPollutantsQuery.run();
			List<Pollutant> pollutants = getPollutantsQueryResult.getPollutants();


			// Get the list of regions

			GetRegionsQueryResult getRegionsQueryResult = this.getRegionsQuery.run();
			List<Region> regions = getRegionsQueryResult.getRegions();


			// Create random measures for each date / pollutant / region

			List<Measure> measures = new ArrayList<Measure>();
			Random random = new Random();

			while (currentDate.before(endDate))
			{
				//

				this.addMeasures(measures, pollutants, regions, currentDate, random);


				// Increment current date

				calendar.setTime(currentDate);
				calendar.add(Calendar.SECOND, this.applicationConfiguration.getMeasuresProperties().getFrequency());
				currentDate = calendar.getTime();
			}


			return measures;
		}
		catch (GetPollutantsQueryException e)
		{
			throw new MeasureFactoryException("Getting pollutants failed", e);
		}
		catch (GetRegionsQueryException e)
		{
			throw new MeasureFactoryException("Getting regions failed", e);
		}
	}


	/**
	 *
	 */

	private void addMeasures(List<Measure> measures, List<Pollutant> pollutants, List<Region> regions, Date currentDate, Random random)
	{
		for (Pollutant pollutant : pollutants)
		{
			for (Region region : regions)
			{
				// Create a random measure

				Measure measure = new Measure();

				measure.setPollutant(pollutant.getCode());
				measure.setRegion(region.getCode());
				measure.setDatetime(currentDate);
				measure.setValue(random.nextInt(101));


				// Add the measure to the list

				measures.add(measure);
			}
		}
	}
}
