# Use Case: Retrieving Population Data by Urban and Rural Demographics for Each Country

## Goal in Context
As a data analyst, I want to retrieve the total population of people, the number of people living in cities, and the number of people not living in cities for each country, so that I can analyze urbanization trends and population distribution at the country level.

## Scope
Country-specific population data, including urban and rural demographics.

## Level
Primary task.

## Preconditions
- The database contains accurate population data categorized by country, including urban and rural populations.

## Success End Condition
A comprehensive report detailing the total population, urban population, and rural population for each country is retrieved and ready for analysis.

## Failed End Condition
No data is retrieved, or the report is incomplete or incorrectly ordered.

## Primary Actor
Data Analyst.

## Trigger
The data analyst requests population data for each country.

## Main Success Scenario
1. The data analyst requests population data for all countries.
2. The system retrieves the total population, urban population, and rural population data for each country.
3. The system organizes and formats the data for clarity.
4. The system presents the comprehensive report to the data analyst.

## Extensions
- **Missing or incomplete population data for a country:**
    - The system skips the country and informs the data analyst of the incomplete data.
- **Query execution error (e.g., system failure):**
    - The system notifies the data analyst of the error and suggests re-running the query.

## Sub-Variations
- The data analyst may choose to filter the data by specific countries or compare multiple countries.

## Schedule
- **Due Date:** Optional, based on the analysis timeline.
