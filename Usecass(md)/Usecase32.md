# 32. The Number of People Who Speak Chinese, English, Hindi, Spanish, and Arabic (Greatest to Smallest)

## Goal in Context
As a data analyst, I want to retrieve the number of speakers for specified languages (Chinese, English, Hindi, Spanish, and Arabic) organized from greatest to smallest, including the percentage of the world population, so that I can analyze linguistic demographics globally.

## Scope
Language-specific population data.

## Level
Primary task.

## Preconditions
- The database contains accurate data on the number of speakers for the specified languages.
- The database includes the total world population for percentage calculations.

## Success End Condition
A list of the number of speakers for the specified languages, organized from greatest to smallest, along with their respective percentages of the world population, is retrieved and ready for analysis.

## Failed End Condition
No data is retrieved, or the list is incomplete or incorrectly ordered.

## Primary Actor
Data Analyst. (Code Maintainer)

## Trigger
The data analyst requests population data for the specified languages.

## Main Success Scenario
1. The data analyst specifies the languages (Chinese, English, Hindi, Spanish, and Arabic) for analysis.
2. The system retrieves the number of speakers for each specified language.
3. The system calculates the percentage of each language's speakers relative to the total world population.
4. The system organizes the languages from greatest to smallest number of speakers.
5. The system presents the ordered list along with the percentages to the data analyst.

## Extensions
- **Missing data for a language:**
    - The system informs the data analyst if data for any specified language is unavailable.
- **Query execution error (e.g., system failure):**
    - The system notifies the data analyst of the error and suggests re-running the query.

## Sub-Variations
- The data analyst may choose to request data for additional languages.

## Schedule
**Due Date:** Optional, based on the analysis timeline.
