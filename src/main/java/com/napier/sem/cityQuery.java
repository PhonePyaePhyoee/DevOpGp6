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

        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population);
            cities.add(new City(cityName, countryName, district, population));
        }
        rs.close();
        return cities;
    }

    public void displayAllCitiesOrderedByPopulation(List<City> cities) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No Cities found");
            return;
        }
        System.out.println("\n No. 7 City Report (All Cities by Population):");
        System.out.println(String.format("%-40s | %-40s | %-25s | %-15s", "City Name", "Country Name", "District", "Population"));
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (City city : cities) {
            System.out.println(String.format("%-40s | %-40s | %-25s | %,15d", city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation()));
        }
    }

    // Method to retrieve all cities in a continent ordered by population (largest to smallest)
    public List<City> getCitiesByContinentOrderedByPopulation(Connection con, String continent) throws SQLException {
        List<City> cities = new ArrayList<>();

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC";
        ResultSet rs = stmt.executeQuery(query);


        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population);
            cities.add(new City(cityName, countryName, district, population));

        }
        rs.close();

        return cities;
    }

    public void displayCitiesByContinentOrderedByPopulation(List<City> cities, String continent) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No Cities found");
            return;
        }
        System.out.println("\nNo. 8 City Report (Cities in " + continent + " by Population):");
        System.out.println(String.format("%-40s | %-40s | %-25s | %-15s", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.println(String.format("%-40s | %-40s | %-25s | %,15d", city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation()));
        }
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


        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population
            cities.add(new City(cityName, countryName, district, population));

        }
        rs.close();

        return cities;
    }

    public void displayCitiesByRegionOrderedByPopulation(List<City> cities, String region) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No Cities found");
            return;
        }
        System.out.println("\nNo. 9 City Report (Cities in " + region + " by Population):");
        System.out.println(String.format(" %-40s | %-40s | %-25s | %-15s", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.println(String.format("%-40s | %-40s | %-25s | %,15d", city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation()));
        }
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


        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population
            cities.add(new City(cityName, countryName, district, population));
        }
        rs.close();

        return cities;
    }

    public void displayCitiesByCountryOrderedByPopulation(List<City> cities, String country) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No Cities found");
            return;
        }
        System.out.println("\nNo. 10 City Report (Cities in " + country + " by Population):");
        System.out.println(String.format(" %-40s | %-40s | %-25s | %-15s", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.println(String.format("%-40s | %-40s | %-25s | %,15d", city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation()));
        }
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


        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String districtName = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population
            cities.add(new City(cityName, countryName, district, population));
        }
        rs.close();

        return cities;
    }

    public void displayCitiesByDistrictOrderedByPopulation(List<City> cities, String district) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No Cities found");
            return;
        }
        System.out.println("\nNo. 11 City Report (Cities in " + district + " District by Population):");
        System.out.println(String.format(" %-40s | %-40s | %-25s | %-15s", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.println(String.format("%-40s | %-40s | %-25s | %,15d", city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation()));
        }
    }


    // Method to retrieve the top N populated cities in the world (user-defined N)
    public List<City> getTopNPopulatedCitiesInWorld(Connection con, int N) throws SQLException {
        List<City> cities = new ArrayList<>();

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + N;
        ResultSet rs = stmt.executeQuery(query);


        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population);
            cities.add(new City(cityName, countryName, district, population));
        }
        rs.close();

        return cities;
    }

    public void displayTopNPopulatedCitiesInWorld(List<City> cities, int N) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No Cities found");
            return;
        }
        System.out.println("\n No.12 Top " + N + " Populated Cities in the World:");
        System.out.println(String.format(" %-40s | %-40s | %-25s | %-15s", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.println(String.format("%-40s | %-40s | %-25s | %,15d", city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation()));
        }
    }

    // Method to retrieve the top N populated cities in a continent (default continent and N)
    public List<City> getTopNPopulatedCitiesInContinent(Connection con, String continent, int N) throws SQLException {
        // Set default values for continent and N
        // Default number of top cities

        List<City> cities = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + continent + "' " + // Filter by default continent
                "ORDER BY city.Population DESC " + // Order by population from largest to smallest
                "LIMIT " + N;  // Limit the result to default N cities
        ResultSet rs = stmt.executeQuery(query);


        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population
            cities.add(new City(cityName, countryName, district, population));

        }
        rs.close();

        return cities;
    }
    public void displayTopNPopulatedCitiesInContinent(List<City> cities,String continent, int N) {
        // Default number of top cities
        if (cities == null || cities.isEmpty()) {
            System.out.println("No Cities found");
            return;
        }
        System.out.println("\nNo.13 Top " + N + " Cities in " + continent + " by Population:");
        System.out.println(String.format(" %-40s | %-40s | %-25s | %-15s", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.println(String.format("%-40s | %-40s | %-25s | %,15d", city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation()));
        }
    }

    public List<City> getTopNPopulatedCitiesInRegion(Connection con, String region, int N) throws SQLException {
        // Set default values for region and N

        List<City> cities = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + region + "' " + // Filter by default region
                "ORDER BY city.Population DESC " + // Order by population from largest to smallest
                "LIMIT " + N;  // Limit the result to default N cities
        ResultSet rs = stmt.executeQuery(query);


        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population
            cities.add(new City(cityName, countryName, district, population));

        }
        rs.close();

        return cities;
    }

    public void displayTopNPopulatedCitiesInRegion(List<City> cities,String region, int N) {
        // Default number of top cities
        if (cities == null || cities.isEmpty()) {
            System.out.println("No Cities found");
            return;
        }
        System.out.println("\nNo. 14 Top " + N + " Populated Cities in " + region + " by Population:");
        System.out.println(String.format(" %-40s | %-40s | %-25s | %-15s", "City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.println(String.format("%-40s | %-40s | %-25s | %,15d", city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation()));
        }
    }
    // Method to retrieve the top N populated cities in a country with default values
    public List<City> getTopNPopulatedCitiesInCountry(Connection con,String country,int N) throws SQLException {
        // Set default values for country and N


        List<City> cities = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + country + "' " + // Filter by default country
                "ORDER BY city.Population DESC " + // Order by population from largest to smallest
                "LIMIT " + N;  // Limit the result to default N cities
        ResultSet rs = stmt.executeQuery(query);
        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            String district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population
            cities.add(new City(cityName, countryName, district, population));
        }
        rs.close();

        return cities;
    }
    public void displayTopNPopulatedCitiesInCountry(List<City> cities,String country, int N) {
        // Default number of top cities
        if (cities == null || cities.isEmpty()) {
            System.out.println("No Cities found");
            return;
        }
        System.out.println("\nNo. 15 Top " + N + " Populated Cities in " + country + " by Population:");
        System.out.println(String.format("%-40s | %-40s | %-25s | %-15s","City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.println(String.format("%-40s | %-40s | %-25s | %,15d", city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation()));
        }
    }
    public List<City> getTopNPopulatedCitiesInDistrict(Connection con,String district, int N) throws SQLException {


        List<City> cities = new ArrayList<>(); // List to store the result

        Statement stmt = con.createStatement();
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = '" + district + "' " + // Filter by default district
                "ORDER BY city.Population DESC " + // Order by population from largest to smallest
                "LIMIT " + N;  // Limit the result to default N cities
        ResultSet rs = stmt.executeQuery(query);


        NumberFormat numberFormat = NumberFormat.getInstance();
        while (rs.next()) {
            int cityID = rs.getInt("ID");
            String cityName = rs.getString("Name");
            String countryName = rs.getString("CountryName");
            district = rs.getString("District");
            int population = rs.getInt("Population");
            String populationFormatted = numberFormat.format(population); // Format population

            cities.add(new City(cityName, countryName, district, population));
        }
        rs.close();

        return cities;
    }
    public void displayTopNPopulatedCitiesInDistrict(List<City> cities,String district, int N) {
        // Default number of top cities
        if (cities == null || cities.isEmpty()) {
            System.out.println("No Cities found");
            return;
        }
        System.out.println("\nNo. 16 Top " + N + " Populated Cities in " + district + " by Population:");
        System.out.println(String.format("%-40s | %-40s | %-25s | %-15s","City Name", "Country Name", "District", "Population"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.println(String.format("%-40s | %-40s | %-25s | %,15d", city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation()));
        }
    }
}


