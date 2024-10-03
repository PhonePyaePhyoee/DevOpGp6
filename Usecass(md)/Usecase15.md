# Use Case: Querying and Retrieving the Top N Most Populated Cities in a Country

## Goal in Context
As a data analyst, I want to retrieve the top N most populated cities in a specific country, where N is provided by the user, so that I can analyze urban population trends within that country.

## Scope
Country-specific city population data.

## Level
Primary task.

## Preconditions
- The database contains accurate population data for all cities.
- The user specifies both the country and the value of N (the number of cities to retrieve).

## Success End Condition
A list of the top N most populated cities in the specified country is retrieved and ready for analysis.

## Failed End Condition
No data is retrieved, or the list is incomplete or incorrectly ordered.

## Primary Actor
Data Analyst.

## Trigger
The data analyst inputs the name of the country and the value of N to retrieve population data for the top N cities.

## Main Success Scenario
1. The data analyst specifies the country and the value of N.
2. The system retrieves population data for all cities in the specified country.
3. The system selects the top N most populated cities.
4. The system presents the ordered list of N cities to the data analyst.

## Extensions
- **N is greater than the total number of cities in the country:**
    - The system retrieves all cities in the country and informs the data analyst that fewer than N cities exist.
- **Query execution error (e.g., system failure):**
    - The system notifies the data analyst of the error and suggests re-running the query.

## Sub-Variations
- The data analyst may choose a different country or a different value for N and re-run the query.

## Schedule
- **Due Date:** Optional, based on the analysis timeline.
