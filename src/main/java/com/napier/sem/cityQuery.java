package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class cityQuery {

    // Method to retrieve all cities in the world ordered by population (largest to smallest)
    public List<City> getAllCitiesOrderedByPopulation(Connection con) throws SQLException {
        List<City> cities = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " + // Join to get country names
                "ORDER BY city.Population DESC"; // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\n No. 7 City Report (All Cities by Population):");
        System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", "City ID", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", cityID, cityName, countryName, district, populationFormatted));
        }
        rs.close();

        return cities;
    }

    // Method to retrieve all cities in a continent ordered by population (largest to smallest)
    public List<City> getCitiesByContinentOrderedByPopulation(Connection con, String continent) throws SQLException {
        List<City> cities = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + continent + "' " + // Filter by continent
                "ORDER BY city.Population DESC"; // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\nNo. 8 City Report (Cities in " + continent + " by Population):");
        System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", "City ID", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", cityID, cityName, countryName, district, populationFormatted));
        }
        rs.close();

        return cities;
    }

    // Method to retrieve all cities in a region ordered by population (largest to smallest)
    public List<City> getCitiesByRegionOrderedByPopulation(Connection con, String region) throws SQLException {
        List<City> cities = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + region + "' " + // Filter by region
                "ORDER BY city.Population DESC"; // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\nNo. 9 City Report (Cities in " + region + " by Population):");
        System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", "City ID", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", cityID, cityName, countryName, district, populationFormatted));
        }
        rs.close();

        return cities;
    }

    // Method to retrieve all cities in a country ordered by population (largest to smallest)
    public List<City> getCitiesByCountryOrderedByPopulation(Connection con, String country) throws SQLException {
        List<City> cities = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + country + "' " + // Filter by country name
                "ORDER BY city.Population DESC"; // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\nNo. 10 City Report (Cities in " + country + " by Population):");
        System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", "City ID", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", cityID, cityName, countryName, district, populationFormatted));
        }
        rs.close();

        return cities;
    }

    // Method to retrieve all cities in a district ordered by population (largest to smallest)
    public List<City> getCitiesByDistrictOrderedByPopulation(Connection con, String district) throws SQLException {
        List<City> cities = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = '" + district + "' " + // Filter by district name
                "ORDER BY city.Population DESC"; // Order by population from largest to smallest
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\nNo. 11 City Report (Cities in " + district + " District by Population):");
        System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", "City ID", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String districtName = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", cityID, cityName, countryName, districtName, populationFormatted));
        }
        rs.close();

        return cities;
    }

    // Method to retrieve the top N populated cities in a continent (default continent and N)
    public List<City> getTopNPopulatedCitiesInContinent(Connection con) throws SQLException {
        // Set default values for continent and N
        String defaultContinent = "Asia";  // Default continent
        int defaultN = 5;                  // Default number of top cities

        List<City> cities = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + defaultContinent + "' " + // Filter by default continent
                "ORDER BY city.Population DESC " + // Order by population from largest to smallest
                "LIMIT " + defaultN;  // Limit the result to default N cities
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\nNo.13 Top " + defaultN + " Cities in " + defaultContinent + " by Population:");
        System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", "City ID", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", cityID, cityName, countryName, district, populationFormatted));
        }
        rs.close();

        return cities;
    }

    // Method to retrieve the top N populated cities in the world (user-defined N)
    public List<City> getTopNPopulatedCitiesInWorld(Connection con, int N) throws SQLException {
        List<City> cities = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC " + // Order by population from largest to smallest
                "LIMIT " + N;  // Limit the result to user-defined N cities
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\n No.12 Top " + N + " Populated Cities in the World:");
        System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", "City ID", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", cityID, cityName, countryName, district, populationFormatted));
        }
        rs.close();

        return cities;

    }
    public List<City> getTopNPopulatedCitiesInRegion(Connection con) throws SQLException {
        // Set default values for region and N
        String defaultRegion = "Eastern Asia";  // Default region
        int defaultN = 5;                        // Default number of top cities

        List<City> cities = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + defaultRegion + "' " + // Filter by default region
                "ORDER BY city.Population DESC " + // Order by population from largest to smallest
                "LIMIT " + defaultN;  // Limit the result to default N cities
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\nNo. 14 Top " + defaultN + " Populated Cities in " + defaultRegion + " by Population:");
        System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", "City ID", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", cityID, cityName, countryName, district, populationFormatted));
        }
        rs.close();

        return cities;

    }
    // Method to retrieve the top N populated cities in a country with default values
    public List<City> getTopNPopulatedCitiesInCountry(Connection con) throws SQLException {
        // Set default values for country and N
        String defaultCountry = "China";  // Default country
        int defaultN = 5;                 // Default number of top cities

        List<City> cities = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + defaultCountry + "' " + // Filter by default country
                "ORDER BY city.Population DESC " + // Order by population from largest to smallest
                "LIMIT " + defaultN;  // Limit the result to default N cities
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\nNo. 15 Top " + defaultN + " Populated Cities in " + defaultCountry + " by Population:");
        System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", "City ID", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", cityID, cityName, countryName, district, populationFormatted));
        }
        rs.close();

        return cities;
    }
    public List<City> getTopNPopulatedCitiesInDistrict(Connection con) throws SQLException {
        // Set default values for district and N
        String defaultDistrict = "Shandong";  // Default district
        int defaultN = 5;                      // Default number of top cities

        List<City> cities = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = '" + defaultDistrict + "' " + // Filter by default district
                "ORDER BY city.Population DESC " + // Order by population from largest to smallest
                "LIMIT " + defaultN;  // Limit the result to default N cities
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\nNo. 16 Top " + defaultN + " Populated Cities in " + defaultDistrict + " by Population:");
        System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", "City ID", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            System.out.println(String.format("%-10s | %-40s | %-40s | %-25s | %-15s", cityID, cityName, countryName, district, populationFormatted));
        }
        rs.close();

        return cities;
    }
}


