package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class countryQuery {

    // Method to retrieve all countries ordered by population (largest to smallest)
    public List<Country> getAllCountriesOrderedByPopulation(Connection con) throws SQLException {
        List<Country> countries = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population " +
                "FROM country " +
                "ORDER BY country.Population DESC"; // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\n No. 1 Country Report (All Countries by Population):");
        System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s", "Code", "Name", "Continent", "Region", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();  // Formatter for population
        while (rs.next()) {
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            String continent = rs.getString("Continent");
            String region = rs.getString("Region");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s", code, name, continent, region, populationFormatted));

            // Create a new Country object and add it to the list
            // Create a new Country object and add it to the list
            Country country = new Country(code, name, continent, region, "", population); // Set Capital to an empty string
            countries.add(country);

        }
        rs.close();

        return countries;  // Return the list of countries
    }
    // Method to retrieve all countries in a specific continent ordered by population (largest to smallest)
    public List<Country> getCountriesByContinentOrderedByPopulation(Connection con, String continent) throws SQLException {
        List<Country> countries = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population " +
                "FROM country " +
                "WHERE country.Continent = '" + continent + "' " +  // Filter by continent
                "ORDER BY country.Population DESC"; // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\n No. 2 Country Report (Countries in " + continent + " by Population):");
        System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s", "Code", "Name", "Continent", "Region", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();  // Formatter for population
        while (rs.next()) {
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            String region = rs.getString("Region");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s", code, name, continent, region, populationFormatted));

            // Create a new Country object and add it to the list
            Country country = new Country(code, name, continent, region, "", population); // Set Capital to an empty string
            countries.add(country);
        }
        rs.close();

        return countries;  // Return the list of countries
    }
    // Method to retrieve all countries in a specific region ordered by population (largest to smallest)
    public List<Country> getCountriesByRegionOrderedByPopulation(Connection con, String region) throws SQLException {
        List<Country> countries = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population " +
                "FROM country " +
                "WHERE country.Region = '" + region + "' " +  // Filter by region
                "ORDER BY country.Population DESC"; // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\n No. 3 Country Report (Countries in " + region + " by Population):");
        System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s", "Code", "Name", "Continent", "Region", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();  // Formatter for population
        while (rs.next()) {
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            String continent = rs.getString("Continent");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s", code, name, continent, region, populationFormatted));

            // Create a new Country object and add it to the list
            Country country = new Country(code, name, continent, region, "", population); // Set Capital to an empty string
            countries.add(country);
        }
        rs.close();

        return countries;  // Return the list of countries
    }
    // Method to retrieve the top N populated countries in a specific region
    public List<Country> getTopNPopulatedCountriesInRegion(Connection con, String region, int N) throws SQLException {
        List<Country> countries = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population " +
                "FROM country " +
                "WHERE country.Region = '" + region + "' " +  // Filter by region
                "ORDER BY country.Population DESC " +         // Order by population from largest to smallest
                "LIMIT " + N;                                 // Limit the results to top N countries
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\n No. 6 Top " + N + " Populated Countries in " + region + " by Population:");
        System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s", "Code", "Name", "Continent", "Region", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();  // Formatter for population
        while (rs.next()) {
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            String continent = rs.getString("Continent");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s", code, name, continent, region, populationFormatted));

            // Create a new Country object and add it to the list
            Country country = new Country(code, name, continent, region, "", population); // Set Capital to an empty string
            countries.add(country);
        }
        rs.close();

        return countries;  // Return the list of top N populated countries in the region
    }
    // Method to retrieve the top N populated countries in the world
    public List<Country> getTopNPopulatedCountriesInWorld(Connection con, int N) throws SQLException {
        List<Country> countries = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population " +
                "FROM country " +
                "ORDER BY country.Population DESC " +  // Order by population from largest to smallest
                "LIMIT " + N;                          // Limit to the top N countries
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\n No. 4 Top " + N + " Populated Countries in the World:");
        System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s", "Code", "Name", "Continent", "Region", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();  // Formatter for population
        while (rs.next()) {
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            String continent = rs.getString("Continent");
            String region = rs.getString("Region");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s", code, name, continent, region, populationFormatted));

            // Create a new Country object and add it to the list
            Country country = new Country(code, name, continent, region, "", population); // Set Capital to an empty string
            countries.add(country);
        }
        rs.close();

        return countries;  // Return the list of top N populated countries in the world
    }
    // Method to retrieve the top N populated countries in a specific continent
    public List<Country> getTopNPopulatedCountriesInContinent(Connection con, String continent, int N) throws SQLException {
        List<Country> countries = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population " +
                "FROM country " +
                "WHERE country.Continent = '" + continent + "' " +  // Filter by continent
                "ORDER BY country.Population DESC " +               // Order by population from largest to smallest
                "LIMIT " + N;                                       // Limit the result to top N countries
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\n No. 5 Top " + N + " Populated Countries in " + continent + ":");
        System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s", "Code", "Name", "Continent", "Region", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();  // Formatter for population
        while (rs.next()) {
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            String region = rs.getString("Region");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s", code, name, continent, region, populationFormatted));

            // Create a new Country object and add it to the list
            Country country = new Country(code, name, continent, region, "", population); // Set Capital to an empty string
            countries.add(country);
        }
        rs.close();

        return countries;  // Return the list of top N populated countries in the continent
    }
}
