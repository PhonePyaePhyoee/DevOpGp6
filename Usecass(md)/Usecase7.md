# Use Case: Retrieve Cities Organized by Population

## Goal in Context
As a data analyst, I want to retrieve a list of all cities in the world organized by population, from largest to smallest, so that I can analyze urban population trends.

## Scope
Global city population data.

## Level
Primary task.

## Preconditions
- The database contains accurate population data for all cities.

## Success End Condition
A complete and accurate list of cities, sorted by population from largest to smallest, is retrieved and ready for analysis.

## Failed End Condition
No data is retrieved, or the list is incomplete or incorrectly sorted.

## Primary Actor
Data Analyst. (Code Maintainer)

## Trigger
The data analyst requests population data for all cities in the world.

---

## MAIN SUCCESS SCENARIO

1. The data analyst requests a list of cities organized by population.
2. The system retrieves population data for all cities globally.
3. The system organizes the cities from largest to smallest population.
4. The system presents the ordered list to the data analyst.

---

## EXTENSIONS

### Missing or incomplete population data for a city:
- The system skips the city and informs the data analyst of the incomplete data.

### Query execution error (e.g., system failure):
- The system notifies the data analyst of the error and suggests re-running the query.

---

## SUB-VARIATIONS
None.

---

## SCHEDULE
**DUE DATE:** Optional, based on the analysis timeline.
