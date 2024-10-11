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
    public static void main(String[] args) {
        App app = new App();  // Create an instance of App class
        app.connect();        // Connect to the database

        // Call countryQuery and retrieve countries ordered by population
        countryQuery countryQuery = new countryQuery();  // Create an instance of countryQuery

        try {
            // Retrieve and display all countries ordered by population
            List<Country> countries = countryQuery.getAllCountriesOrderedByPopulation(app.con);
            for (Country country : countries) {
                System.out.println("Country: " + country.getName() + ", Population: " + country.getPopulation());
            }
        } catch (SQLException e) {
            System.out.println("Error fetching countries: " + e.getMessage());
        }
        try {
            String continent = "Asia";  // Example continent; can be dynamic based on user input
            List<Country> countriesByContinent = countryQuery.getCountriesByContinentOrderedByPopulation(app.con, continent);
            for (Country country : countriesByContinent) {
                System.out.println(country.getName() + ", " + country.getContinent() + ", " + country.getRegion() + ", Population: " + country.getPopulation());
            }

        } catch (SQLException e) {
            System.out.println("Error fetching countries: " + e.getMessage());
        }
        try {
            String region = "Eastern Asia";  // Example region, can be dynamic
            List<Country> countriesByRegion = countryQuery.getCountriesByRegionOrderedByPopulation(app.con, region);
            for (Country country : countriesByRegion) {
                System.out.println("Country: " + country.getName() + ", Population: " + country.getPopulation());
            }
        } catch (SQLException e) {
            System.out.println("Error fetching countries by region: " + e.getMessage());
        }
        try {
            int N = 5;  // Example N value, can be dynamic
            List<Country> topCountriesInWorld = countryQuery.getTopNPopulatedCountriesInWorld(app.con, N);
            for (Country country : topCountriesInWorld) {
                System.out.println("Country: " + country.getName() + ", Population: " + country.getPopulation());
            }
        } catch (SQLException e) {
            System.out.println("Error fetching top countries in the world: " + e.getMessage());
        }

        try {
            int N = 5;  // Example N value, can be dynamic
            String continent = "Asia";  // Example continent, can be dynamic
            List<Country> topCountriesInContinent = countryQuery.getTopNPopulatedCountriesInContinent(app.con, continent, N);
            for (Country country : topCountriesInContinent) {
                System.out.println("Country: " + country.getName() + ", Population: " + country.getPopulation());
            }
        } catch (SQLException e) {
            System.out.println("Error fetching top countries in the continent: " + e.getMessage());
        }

        try {
            String region = "Eastern Asia";  // Example region, can be dynamic
            int N = 5;  // Example N value, can be dynamic
            List<Country> topCountriesInRegion = countryQuery.getTopNPopulatedCountriesInRegion(app.con, region, N);
            for (Country country : topCountriesInRegion) {
                System.out.println("Country: " + country.getName() + ", Population: " + country.getPopulation());
            }
        } catch (SQLException e) {
            System.out.println("Error fetching top countries by region: " + e.getMessage());
        }

        capitalQuery capitalQuery = new capitalQuery();  // Create an instance of capitalQuery

        try {
            // Retrieve and display all capital cities ordered by population
            List<Capital> capitalsInWorld = capitalQuery.getAllCapitalCitiesOrderedByPopulation(app.con);
            System.out.println(capitalsInWorld);
            // The method will print results internally

        } catch (SQLException e) {
            System.out.println("Error fetching capital cities: " + e.getMessage());
        }
        try {
            String continent = "Asia";  // Example continent, can be dynamic
            List<Capital> capitalsByContinent = capitalQuery.getCapitalCitiesByContinentOrderedByPopulation(app.con, continent);
            for (Capital capital : capitalsByContinent) {
                System.out.println("Capital: " + capital.getName() + ", Country: " + capital.getCountry() + ", Population: " + capital.getPopulation());
            }
        } catch (SQLException e) {
            System.out.println("Error fetching capital cities by continent: " + e.getMessage());
        }
        try {
            String region = "Eastern Asia";  // Example region, can be dynamic
            List<Capital> capitalsByRegion = capitalQuery.getCapitalCitiesByRegionOrderedByPopulation(app.con, region);
            for (Capital capital : capitalsByRegion) {
                System.out.println("Capital: " + capital.getName() + ", Country: " + capital.getCountry() + ", Population: " + capital.getPopulation());
            }
        } catch (SQLException e) {
            System.out.println("Error fetching capital cities by region: " + e.getMessage());
        }
        try {
            int N = 10;  // Example N, can be dynamic based on user input
            List<Capital> topCapitalsInWorld = capitalQuery.getTopNPopulatedCapitalCitiesInWorld(app.con, N);
            for (Capital capital : topCapitalsInWorld) {
                System.out.println("Capital: " + capital.getName() + ", Country: " + capital.getCountry() + ", Population: " + capital.getPopulation());
            }
        } catch (SQLException e) {
            System.out.println("Error fetching top capital cities: " + e.getMessage());
        }
        // Create an instance of cityQuery
        cityQuery cityQuery = new cityQuery();

        try {
            // Retrieve and display all cities ordered by population
            List<City> cities = cityQuery.getAllCitiesOrderedByPopulation(app.con);
            for (City city : cities) {
                System.out.println(city.getName() + ", " + city.getCountry() + ", " + city.getDistrict() + ", Population: " + city.getPopulation());
            }

            // Retrieve and display cities by continent ordered by population
            String continent = "Asia";  // Example continent, can be dynamic
            List<City> citiesByContinent = cityQuery.getCitiesByContinentOrderedByPopulation(app.con, continent);
            for (City city : citiesByContinent) {
                System.out.println(city.getName() + ", " + city.getCountry() + ", " + city.getDistrict() + ", Population: " + city.getPopulation());
            }

            // Retrieve cities by region ordered by population
            String region = "Eastern Asia";  // Example region, can be dynamic
            List<City> citiesByRegion = cityQuery.getCitiesByRegionOrderedByPopulation(app.con, region);
            for (City city : citiesByRegion) {
                System.out.println(city.getName() + ", " + city.getCountry() + ", " + city.getDistrict() + ", Population: " + city.getPopulation());
            }

            // Retrieve cities by country ordered by population
            String country = "China";  // Example country, can be dynamic
            List<City> citiesByCountry = cityQuery.getCitiesByCountryOrderedByPopulation(app.con, country);
            for (City city : citiesByCountry) {
                System.out.println(city.getName() + ", " + city.getCountry() + ", " + city.getDistrict() + ", Population: " + city.getPopulation());
            }

            // Retrieve cities by district ordered by population
            String district = "Shandong";  // Example district, can be dynamic
            List<City> citiesByDistrict = cityQuery.getCitiesByDistrictOrderedByPopulation(app.con, district);
            for (City city : citiesByDistrict) {
                System.out.println(city.getName() + ", " + city.getCountry() + ", " + city.getDistrict() + ", Population: " + city.getPopulation());
            }

            // Retrieve the top N populated cities in the world, where N is user-defined
            int N = 5; // Example N value, this can be dynamic based on user input later
            try {
                List<City> topCitiesInWorld = cityQuery.getTopNPopulatedCitiesInWorld(app.con, N);
                // The method will print results internally
                System.out.println(topCitiesInWorld);
            } catch (SQLException e) {
                System.out.println("Error fetching top populated cities: " + e.getMessage());
            }

            // Default values
            // Retrieve and display the top N populated cities in the continent (using default values)
            try {
                List<City> topCitiesInContinent = cityQuery.getTopNPopulatedCitiesInContinent(app.con);
                // Optional: Process the list if needed
                // For example, you can print the number of cities retrieved
                System.out.println("\nTotal cities retrieved: " + topCitiesInContinent.size());

                // If you want to loop through and do something with the list
                for (City city : topCitiesInContinent) {
                    // Example action: print city details
                    System.out.println("City Name: " + city.getName() + ", Population: " + city.getPopulation());
                }
            } catch (SQLException e) {
                System.out.println("Error fetching top populated cities: " + e.getMessage());
            }
            try {
                List<City> topCitiesInRegion = cityQuery.getTopNPopulatedCitiesInRegion(app.con);
                System.out.println("\nTotal cities retrieved: " + topCitiesInRegion.size());
                for (City city : topCitiesInRegion) {
                    System.out.println("City Name: " + city.getName() + ", Population: " + city.getPopulation());
                }
            }catch (SQLException e) {
                System.out.println("Error fetching top populated cities: " + e.getMessage());
            }
            try {

                // Retrieve and display the top N populated cities in the default country
                List<City> topCitiesInCountry = cityQuery.getTopNPopulatedCitiesInCountry(app.con);
                // The method will print results internally
                System.out.println("\nTotal cities retrieved: " + topCitiesInCountry.size());
                for (City city : topCitiesInCountry) {
                    System.out.println("City Name: " + city.getName() + ", Population: " + city.getPopulation());
                }

            } catch (SQLException e) {
                System.out.println("Error fetching cities: " + e.getMessage());
            }
            try {
                List<City> topCitiesInDistract  = cityQuery.getTopNPopulatedCitiesInDistrict(app.con);
                System.out.println("\nTotal cities retrieved: " + topCitiesInDistract.size());
                for (City city : topCitiesInDistract) {
                    System.out.println("City Name: " + city.getName() + ", Population: " + city.getPopulation());
                }

            }catch (SQLException e) {
                System.out.println("Error fetching cities: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error fetching cities: " + e.getMessage());
        }


        app.disconnect();     // Disconnect from the database
    }
}
