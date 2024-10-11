package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CountryUnitTest {

    private countryQuery countryQuery;  // Instance of countryQuery to test
    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        countryQuery = new countryQuery();
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
    }

    @Test
    public void testGetAllCountriesOrderedByPopulation() throws SQLException {
        when(mockStatement.executeQuery("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population FROM country ORDER BY country.Population DESC"))
                .thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("Code")).thenReturn("US");
        when(mockResultSet.getString("Name")).thenReturn("United States");
        when(mockResultSet.getString("Continent")).thenReturn("North America");
        when(mockResultSet.getString("Region")).thenReturn("Northern America");
        when(mockResultSet.getInt("Population")).thenReturn(331002651);

        List<Country> countries = countryQuery.getAllCountriesOrderedByPopulation(mockConnection);

        assertEquals(1, countries.size());
        assertEquals("United States", countries.get(0).getName());
        assertEquals("US", countries.get(0).getCode());
        assertEquals("North America", countries.get(0).getContinent());
        assertEquals("Northern America", countries.get(0).getRegion());
        assertEquals(331002651, countries.get(0).getPopulation());
    }

    @Test
    public void testGetCountriesByContinentOrderedByPopulation() throws SQLException {
        String continent = "Asia";
        when(mockStatement.executeQuery("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population FROM country WHERE country.Continent = 'Asia' ORDER BY country.Population DESC"))
                .thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("Code")).thenReturn("CN");
        when(mockResultSet.getString("Name")).thenReturn("China");
        when(mockResultSet.getString("Continent")).thenReturn("Asia");
        when(mockResultSet.getString("Region")).thenReturn("Eastern Asia");
        when(mockResultSet.getInt("Population")).thenReturn(1400000000);

        List<Country> countries = countryQuery.getCountriesByContinentOrderedByPopulation(mockConnection, continent);

        assertEquals(1, countries.size());
        assertEquals("China", countries.get(0).getName());
        assertEquals("CN", countries.get(0).getCode());
        assertEquals("Asia", countries.get(0).getContinent());
        assertEquals("Eastern Asia", countries.get(0).getRegion());
        assertEquals(1400000000, countries.get(0).getPopulation());
    }

    @Test
    public void testGetCountriesByRegionOrderedByPopulation() throws SQLException {
        String region = "Western Europe";
        when(mockStatement.executeQuery("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population FROM country WHERE country.Region = 'Western Europe' ORDER BY country.Population DESC"))
                .thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("Code")).thenReturn("DE");
        when(mockResultSet.getString("Name")).thenReturn("Germany");
        when(mockResultSet.getString("Continent")).thenReturn("Europe");
        when(mockResultSet.getString("Region")).thenReturn("Western Europe");
        when(mockResultSet.getInt("Population")).thenReturn(83000000);

        List<Country> countries = countryQuery.getCountriesByRegionOrderedByPopulation(mockConnection, region);

        assertEquals(1, countries.size());
        assertEquals("Germany", countries.get(0).getName());
        assertEquals("DE", countries.get(0).getCode());
        assertEquals("Europe", countries.get(0).getContinent());
        assertEquals("Western Europe", countries.get(0).getRegion());
        assertEquals(83000000, countries.get(0).getPopulation());
    }

    @Test
    public void testGetTopNPopulatedCountriesInRegion() throws SQLException {
        String region = "Eastern Africa";
        int N = 2;
        when(mockStatement.executeQuery("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population FROM country WHERE country.Region = 'Eastern Africa' ORDER BY country.Population DESC LIMIT 2"))
                .thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("Code")).thenReturn("ET").thenReturn("KE");
        when(mockResultSet.getString("Name")).thenReturn("Ethiopia").thenReturn("Kenya");
        when(mockResultSet.getString("Continent")).thenReturn("Africa").thenReturn("Africa");
        when(mockResultSet.getString("Region")).thenReturn("Eastern Africa").thenReturn("Eastern Africa");
        when(mockResultSet.getInt("Population")).thenReturn(114000000).thenReturn(53000000);

        List<Country> countries = countryQuery.getTopNPopulatedCountriesInRegion(mockConnection, region, N);

        assertEquals(2, countries.size());
        assertEquals("Ethiopia", countries.get(0).getName());
        assertEquals("Kenya", countries.get(1).getName());
    }

    @Test
    public void testGetTopNPopulatedCountriesInWorld() throws SQLException {
        int N = 3;
        when(mockStatement.executeQuery("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population FROM country ORDER BY country.Population DESC LIMIT 3"))
                .thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("Code")).thenReturn("CN").thenReturn("IN").thenReturn("US");
        when(mockResultSet.getString("Name")).thenReturn("China").thenReturn("India").thenReturn("United States");
        when(mockResultSet.getString("Continent")).thenReturn("Asia").thenReturn("Asia").thenReturn("North America");
        when(mockResultSet.getString("Region")).thenReturn("Eastern Asia").thenReturn("Southern Asia").thenReturn("Northern America");
        when(mockResultSet.getInt("Population")).thenReturn(1400000000).thenReturn(1300000000).thenReturn(331000000);

        List<Country> countries = countryQuery.getTopNPopulatedCountriesInWorld(mockConnection, N);

        assertEquals(3, countries.size());
        assertEquals("China", countries.get(0).getName());
        assertEquals("India", countries.get(1).getName());
        assertEquals("United States", countries.get(2).getName());
    }

    @Test
    public void testGetTopNPopulatedCountriesInContinent() throws SQLException {
        String continent = "South America";
        int N = 2;
        when(mockStatement.executeQuery("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population FROM country WHERE country.Continent = 'South America' ORDER BY country.Population DESC LIMIT 2"))
                .thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("Code")).thenReturn("BR").thenReturn("AR");
        when(mockResultSet.getString("Name")).thenReturn("Brazil").thenReturn("Argentina");
        when(mockResultSet.getString("Continent")).thenReturn("South America").thenReturn("South America");
        when(mockResultSet.getString("Region")).thenReturn("Latin America").thenReturn("Latin America");
        when(mockResultSet.getInt("Population")).thenReturn(212000000).thenReturn(45000000);

        List<Country> countries = countryQuery.getTopNPopulatedCountriesInContinent(mockConnection, continent, N);

        assertEquals(2, countries.size());
        assertEquals("Brazil", countries.get(0).getName());
        assertEquals("Argentina", countries.get(1).getName());
    }
}
