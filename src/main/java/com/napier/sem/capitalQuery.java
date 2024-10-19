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

        NumberFormat numberFormat = NumberFormat.getInstance();

        if (rs.next()) {
            while (rs.next()) {
                int cityID = rs.getInt("ID");
                String capitalName = rs.getString("Name");
                String countryName = rs.getString("CountryName");
                String district = rs.getString("District");
                int population = rs.getInt("Population");
                String populationFormatted = numberFormat.format(population); // Format population

                capitals.add(new Capital(capitalName, countryName, population));
            }
            rs.close();

            return capitals;
        } else {
            return null;
        }

    }

    public void displayAllCapitalCitiesOrderedByPopulation(List<Capital> capitals) {
        if (capitals == null || capitals.isEmpty()) {
            System.out.println("No capital cities found.");
            return;
        }

        System.out.println("\nNo. 17 Capital City Report (All Capitals by Population):");
        System.out.println(String.format("%-40s | %-40s | %-15s", "Capital Name", "Country Name", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        // Iterate over the capitals and display the information
        for (Capital capital : capitals) {
            System.out.println(String.format("%-40s | %-40s | %,15d", capital.getName(), capital.getCountry(), capital.getPopulation()));
        }
    }

    //#Test Method to retrieve all capital cities in a continent ordered by population (largest to smallest)
    public List<Capital> getCapitalCitiesByContinentOrderedByPopulation(Connection con, String continent) throws SQLException {
        List<Capital> capitals = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " + // Join to get country names
                "WHERE country.Continent = '" + continent + "' " + // Filter by continent
                "ORDER BY city.Population DESC"; // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);

        NumberFormat numberFormat = NumberFormat.getInstance();
        if (rs.next()) {
            while (rs.next()) {
                int cityID = rs.getInt("ID");
                String capitalName = rs.getString("Name");
                String countryName = rs.getString("CountryName");
                String district = rs.getString("District");
                int population = rs.getInt("Population");
                String populationFormatted = numberFormat.format(population); // Format population

                capitals.add(new Capital(capitalName, countryName, population));
            }
            rs.close();
            return capitals;
        } else {
            return null;
        }
    }
    public void displayCapitalCitiesByContinentOrderedByPopulation(List<Capital> capitals,String continent)
    {
        if(capitals== null || capitals.isEmpty())
        {
            System.out.println("No capital found");
            return;
        }


            List<Capital> result = capitals;
            System.out.println("\n No. 18 Capital City Report (Capital Cities in " + continent + " by Population):");
            System.out.println(String.format("%-40s | %-40s | %-15s", "Capital Name", "Country Name", "Population"));
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

            for(Capital capital : result)
            {
                System.out.println(String.format("%-40s | %-40s | %,15d", capital.getName(), capital.getCountry(), capital.getPopulation()));
            }
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

        NumberFormat numberFormat = NumberFormat.getInstance();
        if (rs.next()) {
            while (rs.next()) {
                String capitalName = rs.getString("Name");
                String countryName = rs.getString("CountryName");
                int population = rs.getInt("Population");
                String populationFormatted = numberFormat.format(population);

                capitals.add(new Capital(capitalName, countryName, population));
            }
            rs.close();

            return capitals;
        }
        else {
            return null;
        }
    }
    public void displayCapitalCitiesByRegionOrderedByPopulation(List<Capital> capitals, String region) {
        if (capitals == null) {
            System.out.println("No capital cities found in the region.");
            return;
        }
        List<Capital> result = capitals;
        System.out.println("\nNo. 19 Capital City Report (Capital Cities in " + region + " by Population):");
        System.out.println(String.format("%-40s | %-40s | %-15s", "Capital Name", "Country Name", "Population"));
        System.out.println("-------------------------------------------------------------------------------------");

        for (Capital capital : result) {
            System.out.println(String.format("%-40s | %-40s | %,15d", capital.getName(), capital.getCountry(), capital.getPopulation()));
        }
    }

    // Method to retrieve the top N populated capital cities in the world
    public List<Capital> getTopNPopulatedCapitalCitiesInWorld(Connection con, int N) throws SQLException {
        List<Capital> capitals = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " + // Join to get country names
                "ORDER BY city.Population DESC " + // Order by population from largest to smallest
                "LIMIT " + N;  // Limit the result to top N cities
        ResultSet rs = stmt.executeQuery(query);

        NumberFormat numberFormat = NumberFormat.getInstance();
        if (rs.next()) {
            while (rs.next()) {
                String capitalName = rs.getString("Name");
                String countryName = rs.getString("CountryName");
                int population = rs.getInt("Population");
                String populationFormatted = numberFormat.format(population);

                capitals.add(new Capital(capitalName, countryName, population));
            }
            rs.close();

            return capitals;
        } else {
            return null;
        }
    }
    public void displayTopNPopulatedCapitalCitiesInWorld(List<Capital> capitals, int N) {
        if (capitals == null || capitals.isEmpty()) {
            System.out.println("No capital cities found.");
            return;
        }

        System.out.println("\nNo. 20 Top " + N + " Populated Capital Cities in the World:");
        System.out.println(String.format("%-40s | %-40s | %-15s", "Capital Name", "Country Name", "Population"));
        System.out.println("-------------------------------------------------------------------------------------");

        for (Capital capital : capitals) {
            System.out.println(String.format("%-40s | %-40s | %,15d", capital.getName(), capital.getCountry(), capital.getPopulation()));
        }
    }
    // Method to retrieve the top N populated capital cities in a specific continent
    public List<Capital> getTopNPopulatedCapitalCitiesInContinent(Connection con, String continent, int N) throws SQLException {
        List<Capital> capitals = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +  // Join to get country names
                "WHERE country.Continent = '" + continent + "' " + // Filter by continent
                "ORDER BY city.Population DESC " +  // Order by population from largest to smallest
                "LIMIT " + N;  // Limit the result to top N cities
        ResultSet rs = stmt.executeQuery(query);

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            String capitalName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population);

            capitals.add(new Capital(capitalName, countryName, population));
        }
        rs.close();

        return capitals;  // Return the list of top N populated capital cities in the continent
    }

    // Method to display the top N populated capital cities in a specific continent
    public void displayTopNPopulatedCapitalCitiesInContinent(List<Capital> capitals, String continent, int N) {
        if (capitals == null || capitals.isEmpty()) {
            System.out.println("No capital cities found.");
            return;
        }

        System.out.println("\nNo.21 Top " + N + " Populated Capital Cities in " + continent + ":");
        System.out.println(String.format("%-40s | %-40s | %-15s", "Capital Name", "Country Name", "Population"));
        System.out.println("-------------------------------------------------------------------------------------");

        for (Capital capital : capitals) {
            System.out.println(String.format("%-40s | %-40s | %,15d", capital.getName(), capital.getCountry(), capital.getPopulation()));
        }
    }
    // Method to retrieve the top N populated capital cities in a specific region
    public List<Capital> getTopNPopulatedCapitalCitiesInRegion(Connection con, String region, int N) throws SQLException {
        List<Capital> capitals = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +  // Join to get country names
                "WHERE country.Region = '" + region + "' " + // Filter by region
                "ORDER BY city.Population DESC " +  // Order by population from largest to smallest
                "LIMIT " + N;  // Limit the result to top N cities
        ResultSet rs = stmt.executeQuery(query);

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            String capitalName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population);

            capitals.add(new Capital(capitalName, countryName, population));
        }
        rs.close();

        return capitals;  // Return the list of top N populated capital cities in the region
    }

    // Method to display the top N populated capital cities in a specific region
    public void displayTopNPopulatedCapitalCitiesInRegion(List<Capital> capitals, String region, int N) {
        if (capitals == null || capitals.isEmpty()) {
            System.out.println("No capital cities found.");
            return;
        }

        System.out.println("\nNo.22 Top " + N + " Populated Capital Cities in " + region + ":");
        System.out.println(String.format("%-40s | %-40s | %-15s", "Capital Name", "Country Name", "Population"));
        System.out.println("-------------------------------------------------------------------------------------");

        for (Capital capital : capitals) {
            System.out.println(String.format("%-40s | %-40s | %,15d", capital.getName(), capital.getCountry(), capital.getPopulation()));
        }
    }

}
