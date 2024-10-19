package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class App {

    Connection con = null;

    // Method to connect to the database
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait for the specified delay
                Thread.sleep(delay);

                // Connect to the database using the passed location
                con = DriverManager.getConnection("jdbc:mysql://" + location
                        + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");

                System.out.println("Successfully connected");
                break;  // Exit the loop after successful connection
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
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
        // Create a new Application and connect to the database
        App app = new App();

        // Check if connection parameters are passed, else use defaults
        if (args.length < 2) {
            app.connect("localhost:33060", 30000);
        } else {
            app.connect(args[0], Integer.parseInt(args[1]));
        }

        try {
            // Initialize and use countryQuery class
            countryQuery countryQuery = new countryQuery();

            List<Country> countries = countryQuery.getAllCountriesOrderedByPopulation(app.con);
            countryQuery.displayAllCountriesOrderedByPopulation(countries);

            List<Country> countries1 = countryQuery.getCountriesByContinentOrderedByPopulation(app.con, "Asia");
            countryQuery.displayCountriesByContinentOrderedByPopulation(countries1, "Asia");

            List<Country> countries2 = countryQuery.getCountriesByRegionOrderedByPopulation(app.con, "Western Africa");
            countryQuery.displayCountriesByRegionOrderedByPopulation(countries2, "Western Africa");

            List<Country> countries3 = countryQuery.getTopNPopulatedCountriesInWorld(app.con, 5);
            countryQuery.displayTopNPopulatedCountriesInWorld(countries3, 5);

//            List<Country> countries4 = countryQuery.getTopNPopulatedCountriesInContinent(app.con, "Asia", 5);
//            countryQuery.displayTopNPopulatedCountriesInContinent(countries4, "Asia", 5);
//
//            List<Country> countries5 = countryQuery.getTopNPopulatedCountriesInRegion(app.con, "Western Africa", 5);
//            countryQuery.displayTopNPopulatedCountriesInRegion(countries5, "Western Africa", 5);
//
//            // Initialize and use cityQuery class
            cityQuery cityQuery = new cityQuery();

            List<City> cities = cityQuery.getAllCitiesOrderedByPopulation(app.con);
            cityQuery.displayAllCitiesOrderedByPopulation(cities);

            List<City> resultCityContinent = cityQuery.getCitiesByContinentOrderedByPopulation(app.con, "Asia");
            cityQuery.displayCitiesByContinentOrderedByPopulation(resultCityContinent, "Asia");

            List<City> resultCityRegion = cityQuery.getCitiesByRegionOrderedByPopulation(app.con, "Europe");
            cityQuery.displayCitiesByRegionOrderedByPopulation(resultCityRegion, "Europe");

            List<City> resultCityCountry = cityQuery.getCitiesByCountryOrderedByPopulation(app.con, "China");
            cityQuery.displayCitiesByCountryOrderedByPopulation(resultCityCountry, "China");

            List<City> resultCityDistrict = cityQuery.getCitiesByDistrictOrderedByPopulation(app.con, "Shandong");
            cityQuery.displayCitiesByDistrictOrderedByPopulation(resultCityDistrict, "Shandong");

            List<City> resultTopNWorldCities = cityQuery.getTopNPopulatedCitiesInWorld(app.con, 10);
            cityQuery.displayTopNPopulatedCitiesInWorld(resultTopNWorldCities, 10);

//            List<City> resultTopNContinentCities = cityQuery.getTopNPopulatedCitiesInContinent(app.con, "Asia", 5);
//            cityQuery.displayTopNPopulatedCitiesInContinent(resultTopNContinentCities, "Asia", 5);
//
//            List<City> resultTopNRegionCities = cityQuery.getTopNPopulatedCitiesInRegion(app.con, "Eastern Asia", 5);
//            cityQuery.displayTopNPopulatedCitiesInRegion(resultTopNRegionCities, "Eastern Asia", 5);
//
//            List<City> resultTopNCountryCities = cityQuery.getTopNPopulatedCitiesInCountry(app.con, "China", 5);
//            cityQuery.displayTopNPopulatedCitiesInCountry(resultTopNCountryCities, "China", 5);
//
//            List<City> resultTopNDistrictCities = cityQuery.getTopNPopulatedCitiesInDistrict(app.con, "Shandong", 5);
//            cityQuery.displayTopNPopulatedCitiesInDistrict(resultTopNDistrictCities, "Shandong", 5);
//
//            // Initialize and use capitalQuery class
            capitalQuery capitalQuery = new capitalQuery();

            List<Capital> capitals = capitalQuery.getAllCapitalCitiesOrderedByPopulation(app.con);
            capitalQuery.displayAllCapitalCitiesOrderedByPopulation(capitals);

            List<Capital> capitalsByContinent = capitalQuery.getCapitalCitiesByContinentOrderedByPopulation(app.con, "Asia");
            capitalQuery.displayCapitalCitiesByContinentOrderedByPopulation(capitalsByContinent, "Asia");

            List<Capital> capitalsByRegion = capitalQuery.getCapitalCitiesByRegionOrderedByPopulation(app.con, "Eastern Asia");
            capitalQuery.displayCapitalCitiesByRegionOrderedByPopulation(capitalsByRegion, "Eastern Asia");

            List<Capital> topCapitalsInWorld = capitalQuery.getTopNPopulatedCapitalCitiesInWorld(app.con, 10);
            capitalQuery.displayTopNPopulatedCapitalCitiesInWorld(topCapitalsInWorld, 10);
//
//            List<Capital> topCapitalsInContinent = capitalQuery.getTopNPopulatedCapitalCitiesInContinent(app.con, "Asia", 10);
//            capitalQuery.displayTopNPopulatedCapitalCitiesInContinent(topCapitalsInContinent, "Asia", 10);
//
//            List<Capital> topCapitalsInRegion = capitalQuery.getTopNPopulatedCapitalCitiesInRegion(app.con, "Eastern Asia", 10);
//            capitalQuery.displayTopNPopulatedCapitalCitiesInRegion(topCapitalsInRegion, "Eastern Asia", 10);

            // Initialize and use populationQuery class
            populationQuery populationQuery = new populationQuery();

            List<Population> populationsByContinent = populationQuery.getPopulationDataByContinent(app.con);
            populationQuery.displayPopulationDataByContinent(populationsByContinent);

            List<Population> populationsByRegion = populationQuery.getPopulationDataByRegion(app.con);
            populationQuery.displayPopulationDataByRegion(populationsByRegion);

            List<Population> populationsByCountry = populationQuery.getPopulationDataByCountry(app.con);
            populationQuery.displayPopulationDataByCountry(populationsByCountry);

            List<Population> totalWorldPopulation = populationQuery.getWorldPopulationData(app.con);
            populationQuery.displayTotalWorldPopulation(totalWorldPopulation);

            List<Population> asiaPopulation = populationQuery.getContinentPopulationData(app.con, "Asia");
            populationQuery.displayContinentPopulation(asiaPopulation, "Asia");

            List<Population> easternAsiaPopulation = populationQuery.getRegionPopulationData(app.con, "Eastern Asia");
            populationQuery.displayRegionPopulation(easternAsiaPopulation, "Eastern Asia");

            List<Population> usaPopulation = populationQuery.getCountryPopulationData(app.con, "USA");
            populationQuery.displayCountryPopulation(usaPopulation, "USA");

            List<Population> shandongPopulation = populationQuery.getDistrictPopulationData(app.con, "Shandong");
            populationQuery.displayDistrictPopulation(shandongPopulation, "Shandong");

            List<Population> bredaPopulation = populationQuery.getCityPopulationData(app.con, "Breda");
            populationQuery.displayCityPopulation(bredaPopulation, "Breda");

            List<languagepopulation> languagePopulations = populationQuery.getLanguagePopulationData(app.con);
            populationQuery.displayLanguagePopulations(languagePopulations);

        } finally {
            // Disconnect from the database
            app.disconnect();
        }
    }


}
