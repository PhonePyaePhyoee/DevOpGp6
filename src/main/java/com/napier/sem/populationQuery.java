package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class populationQuery {
    // Method to retrieve population data for each continent or region
    public List<Population> getPopulationDataByContinent(Connection con) throws SQLException {
        List<Population> populationReports = new ArrayList<>(); // List to store population reports

        Statement stmt = con.createStatement();

        // Modified query to calculate total population and population living in cities per continent
        String query = "SELECT country.Continent, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(COALESCE(city.Population, 0)) AS wholiveincities " +  // Use COALESCE to handle cases with no cities
                "FROM country " +
                "LEFT JOIN city ON country.Code = city.CountryCode " +  // Use LEFT JOIN to ensure countries without cities are included
                "GROUP BY country.Continent " +  // Group by continent
                "ORDER BY TotalPopulation ASC " + // Order by total population from smallest to largest
                "LIMIT 15"; // Limit results to 15

        ResultSet rs = stmt.executeQuery(query);

        // Processing the results
        while (rs.next()) {
            String continent = rs.getString("Continent");
            long totalPopulation = rs.getLong("TotalPopulation");
            long wholiveincities = rs.getLong("wholiveincities");
            long whonotliveincities = totalPopulation - wholiveincities; // Calculate population not living in cities

            // Create a new Population object and add it to the list
            populationReports.add(new Population(continent, totalPopulation, wholiveincities, whonotliveincities));
        }

        rs.close();
        return populationReports; // Return the list of population reports
    }

    // Method to display the population data for each continent, region, or country
    public void displayPopulationDataByContinent(List<Population> reports) {
        if (reports == null || reports.isEmpty()) {
            System.out.println("No population data found.");
            return;
        }

        // Header for the table
        System.out.println(" 23 Population Report by continent:");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-20s | %-18s | %-35s | %-35s",
                "Name", "Total Population", "Living in Cities", "Not Living in Cities"));
        System.out.println("---------------------------------------------------------------------------------------------");

        // Loop through each population report and format the output
        for (Population report : reports) {
            long totalPopulation = report.getTotalPopulation();
            long wholiveincities = report.getlivePopulation();
            long whonotliveincities = report.getnotlivePopulation();

            // Calculate percentages
            double livePercentage = totalPopulation > 0 ? ((double) wholiveincities / totalPopulation) * 100 : 0;
            double notLivePercentage = totalPopulation > 0 ? ((double) whonotliveincities / totalPopulation) * 100 : 0;

            // Display data with adjusted width for better alignment
            System.out.println(String.format("%-20s | %,18d | %,25d (%.2f%%) | %,25d (%.2f%%)",
                    report.getName(),
                    totalPopulation,
                    wholiveincities, livePercentage,   // People living in cities with percentage
                    whonotliveincities, notLivePercentage));  // People not living in cities with percentage
        }

        System.out.println("---------------------------------------------------------------------------------------------");
    }
    // Method to retrieve population data for each region
    public List<Population> getPopulationDataByRegion(Connection con) throws SQLException {
        List<Population> populationReports = new ArrayList<>(); // List to store population reports

        Statement stmt = con.createStatement();

        // Query to calculate total population and population living in cities per region
        String query = "SELECT country.Region, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(COALESCE(city.Population, 0)) AS wholiveincities " +  // Use COALESCE to handle cases with no cities
                "FROM country " +
                "LEFT JOIN city ON country.Code = city.CountryCode " +  // Use LEFT JOIN to ensure countries without cities are included
                "GROUP BY country.Region " +  // Group by region
                "ORDER BY TotalPopulation ASC " + // Order by total population from smallest to largest
                "LIMIT 15"; // Limit results to 15

        ResultSet rs = stmt.executeQuery(query);

        // Processing the results
        while (rs.next()) {
            String region = rs.getString("Region");
            long totalPopulation = rs.getLong("TotalPopulation");
            long wholiveincities = rs.getLong("wholiveincities");
            long whonotliveincities = totalPopulation - wholiveincities; // Calculate population not living in cities

            // Create a new Population object and add it to the list
            populationReports.add(new Population(region, totalPopulation, wholiveincities, whonotliveincities));
        }

        rs.close();
        return populationReports; // Return the list of population reports
    }

    // Method to display the population data for each region
    public void displayPopulationDataByRegion(List<Population> reports) {
        if (reports == null || reports.isEmpty()) {
            System.out.println("No population data found.");
            return;
        }

        // Header for the table
        System.out.println(" 24 Population Report by Region:");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-20s | %-18s | %-35s | %-35s",
                "Region", "Total Population", "Living in Cities", "Not Living in Cities"));
        System.out.println("---------------------------------------------------------------------------------------------");

        // Loop through each population report and format the output
        for (Population report : reports) {
            long totalPopulation = report.getTotalPopulation();
            long wholiveincities = report.getlivePopulation();
            long whonotliveincities = report.getnotlivePopulation();

            // Calculate percentages
            double livePercentage = totalPopulation > 0 ? ((double) wholiveincities / totalPopulation) * 100 : 0;
            double notLivePercentage = totalPopulation > 0 ? ((double) whonotliveincities / totalPopulation) * 100 : 0;

            // Display data with adjusted width for better alignment
            System.out.println(String.format("%-20s | %,18d | %,25d (%.2f%%) | %,25d (%.2f%%)",
                    report.getName(),
                    totalPopulation,
                    wholiveincities, livePercentage,   // People living in cities with percentage
                    whonotliveincities, notLivePercentage));  // People not living in cities with percentage
        }

        System.out.println("---------------------------------------------------------------------------------------------");
    }
    public List<Population> getPopulationDataByCountry(Connection con) throws SQLException {
        List<Population> populationReports = new ArrayList<>(); // List to store population reports

        Statement stmt = con.createStatement();

        // Adjusted query to calculate total population and population living in cities per country
        String query = "SELECT country.Name AS CountryName, " +
                "country.Population AS TotalPopulation, " + // Use country.Population directly
                "COALESCE(SUM(city.Population), 0) AS wholiveincities " +  // Use COALESCE to handle cases with no cities
                "FROM country " +
                "LEFT JOIN city ON country.Code = city.CountryCode " +  // Use LEFT JOIN to ensure countries without cities are included
                "GROUP BY country.Code, country.Name, country.Population " +  // Group by country code, name, and population
                "ORDER BY TotalPopulation DESC " + // Order by total population from smallest to largest
                "LIMIT 15"; // Limit results to 15

        ResultSet rs = stmt.executeQuery(query);

        // Processing the results
        while (rs.next()) {
            String countryName = rs.getString("CountryName");
            long totalPopulation = rs.getLong("TotalPopulation");
            long wholiveincities = rs.getLong("wholiveincities");
            long whonotliveincities = totalPopulation - wholiveincities; // Calculate population not living in cities

            // Create a new Population object and add it to the list
            populationReports.add(new Population(countryName, totalPopulation, wholiveincities, whonotliveincities));
        }

        rs.close();
        return populationReports; // Return the list of population reports
    }

    // Method to display the population data for each country
    public void displayPopulationDataByCountry(List<Population> reports) {
        if (reports == null || reports.isEmpty()) {
            System.out.println("No population data found.");
            return;
        }

        // Header for the table
        System.out.println(" 25 Population Report by Country:");
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-35s | %-18s | %-35s | %-35s",
                "Country", "Total Population", "Living in Cities", "Not Living in Cities"));
        System.out.println("------------------------------------------------------------------------------------------------");

        // Loop through each population report and format the output
        for (Population report : reports) {
            long totalPopulation = report.getTotalPopulation();
            long wholiveincities = report.getlivePopulation();
            long whonotliveincities = report.getnotlivePopulation();

            // Calculate percentages
            double livePercentage = totalPopulation > 0 ? ((double) wholiveincities / totalPopulation) * 100 : 0;
            double notLivePercentage = totalPopulation > 0 ? ((double) whonotliveincities / totalPopulation) * 100 : 0;

            // Display data with adjusted width for better alignment and formatting with commas
            System.out.println(String.format("%-35s | %,18d | %,25d (%.2f%%) | %,25d (%.2f%%)",
                    report.getName(),
                    totalPopulation,
                    wholiveincities, livePercentage,   // People living in cities with percentage
                    whonotliveincities, notLivePercentage));  // People not living in cities with percentage
        }

        System.out.println("------------------------------------------------------------------------------------------------");
    }
    // Method to retrieve population data for the world
    public List<Population> getWorldPopulationData(Connection con) throws SQLException {
        List<Population> populations = new ArrayList<>();
        Statement stmt = con.createStatement();

        // Query to calculate total world population and population living in cities
        String query = "SELECT SUM(country.Population) AS TotalPopulation, " +
                "(SELECT SUM(city.Population) FROM city) AS wholiveincities " +
                "FROM country";

        ResultSet rs = stmt.executeQuery(query);

        // Initialize population variables
        long totalPopulation = 0;
        long wholiveincities = 0;

        // Process the result
        if (rs.next()) {
            totalPopulation = rs.getLong("TotalPopulation");
            wholiveincities = rs.getLong("wholiveincities");
        }

        rs.close();

        // Calculate the population not living in cities
        long whonotliveincities = totalPopulation - wholiveincities;

        // Add population data to the list
        populations.add(new Population("World", totalPopulation, wholiveincities, whonotliveincities));

        return populations;
    }

    // Method to display the total world population
    public void displayTotalWorldPopulation(List<Population> populations) {
        if (populations == null || populations.isEmpty()) {
            System.out.println("No population data found for the world.");
            return;
        }

        // Get the world population data (assuming it's the first in the list)
        Population worldPopulation = populations.get(0);

        // Header for the world population report
        System.out.println(" 26 World Population:");

        // Display the total population in the desired format
        System.out.println("Population: " + String.format("%,d", worldPopulation.getTotalPopulation()));

    }
    // Method to retrieve population data for a specific continent
    // Method to retrieve population data for a specific continent
    public List<Population> getContinentPopulationData(Connection con, String continent) throws SQLException {
        List<Population> populations = new ArrayList<>();

        // Define the SQL query with a placeholder for the continent
        String query = "SELECT SUM(country.Population) AS TotalPopulation, " +
                "(SELECT SUM(city.Population) FROM city) AS wholiveincities " +
                "FROM country " +
                "WHERE country.Continent = ?";  // Use ? as a placeholder for continent // Use ? as a placeholder for continent

        // Use PreparedStatement to execute the query
        PreparedStatement stmt = con.prepareStatement(query);

        // Set the parameter for the continent
        stmt.setString(1, continent);  // Set the continent parameter (index 1)

        ResultSet rs = stmt.executeQuery(); // Execute the prepared statement

        // Initialize population variables
        long totalPopulation = 0;
        long wholiveincities = 0;

        // Process the result
        if (rs.next()) {
            totalPopulation = rs.getLong("TotalPopulation");
            wholiveincities = rs.getLong("wholiveincities");
        }

        rs.close();
        stmt.close();  // Always close the statement after use

        // Calculate the population not living in cities
        long whonotliveincities = totalPopulation - wholiveincities;

        // Add population data to the list
        populations.add(new Population(continent, totalPopulation, wholiveincities, whonotliveincities));

        return populations;
    }

    // Method to display the population of a specific continent
    public void displayContinentPopulation(List<Population> populations, String continent) {
        if (populations == null || populations.isEmpty()) {
            System.out.println("No population data found for the continent: " + continent);
            return;
        }

        // Get the population data for the continent (assuming it's the first in the list)
        Population continentPopulation = populations.get(0);

        // Header for the continent population report
        System.out.println(" 27 Population of " + continent + ":");

        // Display the total population in the desired format
        System.out.println("Population: " + String.format("%,d", continentPopulation.getTotalPopulation()));
    }

    // Method to retrieve population data for a specific region
    public List<Population> getRegionPopulationData(Connection con, String region) throws SQLException {
        List<Population> populations = new ArrayList<>();

        // Define the SQL query with a placeholder for the region
        String query = "SELECT SUM(country.Population) AS TotalPopulation, " +
                "(SELECT SUM(city.Population) FROM city) AS wholiveincities " +
                "FROM country " +
                "WHERE country.Region = ?";  // Use ? as a placeholder for region

        // Use PreparedStatement to execute the query
        PreparedStatement stmt = con.prepareStatement(query);

        // Set the parameter for the region
        stmt.setString(1, region);  // Set the region parameter (index 1)

        ResultSet rs = stmt.executeQuery(); // Execute the prepared statement

        // Initialize population variables
        long totalPopulation = 0;
        long wholiveincities = 0;

        // Process the result
        if (rs.next()) {
            totalPopulation = rs.getLong("TotalPopulation");
            wholiveincities = rs.getLong("wholiveincities");
        }

        rs.close();
        stmt.close();  // Always close the statement after use

        // Calculate the population not living in cities
        long whonotliveincities = totalPopulation - wholiveincities;

        // Add population data to the list
        populations.add(new Population(region, totalPopulation, wholiveincities, whonotliveincities));

        return populations;
    }

    // Method to display the population of a specific region
    public void displayRegionPopulation(List<Population> populations, String region) {
        if (populations == null || populations.isEmpty()) {
            System.out.println("No population data found for the region: " + region);
            return;
        }

        // Get the population data for the region (assuming it's the first in the list)
        Population regionPopulation = populations.get(0);

        // Header for the region population report
        System.out.println(" 28 Population of " + region + ":");

        // Display the total population in the desired format
        System.out.println("Population: " + String.format("%,d", regionPopulation.getTotalPopulation()));
    }


    // Method to retrieve population data for a specific country
    public List<Population> getCountryPopulationData(Connection con, String countryCode) throws SQLException {
        List<Population> populations = new ArrayList<>();

        // Define the SQL query with a placeholder for the country code
        String query = "SELECT SUM(country.Population) AS TotalPopulation, " +
                "(SELECT SUM(city.Population) FROM city) AS wholiveincities " +
                "FROM country " +
                "WHERE country.Code = ?";  // Use ? as a placeholder for country code

        // Use PreparedStatement to execute the query
        PreparedStatement stmt = con.prepareStatement(query);

        // Set the parameter for the country code
        stmt.setString(1, countryCode);  // Set the country code parameter (index 1)

        ResultSet rs = stmt.executeQuery(); // Execute the prepared statement

        // Initialize population variables
        long totalPopulation = 0;
        long wholiveincities = 0;

        // Process the result
        if (rs.next()) {
            totalPopulation = rs.getLong("TotalPopulation");
            wholiveincities = rs.getLong("wholiveincities");
        }

        rs.close();
        stmt.close();  // Always close the statement after use

        // Calculate the population not living in cities
        long whonotliveincities = totalPopulation - wholiveincities;

        // Add population data to the list
        populations.add(new Population(countryCode, totalPopulation, wholiveincities, whonotliveincities));

        return populations;
    }

    // Method to display the population of a specific country
    public void displayCountryPopulation(List<Population> populations, String countryCode) {
        if (populations == null || populations.isEmpty()) {
            System.out.println("No population data found for the country: " + countryCode);
            return;
        }

        // Get the population data for the country (assuming it's the first in the list)
        Population countryPopulation = populations.get(0);

        // Header for the country population report
        System.out.println(" 29 Population of " + countryCode + ":");

        // Display the total population in the desired format
        System.out.println("Population: " + String.format("%,d", countryPopulation.getTotalPopulation()));
    }
    // Method to retrieve population data for a specific district
    // Method to retrieve population data for a specific district
    public List<Population> getDistrictPopulationData(Connection con, String district) throws SQLException {
        List<Population> populations = new ArrayList<>();

        // Define the SQL query with a placeholder for the district name
        String query = "SELECT SUM(city.Population) AS TotalPopulation, " +
                "(SELECT SUM(city.Population) FROM city WHERE city.District = ?) AS wholiveincities " +
                "FROM city " +  // Use the city table for total population
                "WHERE city.District = ?"; // Use the correct column name for districts

        // Use PreparedStatement to execute the query
        PreparedStatement stmt = con.prepareStatement(query);

        // Set the parameter for the district name
        stmt.setString(1, district);  // Set the district parameter for wholiveincities (index 1)
        stmt.setString(2, district);  // Set the district parameter for TotalPopulation (index 2)

        ResultSet rs = stmt.executeQuery(); // Execute the prepared statement

        // Initialize population variables
        long totalPopulation = 0;
        long wholiveincities = 0;

        // Process the result
        if (rs.next()) {
            totalPopulation = rs.getLong("TotalPopulation");
            wholiveincities = rs.getLong("wholiveincities");
        }

        rs.close();
        stmt.close();  // Always close the statement after use

        // Calculate the population not living in cities
        long whonotliveincities = totalPopulation - wholiveincities;

        // Add population data to the list
        populations.add(new Population(district, totalPopulation, wholiveincities, whonotliveincities));

        return populations;
    }

    // Method to display the population of a specific district
    public void displayDistrictPopulation(List<Population> populations, String district) {
        if (populations == null || populations.isEmpty()) {
            System.out.println("No population data found for the district: " + district);
            return;
        }

        // Get the population data for the district (assuming it's the first in the list)
        Population districtPopulation = populations.get(0);

        // Header for the district population report
        System.out.println(" 30 Population of " + district + ":");

        // Display the total population in the desired format
        System.out.println("Population: " + String.format("%,d", districtPopulation.getTotalPopulation()));
    }
    // Method to retrieve population data for a specific city
    public List<Population> getCityPopulationData(Connection con, String cityName) throws SQLException {
        List<Population> populations = new ArrayList<>();

        // Define the SQL query with a placeholder for the city name
        String query = "SELECT Population AS TotalPopulation " +
                "FROM city " +
                "WHERE Name = ?"; // Use the correct column name for the city name

        // Use PreparedStatement to execute the query
        PreparedStatement stmt = con.prepareStatement(query);

        // Set the parameter for the city name
        stmt.setString(1, cityName);  // Set the city parameter (index 1)

        ResultSet rs = stmt.executeQuery(); // Execute the prepared statement

        // Initialize population variable
        long totalPopulation = 0;

        // Process the result
        if (rs.next()) {
            totalPopulation = rs.getLong("TotalPopulation");
        }

        rs.close();
        stmt.close();  // Always close the statement after use

        // Add population data to the list
        populations.add(new Population(cityName, totalPopulation, 0, 0)); // Assuming 0 for wholiveincities and whonotliveincities

        return populations;
    }

    // Method to display the population of a specific city
    public void displayCityPopulation(List<Population> populations, String cityName) {
        if (populations == null || populations.isEmpty()) {
            System.out.println("No population data found for the city: " + cityName);
            return;
        }

        // Get the population data for the city (assuming it's the first in the list)
        Population cityPopulation = populations.get(0);

        // Header for the city population report
        System.out.println(" 31 Population of " + cityName + ":");

        // Display the total population in the desired format
        System.out.println("Population: " + String.format("%,d", cityPopulation.getTotalPopulation()));
    }

    // Method to retrieve language population data
    // Method to retrieve language population data
    public List<languagepopulation> getLanguagePopulationData(Connection con) throws SQLException {
        List<languagepopulation> languagePopulations = new ArrayList<>();

        // Define the SQL query to get the number of speakers for each language
        String query = "SELECT cl.Language, SUM(c.Population * (cl.Percentage / 100)) AS TotalSpeakers " +
                "FROM countrylanguage cl " +
                "JOIN country c ON cl.CountryCode = c.Code " +
                "GROUP BY cl.Language " + // Group by Language
                "ORDER BY TotalSpeakers DESC;"; // Order by number of speakers from greatest to smallest

        // Use PreparedStatement to execute the query
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery(); // Execute the prepared statement

        // Total world population for percentage calculation
        long totalWorldPopulation = 7800000000L; // Example value, update it as necessary

        // Process the result
        while (rs.next()) {
            String language = rs.getString("Language");
            long totalSpeakers = rs.getLong("TotalSpeakers");

            // Calculate percentage of the world population
            double percentage = ((double) totalSpeakers / totalWorldPopulation) * 100;  // Ensure at least one operand is a double

            // Add the language data to the list
            languagePopulations.add(new languagepopulation(language, totalSpeakers, percentage));
        }

        rs.close();
        stmt.close();  // Always close the statement after use

        return languagePopulations;
    }




    // Method to display language populations
    // Method to display language populations
    public void displayLanguagePopulations(List<languagepopulation> languagePopulations) {
        if (languagePopulations == null || languagePopulations.isEmpty()) {
            System.out.println("No language population data found.");
            return;
        }

        // Header for the language population report
        System.out.println(" 32 Number of speakers for each language (from greatest to smallest):");

        // Display each language's population and percentage
        for (languagepopulation langPop : languagePopulations) {
            System.out.printf("Language: %s, Total Speakers: %,d, Percentage of World Population: %.2f%%\n",
                    langPop.getLanguage(), langPop.getTotalSpeakers(), langPop.getPercentage());
        }
    }

}

