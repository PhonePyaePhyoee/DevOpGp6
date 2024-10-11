package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class capitalQuery {

    // Method to retrieve all capital cities in the world ordered by population (largest to smallest)
    public List<Capital> getAllCapitalCitiesOrderedByPopulation(Connection con) throws SQLException {
        List<Capital> capitals = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " + // Join to get country names
                "ORDER BY city.Population DESC"; // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\n No. 17 Capital City Report (All Capitals by Population):");
        System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", "City ID", "Capital Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String capitalName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", cityID, capitalName, countryName, district, populationFormatted));
        }
        rs.close();

        return capitals;
    }
    // Method to retrieve all capital cities in a continent ordered by population (largest to smallest)
    public List<Capital> getCapitalCitiesByContinentOrderedByPopulation(Connection con, String continent) throws SQLException {
        List<Capital> capitals = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " + // Join to get country names
                "WHERE country.Continent = '" + continent + "' " + // Filter by continent
                "ORDER BY city.Population DESC"; // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\n No. 18 Capital City Report (Capital Cities in " + continent + " by Population):");
        System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", "City ID", "Capital Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String capitalName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", cityID, capitalName, countryName, district, populationFormatted));
        }
        rs.close();
        return capitals;
    }
    public List<Capital> getCapitalCitiesByRegionOrderedByPopulation(Connection con, String region) throws SQLException {
        List<Capital> capitals = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = '" + region + "' " + // Filter by region
                "ORDER BY city.Population DESC"; // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\n No. 19 Capital City Report (Capital Cities in " + region + " by Population):");
        System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", "City ID", "Capital Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String capitalName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", cityID, capitalName, countryName, district, populationFormatted));
        }
        rs.close();

        return capitals;
    }
    // Method to retrieve the top N populated capital cities in the world
    public List<Capital> getTopNPopulatedCapitalCitiesInWorld(Connection con, int N) throws SQLException {
        List<Capital> capitals = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " + // Join to get country names
                "ORDER BY city.Population DESC " + // Order by population from largest to smallest
                "LIMIT " + N;  // Limit the result to top N cities
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\n No. 20 Top " + N + " Populated Capital Cities in the World:");
        System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", "City ID", "Capital Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String capitalName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", cityID, capitalName, countryName, district, populationFormatted));
        }
        rs.close();

        return capitals;
    }
}
