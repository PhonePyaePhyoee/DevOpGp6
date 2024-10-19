package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class countryQuery {

    // Method to retrieve all countries ordered by population (largest to smallest), including capital names
    public List<Country> getAllCountriesOrderedByPopulation(Connection con) throws SQLException {
        List<Country> countries = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name AS Capital " +
                "FROM country " +
                "LEFT JOIN city ON country.Capital = city.ID " + // Join city table to get the capital name
                "ORDER BY country.Population DESC"; // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);

        NumberFormat numberFormat = NumberFormat.getInstance();  // Formatter for population
        while (rs.next()) {
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            String continent = rs.getString("Continent");
            String region = rs.getString("Region");
            String capital = rs.getString("Capital"); // Get capital name
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population


            // Create a new Country object and add it to the list
            countries.add(new Country(code, name, continent, region, capital, population)); // Set the capital field

        }
        rs.close();

        return countries;  // Return the list of countries
    }
    public void displayAllCountriesOrderedByPopulation(List<Country> countries) {
        if (countries == null || countries.isEmpty()) {
            System.out.println("No Cities found");
            return;
        }
        System.out.println("\n No. 1 Country Report (All Countries by Population):");
        System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-25s | %-15s", "Code", "Name", "Continent", "Region", "Capital", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        for (Country country : countries) {
            System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-25s | %,15d", country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getCapital(), country.getPopulation()));
        }

    }

    // Method to retrieve all countries in a specific continent ordered by population (largest to smallest), including capital names
    public List<Country> getCountriesByContinentOrderedByPopulation(Connection con, String continent) throws SQLException {
        List<Country> countries = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name AS Capital " +
                "FROM country " +
                "LEFT JOIN city ON country.Capital = city.ID " + // Join city table to get the capital name
                "WHERE country.Continent = '" + continent + "' " +  // Filter by continent
                "ORDER BY country.Population DESC"; // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);


        NumberFormat numberFormat = NumberFormat.getInstance();  // Formatter for population
        while (rs.next()) {
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            String region = rs.getString("Region");
            String capital = rs.getString("Capital"); // Get capital name
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            // Create a new Country object and add it to the list
            countries.add(new Country(code, name, continent, region, capital, population));
        }
        rs.close();

        return countries;  // Return the list of countries
    }

    public void displayCountriesByContinentOrderedByPopulation(List<Country> countries, String continent) {
        if (countries == null || countries.isEmpty()) {
            System.out.println("No Cities found");
        }
        System.out.println("\n No. 2 Country Report (Countries in " + continent + " by Population):");
        System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-25s | %-15s", "Code", "Name", "Continent", "Region", "Capital", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        for (Country country : countries) {
            System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-25s | %,15d", country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getCapital(), country.getPopulation()));
        }



    }

    // Method to retrieve all countries in a specific region ordered by population (largest to smallest), including capital names
    public List<Country> getCountriesByRegionOrderedByPopulation(Connection con, String region) throws SQLException {
        List<Country> countries = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name AS Capital " +
                "FROM country " +
                "LEFT JOIN city ON country.Capital = city.ID " + // Join city table to get the capital name
                "WHERE country.Region = '" + region + "' " +      // Filter by region
                "ORDER BY country.Population DESC";               // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);


        NumberFormat numberFormat = NumberFormat.getInstance();  // Formatter for population
        while (rs.next()) {
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            String continent = rs.getString("Continent");
            String capital = rs.getString("Capital"); // Get capital name
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            countries.add(new Country(code, name, continent, region, capital, population));
        }
        rs.close();

        return countries;  // Return the list of countries
    }


    public void displayCountriesByRegionOrderedByPopulation(List<Country> countries, String region) {
        if (countries == null || countries.isEmpty()) {
            System.out.println("No Cities found");
        }
        System.out.println("\n No. 3 Country Report (Countries in " + region + " by Population):");
        System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-25s | %-15s", "Code", "Name", "Continent", "Region", "Capital", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        for (Country country : countries) {
            System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-25s | %,15d", country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getCapital(), country.getPopulation()));
        }



    }
    // Method to retrieve the top N populated countries in the world, including capital names
    public List<Country> getTopNPopulatedCountriesInWorld(Connection con, int N) throws SQLException {
        List<Country> countries = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name AS Capital " +
                "FROM country " +
                "LEFT JOIN city ON country.Capital = city.ID " + // Join city table to get the capital name
                "ORDER BY country.Population DESC " +           // Order by population from largest to smallest
                "LIMIT " + N;                                   // Limit to the top N countries
        ResultSet rs = stmt.executeQuery(query);


        NumberFormat numberFormat = NumberFormat.getInstance();  // Formatter for population
        while (rs.next()) {
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            String continent = rs.getString("Continent");
            String region = rs.getString("Region");
            String capital = rs.getString("Capital"); // Get capital name
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            countries.add(new Country(code, name, continent, region, capital, population));
        }
        rs.close();

        return countries;  // Return the list of top N populated countries in the world
    }
    public void displayTopNPopulatedCountriesInWorld(List<Country> countries, int N) throws SQLException {
        if (countries == null || countries.isEmpty()) {
            System.out.println("No Countries found");
        }
        System.out.println("\n No. 4 Top " + N + " Populated Countries in the World:");
        System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-25s | %-15s", "Code", "Name", "Continent", "Region", "Capital", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        for (Country country : countries) {
            System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-25s | %,15d", country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getCapital(), country.getPopulation()));
        }

    }



}
