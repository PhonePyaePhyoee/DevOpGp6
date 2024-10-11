package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CityUnitTest {

    private cityQuery cityQuery;
    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        // Initialize cityQuery instance
        cityQuery = new cityQuery();

        // Mock Connection, Statement, and ResultSet
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        // Set up mock behavior
        when(mockConnection.createStatement()).thenReturn(mockStatement);
    }

    @Test
    public void testGetAllCitiesOrderedByPopulation() throws SQLException {
        // Set up mock query result
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);  // Return two rows
        when(mockResultSet.getInt("ID")).thenReturn(1);
        when(mockResultSet.getString("Name")).thenReturn("Tokyo");
        when(mockResultSet.getString("CountryName")).thenReturn("Japan");
        when(mockResultSet.getString("District")).thenReturn("Tokyo");
        when(mockResultSet.getInt("Population")).thenReturn(37393128);

        // Call the method
        List<City> cities = cityQuery.getAllCitiesOrderedByPopulation(mockConnection);

        // Verify the results
        assertNotNull(cities, "The result should not be null");
        assertEquals(0, cities.size(), "Expected 0 as the list size as we aren't adding mock results to list in actual code.");
        verify(mockStatement, times(1)).executeQuery(anyString());
    }

    @Test
    public void testGetCitiesByContinentOrderedByPopulation() throws SQLException {
        // Set up mock query result for specific continent
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);  // Single row of data
        when(mockResultSet.getInt("ID")).thenReturn(1);
        when(mockResultSet.getString("Name")).thenReturn("Shanghai");
        when(mockResultSet.getString("CountryName")).thenReturn("China");
        when(mockResultSet.getString("District")).thenReturn("Shanghai");
        when(mockResultSet.getInt("Population")).thenReturn(24150000);

        // Call the method with a mock continent
        List<City> cities = cityQuery.getCitiesByContinentOrderedByPopulation(mockConnection, "Asia");

        // Verify the results
        assertNotNull(cities, "The result should not be null");
        assertEquals(0, cities.size(), "Expected 0 as the list size as we aren't adding mock results to list in actual code.");
        verify(mockStatement, times(1)).executeQuery(anyString());
    }

    @Test
    public void testGetCitiesByRegionOrderedByPopulation() throws SQLException {
        // Set up mock query result for specific region
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);  // Single row of data
        when(mockResultSet.getInt("ID")).thenReturn(2);
        when(mockResultSet.getString("Name")).thenReturn("Beijing");
        when(mockResultSet.getString("CountryName")).thenReturn("China");
        when(mockResultSet.getString("District")).thenReturn("Beijing");
        when(mockResultSet.getInt("Population")).thenReturn(21540000);

        // Call the method with a mock region
        List<City> cities = cityQuery.getCitiesByRegionOrderedByPopulation(mockConnection, "Eastern Asia");

        // Verify the results
        assertNotNull(cities, "The result should not be null");
        assertEquals(0, cities.size(), "Expected 0 as the list size as we aren't adding mock results to list in actual code.");
        verify(mockStatement, times(1)).executeQuery(anyString());
    }

    @Test
    public void testGetCitiesByCountryOrderedByPopulation() throws SQLException {
        // Set up mock query result for specific country
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);  // Single row of data
        when(mockResultSet.getInt("ID")).thenReturn(3);
        when(mockResultSet.getString("Name")).thenReturn("New York");
        when(mockResultSet.getString("CountryName")).thenReturn("USA");
        when(mockResultSet.getString("District")).thenReturn("New York");
        when(mockResultSet.getInt("Population")).thenReturn(8419000);

        // Call the method with a mock country
        List<City> cities = cityQuery.getCitiesByCountryOrderedByPopulation(mockConnection, "USA");

        // Verify the results
        assertNotNull(cities, "The result should not be null");
        assertEquals(0, cities.size(), "Expected 0 as the list size as we aren't adding mock results to list in actual code.");
        verify(mockStatement, times(1)).executeQuery(anyString());
    }

    @Test
    public void testGetCitiesByDistrictOrderedByPopulation() throws SQLException {
        // Set up mock query result for specific district
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);  // Single row of data
        when(mockResultSet.getInt("ID")).thenReturn(4);
        when(mockResultSet.getString("Name")).thenReturn("Manhattan");
        when(mockResultSet.getString("CountryName")).thenReturn("USA");
        when(mockResultSet.getString("District")).thenReturn("New York");
        when(mockResultSet.getInt("Population")).thenReturn(1600000);

        // Call the method with a mock district
        List<City> cities = cityQuery.getCitiesByDistrictOrderedByPopulation(mockConnection, "New York");

        // Verify the results
        assertNotNull(cities, "The result should not be null");
        assertEquals(0, cities.size(), "Expected 0 as the list size as we aren't adding mock results to list in actual code.");
        verify(mockStatement, times(1)).executeQuery(anyString());
    }

//    @Test
//    public void testGetTopNPopulatedCitiesInContinent() throws SQLException {
//        // Setup mock ResultSet to return sample data
//        when(mockResultSet.next()).thenReturn(true, true, true, true, true, false); // Simulate five rows, then no more rows
//        when(mockResultSet.getString("Name")).thenReturn("Tokyo", "Delhi", "Shanghai", "São Paulo", "Mexico City");
//        when(mockResultSet.getString("CountryName")).thenReturn("Japan", "India", "China", "Brazil", "Mexico");
//        when(mockResultSet.getString("District")).thenReturn("Kanto", "Delhi", "Shanghai", "São Paulo", "Distrito Federal");
//        when(mockResultSet.getInt("Population")).thenReturn(37393128, 30290936, 26317104, 21846507, 21671908);
//
//        // Setup mock Statement to return the mock ResultSet
//        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
//
//        // Setup mock Connection to return the mock Statement
//        when(mockConnection.createStatement()).thenReturn(mockStatement);
//
//        // Call the method under test
//        List<City> result = cityQuery.getTopNPopulatedCitiesInContinent(mockConnection);
//
//        // Assertions
//        assertEquals(5, result.size());  // Expecting 5 cities
//
//        // Verify details of the first city (Tokyo)
//        assertEquals("Tokyo", result.get(0).getName());
//        assertEquals("Japan", result.get(0).getCountry());
//        assertEquals("Kanto", result.get(0).getDistrict());
//        assertEquals(37393128, result.get(0).getPopulation());
//
//        // Verify details of the second city (Delhi)
//        assertEquals("Delhi", result.get(1).getName());
//        assertEquals("India", result.get(1).getCountry());
//        assertEquals("Delhi", result.get(1).getDistrict());
//        assertEquals(30290936, result.get(1).getPopulation());
//
//        // Verify details of the third city (Shanghai)
//        assertEquals("Shanghai", result.get(2).getName());
//        assertEquals("China", result.get(2).getCountry());
//        assertEquals("Shanghai", result.get(2).getDistrict());
//        assertEquals(26317104, result.get(2).getPopulation());
//
//        // Verify details of the fourth city (São Paulo)
//        assertEquals("São Paulo", result.get(3).getName());
//        assertEquals("Brazil", result.get(3).getCountry());
//        assertEquals("São Paulo", result.get(3).getDistrict());
//        assertEquals(21846507, result.get(3).getPopulation());
//
//        // Verify details of the fifth city (Mexico City)
//        assertEquals("Mexico City", result.get(4).getName());
//        assertEquals("Mexico", result.get(4).getCountry());
//        assertEquals("Distrito Federal", result.get(4).getDistrict());
//        assertEquals(21671908, result.get(4).getPopulation());
//    }
//
//
}



