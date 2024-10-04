# 23. The Population of People, People Living in Cities, and People Not Living in Cities in Each Continent

## Goal in Context
As a data analyst, I want to retrieve the total population of people, the number of people living in cities, and the number of people not living in cities for each continent, so that I can analyze urbanization trends and population distribution.

## Scope
Continental population data, including urban and rural demographics.

## Level
Primary task.

## Preconditions
- The database contains accurate population data categorized by continent, including urban and rural populations.

## Success End Condition
A comprehensive report detailing the total population, urban population, and rural population for each continent is retrieved and ready for analysis.

## Failed End Condition
No data is retrieved, or the report is incomplete or incorrectly ordered.

## Primary Actor
Data Analyst (Document writer).

## Trigger
The data analyst requests population data for each continent.

## Main Success Scenario
1. The data analyst requests population data for all continents.
2. The system retrieves the total population, urban population, and rural population data for each continent.
3. The system organizes and formats the data for clarity.
4. The system presents the comprehensive report to the data analyst.

## Extensions
- Missing or incomplete population data for a continent:
    - The system skips the continent and informs the data analyst of the incomplete data.
- Query execution error (e.g., system failure):
    - The system notifies the data analyst of the error and suggests re-running the query.

## Sub-Variations
- The data analyst may choose to filter the data by specific continents or compare multiple continents.

## Schedule
- Due Date: Optional, based on the analysis timeline.

