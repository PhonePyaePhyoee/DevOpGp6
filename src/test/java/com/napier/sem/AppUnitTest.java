//package com.napier.sem;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class Apptest {
//
//    private App app;
//    private Connection mockConnection;
//    private countryQuery mockCountryQuery;
//    private capitalQuery mockCapitalQuery;
//    private cityQuery mockCityQuery;
//
//    @BeforeEach
//    public void setUp() {
//        app = new App();
//        mockConnection = mock(Connection.class);
//        mockCountryQuery = mock(countryQuery.class);
//        mockCapitalQuery = mock(capitalQuery.class);
//        mockCityQuery = mock(cityQuery.class);
//
//        // Manually set the connection to the app
//        app.con = mockConnection;
//    }
//
//    @Test
//    public void testConnect() throws Exception {
//        // This test can be skipped because it's a live connection test.
//        // However, if you want to simulate and verify the connection logic, you can do so by asserting the method behavior.
//        app.connect();
//        // Here, you can assert things based on your logging or connection behavior if needed.
//    }
//
//    @Test
//    public void testDisconnect() throws SQLException {
//        app.disconnect();
//        verify(mockConnection, times(1)).close();
//    }
//
//    @Test
//    public void testGetAllCountriesOrderedByPopulation() throws SQLException {
//        List<Country> mockCountries = new ArrayList<>();
//        mockCountries.add(new Country("US", "United States", 331002651));
//        when(mockCountryQuery.getAllCountriesOrderedByPopulation(mockConnection)).thenReturn(mockCountries);
//
//        List<Country> countries = mockCountryQuery.getAllCountriesOrderedByPopulation(mockConnection);
//
//        assertEquals(1, countries.size());
//        assertEquals("United States", countries.get(0).getName());
//    }
//
//    @Test
//    public void testGetCapitalCitiesByContinentOrderedByPopulation() throws SQLException {
//        List<Capital> mockCapitals = new ArrayList<>();
//        mockCapitals.add(new Capital("Tokyo", "Japan", 13929286));
//        when(mockCapitalQuery.getCapitalCitiesByContinentOrderedByPopulation(mockConnection, "Asia"))
//                .thenReturn(mockCapitals);
//
//        List<Capital> capitals = mockCapitalQuery.getCapitalCitiesByContinentOrderedByPopulation(mockConnection, "Asia");
//
//        assertEquals(1, capitals.size());
//        assertEquals("Tokyo", capitals.get(0).getName());
//    }
//
//    @Test
//    public void testGetCitiesByRegionOrderedByPopulation() throws SQLException {
//        List<City> mockCities = new ArrayList<>();
//        mockCities.add(new City("Beijing", "China", "Beijing", 21542000));
//        when(mockCityQuery.getCitiesByRegionOrderedByPopulation(mockConnection, "Eastern Asia"))
//                .thenReturn(mockCities);
//
//        List<City> cities = mockCityQuery.getCitiesByRegionOrderedByPopulation(mockConnection, "Eastern Asia");
//
//        assertEquals(1, cities.size());
//        assertEquals("Beijing", cities.get(0).getName());
//    }
//}
