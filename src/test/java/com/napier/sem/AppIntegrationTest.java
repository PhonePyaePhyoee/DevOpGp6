package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.SQLException;
import java.util.List;

public class AppIntegrationTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();

        // Connect to the database (using dynamic connection parameters)
        String location = "localhost:33060";  // Example location, adjust as necessary
        String dbName = "world";              // Example database name (adjust according to your DB)
        String username = "root";             // Example username
        String password = "example";          // Example password
        int delay = 2000;                     // 2-second delay between retries

        // Connect to the database
        app.connect(location, dbName, username, password, delay);
    }

    @Test
    void testGetCountryOrderedByPopulation() throws SQLException {
        // Assuming you have a method in the countryQuery class to fetch all countries ordered by population
        countryQuery countryQuery = new countryQuery();

        // Fetching all countries ordered by population
        List<Country> countries = countryQuery.getAllCountriesOrderedByPopulation(app.con);

        // Test that the top country by population is the expected one
        Country topCountry = countries.get(0);
        assertEquals("China", topCountry.getName());
        assertEquals(1444216107, topCountry.getPopulation());
    }

    @Test
    void testGetCityOrderedByPopulation() throws SQLException {
        // Assuming you have a method in the cityQuery class to fetch all cities ordered by population
        cityQuery cityQuery = new cityQuery();

        // Fetching all cities ordered by population
        List<City> cities = cityQuery.getAllCitiesOrderedByPopulation(app.con);

        // Test that the top city by population is the expected one
        City topCity = cities.get(0);
        assertEquals("Shanghai", topCity.getName());
        assertEquals(24183300, topCity.getPopulation());
    }

    @Test
    void testGetCountryByRegionOrderedByPopulation() throws SQLException {
        // Assuming you have a method to fetch countries in a region ordered by population
        countryQuery countryQuery = new countryQuery();
        String region = "Eastern Asia";  // Example region

        // Fetching countries in a region ordered by population
        List<Country> countriesByRegion = countryQuery.getCountriesByRegionOrderedByPopulation(app.con, region);

        // Test that the top country in the region by population is the expected one
        Country topCountryInRegion = countriesByRegion.get(0);
        assertEquals("China", topCountryInRegion.getName());
        assertEquals(1444216107, topCountryInRegion.getPopulation());
    }

    @Test
    void testTopNPopulatedCitiesInRegion() throws SQLException {
        // Assuming you have a method to fetch the top N cities in a region ordered by population
        cityQuery cityQuery = new cityQuery();
        String region = "Eastern Asia";  // Example region
        int N = 5;  // Example N value

        // Fetching the top N cities in a region ordered by population
        List<City> topCitiesInRegion = cityQuery.getTopNPopulatedCitiesInRegion(app.con, region, N);

        // Test the size of the result and check the first city
        assertEquals(5, topCitiesInRegion.size());
        City topCityInRegion = topCitiesInRegion.get(0);
        assertEquals("Shanghai", topCityInRegion.getName());
        assertEquals(24183300, topCityInRegion.getPopulation());
    }
}
