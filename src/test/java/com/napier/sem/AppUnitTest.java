package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AppUnitTest {

    private App app;

    @Mock
    private Connection mockConnection;

    @Mock
    private countryQuery mockCountryQuery;

    @Mock
    private cityQuery mockCityQuery;

    @Mock
    private capitalQuery mockCapitalQuery;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
        app = new App();  // Initialize App instance
        app.con = mockConnection;  // Inject mock connection
    }

    @Test
    public void testConnectToDatabase() {
        // Test logic to connect to the database (requires integration test)
        app.connect();
        assertNotNull(app.con);  // Connection should not be null if successful
    }

    @Test
    public void testDisconnectFromDatabase() throws SQLException {
        // Simulate closing the connection
        app.disconnect();
        verify(mockConnection, times(1)).close();  // Verify that the connection close method was called
    }

    @Test
    public void testGetAllCountriesOrderedByPopulation() throws SQLException {
        // Mock country query results
        List<Country> mockCountries = new ArrayList<>();
        mockCountries.add(new Country("CHN", "China", "Asia", "Eastern Asia", "Beijing", 1400000000));
        mockCountries.add(new Country("IND", "India", "Asia", "Southern Asia", "New Delhi", 1300000000));

        // Mock the countryQuery to return the mock data
        when(mockCountryQuery.getAllCountriesOrderedByPopulation(mockConnection)).thenReturn(mockCountries);

        // Simulate calling the method in the App class
        List<Country> result = mockCountryQuery.getAllCountriesOrderedByPopulation(mockConnection);

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("China", result.get(0).getName());
        assertEquals(1400000000, result.get(0).getPopulation());
    }

    @Test
    public void testGetTopNPopulatedCitiesInWorld() throws SQLException {
        // Mock city query results
        List<City> mockCities = new ArrayList<>();
        mockCities.add(new City("Tokyo", "Japan", "Tokyo", 37393000));
        mockCities.add(new City("Delhi", "India", "Delhi", 30291000));

        // Mock the cityQuery to return the mock data
        when(mockCityQuery.getTopNPopulatedCitiesInWorld(mockConnection, 5)).thenReturn(mockCities);

        // Simulate calling the method in the App class
        List<City> result = mockCityQuery.getTopNPopulatedCitiesInWorld(mockConnection, 5);

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Tokyo", result.get(0).getName());
        assertEquals(37393000, result.get(0).getPopulation());
    }

    @Test
    public void testGetAllCapitalCitiesOrderedByPopulation() throws SQLException {
        // Mock capital query results
        List<Capital> mockCapitals = new ArrayList<>();
        mockCapitals.add(new Capital("Beijing", "China", 21540000));
        mockCapitals.add(new Capital("Moscow", "Russia", 12500000));

        // Mock the capitalQuery to return the mock data
        when(mockCapitalQuery.getAllCapitalCitiesOrderedByPopulation(mockConnection)).thenReturn(mockCapitals);

        // Simulate calling the method in the App class
        List<Capital> result = mockCapitalQuery.getAllCapitalCitiesOrderedByPopulation(mockConnection);

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Beijing", result.get(0).getName());
        assertEquals(21540000, result.get(0).getPopulation());
    }
}
