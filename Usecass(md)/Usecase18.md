# 18. All the Capital Cities in a Continent Organized by Largest Population to Smallest

## Goal in Context
As a data analyst, I want to retrieve a list of all capital cities in a specific continent organized by population, from largest to smallest, so that I can analyze population trends across capital cities within that continent.

## Scope
Continent-specific capital city population data.

## Level
Primary task.

## Preconditions
- The database contains accurate population data for all capital cities.
- The data analyst knows the name of the continent for analysis.

## Success End Condition
A complete and accurate list of capital cities in the specified continent, sorted by population from largest to smallest, is retrieved and ready for analysis.

## Failed End Condition
No data is retrieved, or the list is incomplete or incorrectly ordered.

## Primary Actor
Data Analyst.

## Trigger
The data analyst requests population data for capital cities in a specific continent.

---

## Main Success Scenario

1. The data analyst specifies the continent for analysis.
2. The system retrieves population data for all capital cities in the specified continent.
3. The system organizes the capital cities from largest to smallest population.
4. The system presents the ordered list to the data analyst.

---

## Extensions

- **Missing or incomplete population data for a capital city:**
    - The system skips the city and informs the data analyst of the incomplete data.

- **Query execution error (e.g., system failure):**
    - The system notifies the data analyst of the error and suggests re-running the query.

---

## Sub-Variations
- The data analyst may choose to focus on another continent for analysis.

---

## Schedule
- **Due Date:** Optional, based on the analysis timeline.
