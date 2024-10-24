package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppIntegrationTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33061", 5000);
    }

    @Test
    void testGetPopulationDataByContinent() throws SQLException {
        populationQuery populationQuery = new populationQuery();
        List<Population> populations = populationQuery.getPopulationDataByContinent(app.con);

        assertEquals("Antarctica", populations.get(0).getName()); // Adjust based on your expected data
        // Add other assertions based on expected population values for Asia
    }

    @Test
    void testGetPopulationDataByRegion() throws SQLException {
        populationQuery populationQuery = new populationQuery();
        List<Population> populations = populationQuery.getPopulationDataByRegion(app.con);

        assertEquals("Antarctica", populations.get(0).getName()); // Adjust based on your expected data
        // Add other assertions based on expected population values for Eastern Asia
    }

    @Test
    void testGetPopulationDataByCountry() throws SQLException {
        populationQuery populationQuery = new populationQuery();
        List<Population> populations = populationQuery.getPopulationDataByCountry(app.con);

        assertEquals("China", populations.get(0).getName()); // Adjust based on your expected data
        // Add other assertions based on expected population values for USA
    }

    @Test
    void testGetWorldPopulationData() throws SQLException {
        populationQuery populationQuery = new populationQuery();
        List<Population> populations = populationQuery.getWorldPopulationData(app.con);

        // Assuming there’s only one record for total world population
        assertEquals(6078749450L, populations.get(0).getTotalPopulation()); // Adjust based on your expected total
    }

    @Test
    void testGetContinentPopulationData() throws SQLException {
        populationQuery populationQuery = new populationQuery();
        List<Population> populations = populationQuery.getContinentPopulationData(app.con, "Asia");

        assertEquals("Asia", populations.get(0).getName()); // Adjust based on your expected data
        // Add other assertions based on expected population values for Asia
    }

    @Test
    void testGetRegionPopulationData() throws SQLException {
        populationQuery populationQuery = new populationQuery();
        List<Population> populations = populationQuery.getRegionPopulationData(app.con, "Eastern Asia");

        assertEquals("Eastern Asia", populations.get(0).getName()); // Adjust based on your expected data
        // Add other assertions based on expected population values for Eastern Asia
    }

    @Test
    void testGetCountryPopulationData() throws SQLException {
        populationQuery populationQuery = new populationQuery();
        List<Population> populations = populationQuery.getCountryPopulationData(app.con, "USA");

        assertEquals("USA", populations.get(0).getName()); // Adjust based on your expected data
        // Add other assertions based on expected population values for USA
    }

    @Test
    void testGetDistrictPopulationData() throws SQLException {
        populationQuery populationQuery = new populationQuery();
        List<Population> populations = populationQuery.getDistrictPopulationData(app.con, "Shandong");

        assertEquals("Shandong", populations.get(0).getName()); // Adjust based on your expected data
        // Add other assertions based on expected population values for Shandong
    }

    @Test
    void testGetCityPopulationData() throws SQLException {
        populationQuery populationQuery = new populationQuery();
        List<Population> populations = populationQuery.getCityPopulationData(app.con, "Breda");

        assertEquals("Breda", populations.get(0).getName()); // Adjust based on your expected data
        // Add other assertions based on expected population values for Breda
    }

    @Test
    void testGetLanguagePopulationData() throws SQLException {
        populationQuery populationQuery = new populationQuery();
        List<languagepopulation> languagePopulations = populationQuery.getLanguagePopulationData(app.con);

        assertEquals("Chinese", languagePopulations.get(0).getLanguage()); // Adjust based on your expected data
        // Add other assertions based on expected language population values
    }
}