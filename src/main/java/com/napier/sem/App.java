package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class App {

    Connection con = null;

    // Method to connect to the database
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection to the database
        int retries = 100;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                // Exit for loop
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    // Method to disconnect from the database
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    // Main method - Entry point of the program
    public static void main(String[] args) throws SQLException {
        App app = new App();  // Create an instance of App class
        app.connect();        // Connect to the database

        // Call countryQuery and retrieve countries ordered by population

        countryQuery countryQuery = new countryQuery();  // Create an instance of countryQuery

        List<Country> countries = countryQuery.getAllCountriesOrderedByPopulation(app.con);
        countryQuery.displayAllCountriesOrderedByPopulation(countries);

        List<Country> countries1 = countryQuery.getCountriesByContinentOrderedByPopulation(app.con,"Asia");
        countryQuery.displayCountriesByContinentOrderedByPopulation(countries1,"Asia");

        List<Country> countries2 = countryQuery.getCountriesByRegionOrderedByPopulation(app.con,"Western Africa");
        countryQuery.displayCountriesByRegionOrderedByPopulation(countries2,"Western Africa");

        List<Country> countries3 = countryQuery.getTopNPopulatedCountriesInWorld(app.con,5);
        countryQuery.displayTopNPopulatedCountriesInWorld(countries3,5);

        List<Country> countries4 = countryQuery.getTopNPopulatedCountriesInContinent(app.con, "Asia",5);
        countryQuery.displayTopNPopulatedCountriesInContinent(countries4,"Asia",5);

        List<Country> countries5 = countryQuery.getTopNPopulatedCountriesInRegion(app.con, "Western Africa",5);
        countryQuery.displayTopNPopulatedCountriesInRegion(countries5,"Western Africa",5);

        cityQuery cityQuery = new cityQuery();

        List<City> cities = cityQuery.getAllCitiesOrderedByPopulation(app.con);
        cityQuery.displayAllCitiesOrderedByPopulation(cities);

        List<City> resultcity = cityQuery.getCitiesByContinentOrderedByPopulation(app.con,"Asia");
        cityQuery.displayCitiesByContinentOrderedByPopulation(resultcity,"Asia");

        List<City> resultcityregion = cityQuery.getCitiesByRegionOrderedByPopulation(app.con,"Europe");
        cityQuery.displayCitiesByRegionOrderedByPopulation(resultcityregion,"Europe");

        List<City>resultcitycountry = cityQuery.getCitiesByCountryOrderedByPopulation(app.con,"China");
        cityQuery.displayCitiesByCountryOrderedByPopulation(resultcitycountry,"China");

        List<City>resultcitydistrict = cityQuery.getCitiesByDistrictOrderedByPopulation(app.con,"Shandong");
        cityQuery.displayCitiesByDistrictOrderedByPopulation(resultcitydistrict,"Shandong");

        List<City>resulttopNcities = cityQuery.getTopNPopulatedCitiesInWorld(app.con,10);
        cityQuery.displayTopNPopulatedCitiesInWorld(resulttopNcities,10);

        List<City>resulttopNdcontinent = cityQuery.getTopNPopulatedCitiesInContinent(app.con,"Asia",5);
        cityQuery.displayTopNPopulatedCitiesInContinent(resulttopNdcontinent,"Asia", 5);

        List<City>resultgetCitiesInRegion = cityQuery.getTopNPopulatedCitiesInRegion(app.con,"Eastern Asia",5);
        cityQuery.displayTopNPopulatedCitiesInRegion(resultgetCitiesInRegion,"Eastern Asia", 5);

        List<City>resultgetCitiesInCountry = cityQuery.getTopNPopulatedCitiesInCountry(app.con,"China",5);
        cityQuery.displayTopNPopulatedCitiesInCountry(resultgetCitiesInCountry,"China", 5);

        List<City>resulutgetdistrict = cityQuery.getTopNPopulatedCitiesInDistrict(app.con,"Shandong",5);
        cityQuery.displayTopNPopulatedCitiesInDistrict(resulutgetdistrict,"Shandong", 5);

        capitalQuery capitalQuery = new capitalQuery();

        List<Capital> capitals = capitalQuery.getAllCapitalCitiesOrderedByPopulation(app.con);
        capitalQuery.displayAllCapitalCitiesOrderedByPopulation(capitals);

        //#Test Method to retrieve all capital cities in a continent ordered by population (largest to smallest)
        List<Capital> result = capitalQuery.getCapitalCitiesByContinentOrderedByPopulation(app.con,"Asia");
        capitalQuery.displayCapitalCitiesByContinentOrderedByPopulation(result,"Asia");

         // Example region
        List<Capital> capitalsByRegion = capitalQuery.getCapitalCitiesByRegionOrderedByPopulation(app.con,"Eastern Asia" );
        capitalQuery.displayCapitalCitiesByRegionOrderedByPopulation(capitalsByRegion, "Eastern Asia");

        // No. 20: Top N Populated Capital Cities in the World
        int topN = 10; // Example value for N
        List<Capital> topCapitals = capitalQuery.getTopNPopulatedCapitalCitiesInWorld(app.con, topN);
        capitalQuery.displayTopNPopulatedCapitalCitiesInWorld(topCapitals, topN);

        List<Capital> topCapitalcontinent = capitalQuery.getTopNPopulatedCapitalCitiesInContinent(app.con, "Asia",10);
        capitalQuery.displayTopNPopulatedCapitalCitiesInContinent(topCapitalcontinent, "Asia", 10);

        List<Capital> topCapitalregion = capitalQuery.getTopNPopulatedCapitalCitiesInRegion(app.con, "Eastern Asia",10);
        capitalQuery.displayTopNPopulatedCapitalCitiesInRegion(topCapitalregion, "Eastern Asia", 10);

        populationQuery populationQuery = new populationQuery();

        List<Population> populations = populationQuery.getPopulationDataByContinent(app.con);
        populationQuery.displayPopulationDataByContinent(populations);

        List<Population> regionReports = populationQuery.getPopulationDataByRegion(app.con);
        populationQuery.displayPopulationDataByRegion(regionReports);

        List<Population> countryReports = populationQuery.getPopulationDataByCountry(app.con);
        populationQuery.displayPopulationDataByCountry(countryReports);

        List<Population> worldPopulation = populationQuery.getWorldPopulationData(app.con);
        populationQuery.displayTotalWorldPopulation(worldPopulation);

        List<Population> asiaPopulation = populationQuery.getContinentPopulationData(app.con, "Asia");
        populationQuery.displayContinentPopulation(asiaPopulation, "Asia");

        List<Population> regionPopulations = populationQuery.getRegionPopulationData(app.con, "Eastern Asia");
        populationQuery.displayRegionPopulation(regionPopulations, "Eastern Asia");

        // Replace with the desired country code
        List<Population> countryPopulations = populationQuery.getCountryPopulationData(app.con, "USA");
        populationQuery.displayCountryPopulation(countryPopulations, "USA");

        // Replace with the desired district name
        List<Population> districtPopulations = populationQuery.getDistrictPopulationData(app.con,"Shandong");
        populationQuery.displayDistrictPopulation(districtPopulations, "Shandong");

        List<Population> cityPopulations = populationQuery.getCityPopulationData(app.con, "Breda");
        populationQuery.displayCityPopulation(cityPopulations, "Breda");

        List<languagepopulation> languagePopulations = populationQuery.getLanguagePopulationData(app.con);
        populationQuery.displayLanguagePopulations(languagePopulations);



        app.disconnect();     // Disconnect from the database
    }



}
