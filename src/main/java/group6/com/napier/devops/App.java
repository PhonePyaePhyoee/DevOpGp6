package group6.com.napier.devops;

import java.sql.*;
import java.text.NumberFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection to the database
        Connection con = null;
        int retries = 100;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");

                // Generate country report (Query 1)
                generateCountryReport(con);

                // Check if input is available for continent (Query 2)
                String continent = getContinentFromInput();
                generateCountryReportByContinent(con, continent);

                // Check if input is available for region (Query 3)
                String region = getRegionFromInput();
                generateCountryReportByRegion(con, region);

                // Get top N populated countries in the world (Query 4)
                int topN = getTopNFromInput();
                generateTopNPopulatedCountries(con, topN);

                // Exit for loop
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    // Input Handling Methods
    public static <NoSuchElementException extends Throwable> String getContinentFromInput() throws NoSuchElementException {
        String defaultContinent = "Asia";  // Set default continent
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the continent name for the report (default: Asia): ");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input.trim();
            }
        }
        System.out.println("No input provided. Using default continent: " + defaultContinent);
        return defaultContinent;
    }

    public static <NoSuchElementException extends Throwable> String getRegionFromInput() throws NoSuchElementException {
        String defaultRegion = "Eastern Asia";  // Set default region
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the region name for the report (default: Eastern Asia): ");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input.trim();
            }
        }
        System.out.println("No input provided. Using default region: " + defaultRegion);
        return defaultRegion;
    }

    public static <NoSuchElementException extends Throwable> int getTopNFromInput() throws NoSuchElementException {
        int defaultTopN = 5;  // Set default value for top N countries
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of top populated countries to display (default: 5): ");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                int topN = Integer.parseInt(input.trim());
                return topN;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Using default value: " + defaultTopN);
            }
        }
        System.out.println("No input provided. Using default value: " + defaultTopN);
        return defaultTopN;
    }

    // Query 1: All countries in the world ordered by population (with city name for capital)
    public static void generateCountryReport(Connection con)
    {
        try
        {
            Statement stmt = con.createStatement();
            String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName "
                    + "FROM country "
                    + "LEFT JOIN city ON country.Capital = city.ID "
                    + "ORDER BY country.Population DESC";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Country Report (All Countries by Population):");
            System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s | %-20s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

            NumberFormat numberFormat = NumberFormat.getInstance();  // Formatter for population
            while (rs.next())
            {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String continent = rs.getString("Continent");
                String region = rs.getString("Region");
                int population = rs.getInt("Population");
                String populationFormatted = numberFormat.format(population); // Format population
                String capitalName = rs.getString("CapitalName");

                System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s | %-20s", code, name, continent, region, populationFormatted, capitalName));
            }
            rs.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error retrieving country data.");
            e.printStackTrace();
        }
    }

    // Query 2: All countries in a continent ordered by population (with city name for capital)
    public static void generateCountryReportByContinent(Connection con, String continent)
    {
        try
        {
            Statement stmt = con.createStatement();
            String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName "
                    + "FROM country "
                    + "LEFT JOIN city ON country.Capital = city.ID "
                    + "WHERE country.Continent = '" + continent + "' "
                    + "ORDER BY country.Population DESC";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\nCountry Report (Countries in " + continent + " by Population):");
            System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s | %-20s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

            NumberFormat numberFormat = NumberFormat.getInstance();
            while (rs.next())
            {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String region = rs.getString("Region");
                int population = rs.getInt("Population");
                String populationFormatted = numberFormat.format(population);
                String capitalName = rs.getString("CapitalName");

                System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s | %-20s", code, name, continent, region, populationFormatted, capitalName));
            }
            rs.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error retrieving country data for the continent.");
            e.printStackTrace();
        }
    }

    // Query 3: All countries in a region ordered by population (with city name for capital)
    public static void generateCountryReportByRegion(Connection con, String region)
    {
        try
        {
            Statement stmt = con.createStatement();
            String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName "
                    + "FROM country "
                    + "LEFT JOIN city ON country.Capital = city.ID "
                    + "WHERE country.Region = '" + region + "' "
                    + "ORDER BY country.Population DESC";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\nCountry Report (Countries in " + region + " by Population):");
            System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s | %-20s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

            NumberFormat numberFormat = NumberFormat.getInstance();
            while (rs.next())
            {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String continent = rs.getString("Continent");
                int population = rs.getInt("Population");
                String populationFormatted = numberFormat.format(population);
                String capitalName = rs.getString("CapitalName");

                System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s | %-20s", code, name, continent, region, populationFormatted, capitalName));
            }
            rs.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error retrieving country data for the region.");
            e.printStackTrace();
        }
    }

    // Query 4: The top N populated countries in the world (with city name for capital)
    public static void generateTopNPopulatedCountries(Connection con, int topN)
    {
        try
        {
            Statement stmt = con.createStatement();
            String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName "
                    + "FROM country "
                    + "LEFT JOIN city ON country.Capital = city.ID "
                    + "ORDER BY country.Population DESC LIMIT " + topN;
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\nTop " + topN + " Populated Countries in the World:");
            System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s | %-20s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

            NumberFormat numberFormat = NumberFormat.getInstance();
            while (rs.next())
            {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String continent = rs.getString("Continent");
                String region = rs.getString("Region");
                int population = rs.getInt("Population");
                String populationFormatted = numberFormat.format(population);
                String capitalName = rs.getString("CapitalName");

                System.out.println(String.format("%-5s | %-40s | %-15s | %-25s | %-15s | %-20s", code, name, continent, region, populationFormatted, capitalName));
            }
            rs.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error retrieving top " + topN + " populated countries.");
            e.printStackTrace();
        }
    }
}
