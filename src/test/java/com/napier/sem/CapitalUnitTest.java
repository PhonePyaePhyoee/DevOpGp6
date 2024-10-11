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

public class CapitalUnitTest {

    private capitalQuery capitalQuery;  // Instance of capitalQuery to test
    private Connection mockConnection;  // Mock Connection object
    private Statement mockStatement;    // Mock Statement object
    private ResultSet mockResultSet;    // Mock ResultSet object

    @BeforeEach
    public void setUp() throws SQLException {
        capitalQuery = new capitalQuery(); // Create instance of capitalQuery
        mockConnection = mock(Connection.class); // Mock the connection
        mockStatement = mock(Statement.class);   // Mock the statement
        mockResultSet = mock(ResultSet.class);   // Mock the result set

        // Set up mock connection to return the mock statement
        when(mockConnection.createStatement()).thenReturn(mockStatement);
    }

    @Test
    public void testGetAllCapitalCitiesOrderedByPopulation() throws SQLException {
        // Setup mock ResultSet to return sample data
        // Setup mock ResultSet to return sample data
        when(mockResultSet.next()).thenReturn(true, true, false); // Simulate two rows, then no more rows
        when(mockResultSet.getString("Name")).thenReturn("Berlin", "Tokyo"); // Example capital city names
        when(mockResultSet.getString("CountryName")).thenReturn("Germany", "Japan"); // Example real country names
        when(mockResultSet.getInt("Population")).thenReturn(3769000, 13929286); // Example populations


        // Setup mock Statement to return the mock ResultSet
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Call the method under test
        List<Capital> result = capitalQuery.getAllCapitalCitiesOrderedByPopulation(mockConnection);

        // Assertions
        assertEquals(2, result.size());
        assertEquals("Capital1", result.get(0).getName());
        assertEquals(1000000, result.get(0).getPopulation());
        assertEquals("Capital2", result.get(1).getName());
        assertEquals(2000000, result.get(1).getPopulation());
    }

    @Test
    public void testGetCapitalCitiesByContinentOrderedByPopulation() throws SQLException {
        // Setup mock ResultSet to return sample data
        when(mockResultSet.next()).thenReturn(true, true, false); // Simulate two rows, then no more rows
        when(mockResultSet.getString("Name")).thenReturn("Canberra", "Beijing"); // Example capital cities
        when(mockResultSet.getString("CountryName")).thenReturn("Australia", "China"); // Example real countries
        when(mockResultSet.getInt("Population")).thenReturn(426704, 21540000); // Example populations


        // Setup mock Statement to return the mock ResultSet
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Call the method under test
        List<Capital> result = capitalQuery.getCapitalCitiesByContinentOrderedByPopulation(mockConnection, "Asia");

        // Assertions
        assertEquals(2, result.size());
        assertEquals("Capital1", result.get(0).getName());
        assertEquals(1000000, result.get(0).getPopulation());
        assertEquals("Capital2", result.get(1).getName());
        assertEquals(2000000, result.get(1).getPopulation());
    }

    @Test
    public void testGetCapitalCitiesByRegionOrderedByPopulation() throws SQLException {
        // Setup mock ResultSet to return sample data
        when(mockResultSet.next()).thenReturn(true, true, false); // Simulate two rows, then no more rows
        when(mockResultSet.getString("Name")).thenReturn("Paris", "Madrid"); // Example capital cities
        when(mockResultSet.getString("CountryName")).thenReturn("France", "Spain"); // Example real countries
        when(mockResultSet.getInt("Population")).thenReturn(2140526, 3223334); // Example populations


        // Setup mock Statement to return the mock ResultSet
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Call the method under test
        List<Capital> result = capitalQuery.getCapitalCitiesByRegionOrderedByPopulation(mockConnection, "Western Europe");

        // Assertions
        assertEquals(2, result.size());
        assertEquals("Capital1", result.get(0).getName());
        assertEquals(1000000, result.get(0).getPopulation());
        assertEquals("Capital2", result.get(1).getName());
        assertEquals(2000000, result.get(1).getPopulation());
    }

    @Test
    public void testGetTopNPopulatedCapitalCitiesInWorld() throws SQLException {
        // Setup mock ResultSet to return sample data
        when(mockResultSet.next()).thenReturn(true, true, false); // Simulate two rows, then no more rows
        when(mockResultSet.getString("Name")).thenReturn("Moscow", "London"); // Example capital cities
        when(mockResultSet.getString("CountryName")).thenReturn("Russia", "United Kingdom"); // Example real countries
        when(mockResultSet.getInt("Population")).thenReturn(11920000, 8982000); // Example populations



        // Setup mock Statement to return the mock ResultSet
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Call the method under test
        List<Capital> result = capitalQuery.getTopNPopulatedCapitalCitiesInWorld(mockConnection, 2);

        // Assertions
        assertEquals(2, result.size());
        assertEquals("Capital1", result.get(0).getName());
        assertEquals(1000000, result.get(0).getPopulation());
        assertEquals("Capital2", result.get(1).getName());
        assertEquals(2000000, result.get(1).getPopulation());
    }
}
