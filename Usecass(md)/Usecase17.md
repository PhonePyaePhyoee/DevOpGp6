# 17. All the Capital Cities in the World Organized by Largest Population to Smallest

## Goal in Context
As a data analyst, I want to retrieve a list of all capital cities in the world organized by population, from largest to smallest, so that I can analyze global capital city population trends.

## Scope
Global capital city population data.

## Level
Primary task.

## Preconditions
- The database contains accurate population data for all capital cities.

## Success End Condition
A complete and accurate list of capital cities, sorted by population from largest to smallest, is retrieved and ready for analysis.

## Failed End Condition
No data is retrieved, or the list is incomplete or incorrectly ordered.

## Primary Actor
Data Analyst.(Host)

## Trigger
The data analyst requests population data for capital cities globally.

---

## Main Success Scenario

1. The data analyst requests a list of all capital cities.
2. The system retrieves population data for all capital cities globally.
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
- The data analyst may choose to filter the list further by region or continent.

---

## Schedule
- **Due Date:** Optional, based on the analysis timeline.
