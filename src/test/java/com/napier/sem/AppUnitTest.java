package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class AppUnitTest {

    private Connection mockConnection;
    private countryQuery mockCountryQuery;
    private cityQuery mockCityQuery;
    private capitalQuery mockCapitalQuery;
    private populationQuery mockPopulationQuery;

    @BeforeEach
    void setUp() {
        // Initialize mock objects
        mockConnection = mock(Connection.class);
        mockCountryQuery = mock(countryQuery.class);
        mockCityQuery = mock(cityQuery.class);
        mockCapitalQuery = mock(capitalQuery.class);
        mockPopulationQuery = mock(populationQuery.class);
    }

    @Test
    void testCountryQueryMethods() throws SQLException {
        // Mock return values for countryQuery
        List<Country> mockCountries = Arrays.asList(
                new Country("USA", "United States", "North America", "Western Europe", "Washington DC", 331000000),
                new Country("CHN", "China", "Asia", "Eastern Asia", "Beijing", 1441000000)
        );

        when(mockCountryQuery.getAllCountriesOrderedByPopulation(mockConnection)).thenReturn(mockCountries);

        // Call the method under test
        List<Country> countries = mockCountryQuery.getAllCountriesOrderedByPopulation(mockConnection);
        mockCountryQuery.displayAllCountriesOrderedByPopulation(countries);

        // Verify interactions
        verify(mockCountryQuery, times(1)).getAllCountriesOrderedByPopulation(mockConnection);
        verify(mockCountryQuery, times(1)).displayAllCountriesOrderedByPopulation(mockCountries);
    }

    @Test
    void testCityQueryMethods() throws SQLException {
        // Mock return values for cityQuery
        List<City> mockCities = Arrays.asList(
                new City("Tokyo", "Japan", "Kanto", 37400000),
                new City("New York", "USA", "New York", 8419600)
        );

        when(mockCityQuery.getAllCitiesOrderedByPopulation(mockConnection)).thenReturn(mockCities);

        // Call the method under test
        List<City> cities = mockCityQuery.getAllCitiesOrderedByPopulation(mockConnection);
        mockCityQuery.displayAllCitiesOrderedByPopulation(cities);

        // Verify interactions
        verify(mockCityQuery, times(1)).getAllCitiesOrderedByPopulation(mockConnection);
        verify(mockCityQuery, times(1)).displayAllCitiesOrderedByPopulation(mockCities);
    }

    @Test
    void testCapitalQueryMethods() throws SQLException {
        // Mock return values for capitalQuery
        List<Capital> mockCapitals = Arrays.asList(
                new Capital("Washington DC", "USA", 705749),
                new Capital("Beijing", "China", 21540000)
        );

        when(mockCapitalQuery.getAllCapitalCitiesOrderedByPopulation(mockConnection)).thenReturn(mockCapitals);

        // Call the method under test
        List<Capital> capitals = mockCapitalQuery.getAllCapitalCitiesOrderedByPopulation(mockConnection);
        mockCapitalQuery.displayAllCapitalCitiesOrderedByPopulation(capitals);

        // Verify interactions
        verify(mockCapitalQuery, times(1)).getAllCapitalCitiesOrderedByPopulation(mockConnection);
        verify(mockCapitalQuery, times(1)).displayAllCapitalCitiesOrderedByPopulation(mockCapitals);
    }

    @Test
    void testPopulationQueryMethods() throws SQLException {
        // Mock return values for populationQuery
        List<Population> mockPopulations = Arrays.asList(
                new Population("Asia", 4600000000L, (long) 59.6, (long) 40.4),
                new Population("Africa", 1340000000L, (long) 43.7, (long) 56.3)
        );

        when(mockPopulationQuery.getPopulationDataByContinent(mockConnection)).thenReturn(mockPopulations);

        // Call the method under test
        List<Population> populations = mockPopulationQuery.getPopulationDataByContinent(mockConnection);
        mockPopulationQuery.displayPopulationDataByContinent(populations);

        // Verify interactions
        verify(mockPopulationQuery, times(1)).getPopulationDataByContinent(mockConnection);
        verify(mockPopulationQuery, times(1)).displayPopulationDataByContinent(mockPopulations);
    }
}
