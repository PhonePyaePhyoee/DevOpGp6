package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class citygetUnitTest {

    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;
    private cityQuery cityQueryInstance;

    @BeforeEach
    public void setup() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        // Stub the connection to return the mock statement
        when(mockConnection.createStatement()).thenReturn(mockStatement);

        // Create an instance of cityQuery to test
        cityQueryInstance = new cityQuery();
    }

    @Test
    public void testGetAllCitiesOrderedByPopulation() throws SQLException {
        // Stub the ResultSet to return mock data
        when(mockStatement.executeQuery(Mockito.anyString())).thenReturn(mockResultSet);

        // Simulate data in ResultSet
        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false); // Simulate two rows of data
        when(mockResultSet.getString("Name")).thenReturn("CityA").thenReturn("CityB");
        when(mockResultSet.getString("CountryName")).thenReturn("CountryA").thenReturn("CountryB");
        when(mockResultSet.getString("District")).thenReturn("DistrictA").thenReturn("DistrictB");
        when(mockResultSet.getInt("Population")).thenReturn(1000000).thenReturn(500000);

        // Call the method to test
        List<City> cities = cityQueryInstance.getAllCitiesOrderedByPopulation(mockConnection);

        // Validate the results
        assertEquals(2, cities.size());

        City city1 = cities.get(0);
        assertEquals("CityA", city1.getName());
        assertEquals("CountryA", city1.getCountry());
        assertEquals("DistrictA", city1.getDistrict());
        assertEquals(1000000, city1.getPopulation());

        City city2 = cities.get(1);
        assertEquals("CityB", city2.getName());
        assertEquals("CountryB", city2.getCountry());
        assertEquals("DistrictB", city2.getDistrict());
        assertEquals(500000, city2.getPopulation());

        // Verify that close() was called on the ResultSet
        verify(mockResultSet).close();
    }

    @Test
    public void testGetCitiesByContinentOrderedByPopulation() throws SQLException {
        // Stub the ResultSet to return mock data
        when(mockStatement.executeQuery(Mockito.anyString())).thenReturn(mockResultSet);

        // Simulate data in ResultSet
        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // One row of data
        when(mockResultSet.getString("Name")).thenReturn("CityA");
        when(mockResultSet.getString("CountryName")).thenReturn("CountryA");
        when(mockResultSet.getString("District")).thenReturn("DistrictA");
        when(mockResultSet.getInt("Population")).thenReturn(1000000);

        // Call the method to test
        List<City> cities = cityQueryInstance.getCitiesByContinentOrderedByPopulation(mockConnection, "Asia");

        // Validate the result
        assertEquals(1, cities.size());
        City city = cities.get(0);
        assertEquals("CityA", city.getName());
        assertEquals("CountryA", city.getCountry());
        assertEquals("DistrictA", city.getDistrict());
        assertEquals(1000000, city.getPopulation());

        // Verify that close() was called on the ResultSet
        verify(mockResultSet).close();
    }

    @Test
    public void testGetCitiesByRegionOrderedByPopulation() throws SQLException {
        // Stub the ResultSet to return mock data
        when(mockStatement.executeQuery(Mockito.anyString())).thenReturn(mockResultSet);

        // Simulate data in ResultSet
        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // One row of data
        when(mockResultSet.getString("Name")).thenReturn("CityA");
        when(mockResultSet.getString("CountryName")).thenReturn("CountryA");
        when(mockResultSet.getString("District")).thenReturn("DistrictA");
        when(mockResultSet.getInt("Population")).thenReturn(1000000);

        // Call the method to test
        List<City> cities = cityQueryInstance.getCitiesByRegionOrderedByPopulation(mockConnection, "East Asia");

        // Validate the result
        assertEquals(1, cities.size());
        City city = cities.get(0);
        assertEquals("CityA", city.getName());
        assertEquals("CountryA", city.getCountry());
        assertEquals("DistrictA", city.getDistrict());
        assertEquals(1000000, city.getPopulation());

        // Verify that close() was called on the ResultSet
        verify(mockResultSet).close();
    }

    @Test
    public void testGetCitiesByCountryOrderedByPopulation() throws SQLException {
        // Stub the ResultSet to return mock data
        when(mockStatement.executeQuery(Mockito.anyString())).thenReturn(mockResultSet);

        // Simulate data in ResultSet
        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // One row of data
        when(mockResultSet.getString("Name")).thenReturn("CityA");
        when(mockResultSet.getString("CountryName")).thenReturn("CountryA");
        when(mockResultSet.getString("District")).thenReturn("DistrictA");
        when(mockResultSet.getInt("Population")).thenReturn(1000000);

        // Call the method to test
        List<City> cities = cityQueryInstance.getCitiesByCountryOrderedByPopulation(mockConnection, "CountryA");

        // Validate the result
        assertEquals(1, cities.size());
        City city = cities.get(0);
        assertEquals("CityA", city.getName());
        assertEquals("CountryA", city.getCountry());
        assertEquals("DistrictA", city.getDistrict());
        assertEquals(1000000, city.getPopulation());

        // Verify that close() was called on the ResultSet
        verify(mockResultSet).close();
    }

    @Test
    public void testGetCitiesByDistrictOrderedByPopulation() throws SQLException {
        // Stub the ResultSet to return mock data
        when(mockStatement.executeQuery(Mockito.anyString())).thenReturn(mockResultSet);

        // Simulate data in ResultSet
        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // One row of data
        when(mockResultSet.getString("Name")).thenReturn("CityA");
        when(mockResultSet.getString("CountryName")).thenReturn("CountryA");
        when(mockResultSet.getString("District")).thenReturn("DistrictA");
        when(mockResultSet.getInt("Population")).thenReturn(1000000);

        // Call the method to test
        List<City> cities = cityQueryInstance.getCitiesByDistrictOrderedByPopulation(mockConnection, "DistrictA");

        // Validate the result
        assertEquals(1, cities.size());
        City city = cities.get(0);
        assertEquals("CityA", city.getName());
        assertEquals("CountryA", city.getCountry());
        assertEquals("DistrictA", city.getDistrict());
        assertEquals(1000000, city.getPopulation());

        // Verify that close() was called on the ResultSet
        verify(mockResultSet).close();
    }

    // Additional test methods for other query methods like getTopNPopulatedCitiesInWorld, getTopNPopulatedCitiesInContinent, etc.
}
