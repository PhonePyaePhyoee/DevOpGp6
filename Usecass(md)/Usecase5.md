# Top N Populated Countries in a Continent

## Goal in Context
As a data analyst, I want to retrieve the top N most populated countries within a specified continent, where N is provided by the user, so that I can analyze population distribution in that continent.

## Scope
Continent-specific population data.

## Level
Primary task.

## Preconditions
- The database contains accurate population data for all countries.
- The user specifies both the continent and the value of N (number of top countries).

## Success End Condition
A list of the top N most populated countries within the specified continent is retrieved and ready for analysis.

## Failed End Condition
No data is retrieved, or the list is incomplete or incorrectly ordered.

## Primary Actor
Data Analyst. (Host)

## Trigger
The data analyst inputs the name of the continent and the value of N to retrieve population data for the top N countries.

## Main Success Scenario
1. The data analyst specifies the continent and the value of N.
2. The system retrieves population data for all countries in the specified continent.
3. The system selects the top N most populated countries.
4. The system presents the ordered list of N countries to the data analyst.

## Extensions
- **N is greater than the total number of countries in the continent**:
    - The system retrieves all countries within the continent and informs the data analyst that fewer than N countries exist.

- **Query execution error (e.g., system failure)**:
    - The system notifies the data analyst of the error and suggests re-running the query.

## Sub-Variations
- The data analyst may choose a different continent or a different value for N and re-run the query.