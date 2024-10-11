//package com.napier.sem;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class countryQUnitTest {
//
//    private Connection mockConnection;
//    private Statement mockStatement;
//    private ResultSet mockResultSet;
//    private countryQuery countryQuery;
//
//    @BeforeEach
//    public void setUp() throws SQLException {
//        mockConnection = Mockito.mock(Connection.class);
//        mockStatement = Mockito.mock(Statement.class);
//        mockResultSet = Mockito.mock(ResultSet.class);
//        countryQuery = new countryQuery();
//
//        // When the connection is used to create a statement, return the mock statement
//        when(mockConnection.createStatement()).thenReturn(mockStatement);
//    }
//
//    @Test
//    public void testGetAllCountriesOrderedByPopulation() throws SQLException {
//        // Mock the ResultSet to return expected data
//        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // Simulates one record
//        when(mockResultSet.getString("Code")).thenReturn("US");
//        when(mockResultSet.getString("Name")).thenReturn("United States");
//        when(mockResultSet.getString("Continent")).thenReturn("North America");
//        when(mockResultSet.getString("Region")).thenReturn("Americas");
//        when(mockResultSet.getInt("Population")).thenReturn(331002651);
//
//        // Call the method under test
//        List<Country> countries = countryQuery.getAllCountriesOrderedByPopulation(mockConnection);
//
//        // Verify the results
//        assertEquals(1, countries.size()); // Expect one country
//        assertEquals("United States", countries.get(0).getName()); // Check country name
//        assertEquals(331002651, countries.get(0).getPopulation()); // Check population
//    }
//
//    @Test
//    public void testGetCountriesByContinentOrderedByPopulation() throws SQLException {
//        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // Simulates one record
//        when(mockResultSet.getString("Code")).thenReturn("JP");
//        when(mockResultSet.getString("Name")).thenReturn("Japan");
//        when(mockResultSet.getString("Continent")).thenReturn("Asia");
//        when(mockResultSet.getString("Region")).thenReturn("East Asia");
//        when(mockResultSet.getInt("Population")).thenReturn(126476461);
//
//        // Call the method under test
//        List<Country> countries = countryQuery.getCountriesByContinentOrderedByPopulation(mockConnection, "Asia");
//
//        // Verify the results
//        assertEquals(1, countries.size()); // Expect one country
//        assertEquals("Japan", countries.get(0).getName()); // Check country name
//        assertEquals(126476461, countries.get(0).getPopulation()); // Check population
//    }
//
//    @Test
//    public void testGetCountriesByRegionOrderedByPopulation() throws SQLException {
//        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // Simulates one record
//        when(mockResultSet.getString("Code")).thenReturn("CN");
//        when(mockResultSet.getString("Name")).thenReturn("China");
//        when(mockResultSet.getString("Continent")).thenReturn("Asia");
//        when(mockResultSet.getString("Region")).thenReturn("East Asia");
//        when(mockResultSet.getInt("Population")).thenReturn(1439323776);
//
//        // Call the method under test
//        List<Country> countries = countryQuery.getCountriesByRegionOrderedByPopulation(mockConnection, "East Asia");
//
//        // Verify the results
//        assertEquals(1, countries.size()); // Expect one country
//        assertEquals("China", countries.get(0).getName()); // Check country name
//        assertEquals(1439323776, countries.get(0).getPopulation()); // Check population
//    }
//
//    @Test
//    public void testGetTopNPopulatedCountriesInWorld() throws SQLException {
//        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // Simulates one record
//        when(mockResultSet.getString("Code")).thenReturn("IN");
//        when(mockResultSet.getString("Name")).thenReturn("India");
//        when(mockResultSet.getString("Continent")).thenReturn("Asia");
//        when(mockResultSet.getString("Region")).thenReturn("South Asia");
//        when(mockResultSet.getInt("Population")).thenReturn(1380004385);
//
//        // Call the method under test
//        List<Country> countries = countryQuery.getTopNPopulatedCountriesInWorld(mockConnection, 1);
//
//        // Verify the results
//        assertEquals(1, countries.size()); // Expect one country
//        assertEquals("India", countries.get(0).getName()); // Check country name
//        assertEquals(1380004385, countries.get(0).getPopulation()); // Check population
//    }
//
//    @Test
//    public void testGetTopNPopulatedCountriesInContinent() throws SQLException {
//        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // Simulates one record
//        when(mockResultSet.getString("Code")).thenReturn("BR");
//        when(mockResultSet.getString("Name")).thenReturn("Brazil");
//        when(mockResultSet.getString("Continent")).thenReturn("South America");
//        when(mockResultSet.getString("Region")).thenReturn("Americas");
//        when(mockResultSet.getInt("Population")).thenReturn(212559417);
//
//        // Call the method under test
//        List<Country> countries = countryQuery.getTopNPopulatedCountriesInContinent(mockConnection, "South America", 1);
//
//        // Verify the results
//        assertEquals(1, countries.size()); // Expect one country
//        assertEquals("Brazil", countries.get(0).getName()); // Check country name
//        assertEquals(212559417, countries.get(0).getPopulation()); // Check population
//    }
//
//    @Test
//    public void testGetTopNPopulatedCountriesInRegion() throws SQLException {
//        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // Simulates one record
//        when(mockResultSet.getString("Code")).thenReturn("MX");
//        when(mockResultSet.getString("Name")).thenReturn("Mexico");
//        when(mockResultSet.getString("Continent")).thenReturn("North America");
//        when(mockResultSet.getString("Region")).thenReturn("Americas");
//        when(mockResultSet.getInt("Population")).thenReturn(128932753);
//
//        // Call the method under test
//        List<Country> countries = countryQuery.getTopNPopulatedCountriesInRegion(mockConnection, "Americas", 1);
//
//        // Verify the results
//        assertEquals(1, countries.size()); // Expect one country
//        assertEquals("Mexico", countries.get(0).getName()); // Check country name
//        assertEquals(128932753, countries.get(0).getPopulation()); // Check population
//    }
//}
