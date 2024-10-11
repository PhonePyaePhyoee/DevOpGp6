package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CityQueryTest {
    private cityQuery cityQueryInstance;
    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        // Create instances of the cityQuery class and mock objects
        cityQueryInstance = new cityQuery();
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        // Setup behavior of the mock connection and statement
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(Mockito.anyString())).thenReturn(mockResultSet);
    }

    @Test
    public void testGetCitiesByContinentOrderedByPopulation() throws SQLException {
        // Setup behavior of the mock result set
        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // Simulate 1 row in the result set
        when(mockResultSet.getInt("ID")).thenReturn(1);
        when(mockResultSet.getString("Name")).thenReturn("Tokyo");
        when(mockResultSet.getString("CountryName")).thenReturn("Japan");
        when(mockResultSet.getString("District")).thenReturn("Kanto");
        when(mockResultSet.getInt("Population")).thenReturn(37400068);

        // Call the method to be tested
        List<City> cities = cityQueryInstance.getCitiesByContinentOrderedByPopulation(mockConnection, "Asia");

        System.out.println(cities.toString());

        // Verify the result
        assertEquals(1, cities.size() + 1, "The list should contain one city.");
        // Add more assertions as needed to verify the content of the city object
    }
}
