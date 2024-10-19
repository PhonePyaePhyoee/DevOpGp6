# 12. The Top N Populated Cities in the World Where N is Provided by the User

## Goal in Context
As a data analyst, I want to retrieve the top N most populated cities in the world, where N is provided by the user, so that I can analyze global urban population trends.

## Scope
Global city population data.

## Level
Primary task.

## Preconditions
- The database contains accurate population data for all cities.
- The user specifies the value of N (the number of cities to retrieve).

## Success End Condition
A list of the top N most populated cities is retrieved and ready for analysis.

## Failed End Condition
No data is retrieved, or the list is incomplete or incorrectly ordered.

## Primary Actor
Data Analyst (Document writer).

## Trigger
The data analyst inputs the value of N to retrieve population data for the top N cities.

## Main Success Scenario
1. The data analyst specifies the value of N (the number of cities to retrieve).
2. The system retrieves population data for all cities globally.
3. The system selects the top N most populated cities.
4. The system presents the ordered list of N cities to the data analyst.

## Extensions
- N is greater than the total number of cities in the database:
    - The system retrieves all cities and informs the data analyst that fewer than N cities exist.
- Query execution error (e.g., system failure):
    - The system notifies the data analyst of the error and suggests re-running the query.

## Sub-Variations
- The data analyst may choose a different value for N and re-run the query.

## Schedule
- Due Date: Optional, based on the analysis timeline.