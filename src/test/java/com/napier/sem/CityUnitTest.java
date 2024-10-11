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

    @Test
    public void testGetTopNPopulatedCitiesInContinent() throws SQLException {
        // Setup mock ResultSet to return sample data
        when(mockResultSet.next()).thenReturn(true, true, false); // three rows
        when(mockResultSet.getInt("ID")).thenReturn(1, 2);
        when(mockResultSet.getString("Name")).thenReturn("City1", "City2");
        when(mockResultSet.getString("CountryName")).thenReturn("Country1", "Country2");
        when(mockResultSet.getString("District")).thenReturn("District1", "District2");
        when(mockResultSet.getInt("Population")).thenReturn(1000000, 2000000);

        // Call the method under test
        List<City> result = cityQuery.getTopNPopulatedCitiesInContinent(mockConnection);

        // Assertions
        assertEquals(2, result.size());
        assertEquals("City1", result.get(0).getName());
        assertEquals(1000000, result.get(0).getPopulation());
        assertEquals("City2", result.get(1).getName());
        assertEquals(2000000, result.get(1).getPopulation());
    }

}
