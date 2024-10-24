package com.napier.sem;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class AppUnitTest {

    private App app;
    private Connection mockConnection;

    @BeforeEach
    public void setup() {
        app = new App();
        mockConnection = mock(Connection.class);  // Mocking the Connection object
    }

    @Test
    public void testConnectSuccessful() throws SQLException {
        String location = "localhost:33060";
        int delay = 3000;

        try (MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                    .thenReturn(mockConnection);

            app.connect(location, delay);

            assertNotNull(app.con, "Connection should not be null after a successful connection");
            assertEquals(mockConnection, app.con, "Connection should match the mock connection");

            mockedDriverManager.verify(() -> DriverManager.getConnection(
                    "jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                    "root", "example"), times(1));
        }
    }


    @Test
    public void testDisconnectWithNullConnection() {
        app.con = null;  // No active connection
        app.disconnect();

        assertNull(app.con, "Connection should still be null");
    }

    @Test
    public void testCountryQuery() throws SQLException {
        countryQuery mockCountryQuery = mock(countryQuery.class);
        List<Country> mockCountries = new ArrayList<>();
        when(mockCountryQuery.getAllCountriesOrderedByPopulation(mockConnection)).thenReturn(mockCountries);

        // Act
        List<Country> result = mockCountryQuery.getAllCountriesOrderedByPopulation(mockConnection);

        // Assert
        assertNotNull(result);
        assertEquals(mockCountries, result, "Countries should match the mock data");
        verify(mockCountryQuery, times(1)).getAllCountriesOrderedByPopulation(mockConnection);
    }

    @Test
    public void testCityQuery() throws SQLException {
        cityQuery mockCityQuery = mock(cityQuery.class);
        List<City> mockCities = new ArrayList<>();
        when(mockCityQuery.getAllCitiesOrderedByPopulation(mockConnection)).thenReturn(mockCities);

        // Act
        List<City> result = mockCityQuery.getAllCitiesOrderedByPopulation(mockConnection);

        // Assert
        assertNotNull(result);
        assertEquals(mockCities, result, "Cities should match the mock data");
        verify(mockCityQuery, times(1)).getAllCitiesOrderedByPopulation(mockConnection);
    }

    @Test
    public void testCapitalQuery() throws SQLException {
        capitalQuery mockCapitalQuery = mock(capitalQuery.class);
        List<Capital> mockCapitals = new ArrayList<>();
        when(mockCapitalQuery.getAllCapitalCitiesOrderedByPopulation(mockConnection)).thenReturn(mockCapitals);

        // Act
        List<Capital> result = mockCapitalQuery.getAllCapitalCitiesOrderedByPopulation(mockConnection);

        // Assert
        assertNotNull(result);
        assertEquals(mockCapitals, result, "Capitals should match the mock data");
        verify(mockCapitalQuery, times(1)).getAllCapitalCitiesOrderedByPopulation(mockConnection);
    }

    @Test
    public void testPopulationQuery() throws SQLException {
        populationQuery mockPopulationQuery = mock(populationQuery.class);
        List<Population> mockPopulations = new ArrayList<>();
        when(mockPopulationQuery.getPopulationDataByContinent(mockConnection)).thenReturn(mockPopulations);

        // Act
        List<Population> result = mockPopulationQuery.getPopulationDataByContinent(mockConnection);

        // Assert
        assertNotNull(result);
        assertEquals(mockPopulations, result, "Populations should match the mock data");
        verify(mockPopulationQuery, times(1)).getPopulationDataByContinent(mockConnection);
    }

    // Add more tests as needed for specific queries or cases
}
