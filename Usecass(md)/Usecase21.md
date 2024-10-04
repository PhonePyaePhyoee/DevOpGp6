# 21. The top N populated capital cities in a continent where N is provided by the user

## Goal in Context
As a data analyst, I want to retrieve the top N most populated capital cities in a specific continent, where N is provided by the user, so that I can analyze population trends within capital cities across that continent.

## Scope
Continent-specific capital city population data.

## Level
Primary task.

## Preconditions
- The database contains accurate population data for all capital cities in the specified continent.
- The user specifies both the continent and the value of N (the number of cities to retrieve).

## Success End Condition
A list of the top N most populated capital cities in the specified continent is retrieved and ready for analysis.

## Failed End Condition
No data is retrieved, or the list is incomplete or incorrectly ordered.

## Primary Actor
Data Analyst. (Backup Coder)

## Trigger
The data analyst inputs the continent and the value of N to retrieve population data for the top N capital cities.

## Main Success Scenario
1. The data analyst specifies the continent and the value of N.
2. The system retrieves population data for all capital cities in the specified continent.
3. The system selects the top N most populated capital cities.
4. The system presents the ordered list of N cities to the data analyst.

## Extensions
- **N is greater than the total number of capital cities in the continent:**
    - The system retrieves all capital cities in the continent and informs the data analyst that fewer than N cities exist.
- **Query execution error (e.g., system failure):**
    - The system notifies the data analyst of the error and suggests re-running the query.

## Sub-Variations
- The data analyst may choose a different continent or a different value for N and re-run the query.

## Schedule
**Due Date**: Optional, based on the analysis timeline.
