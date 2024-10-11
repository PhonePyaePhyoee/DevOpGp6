//package com.napier.sem;
//
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//
//
//public class report {
//
//    // Input Handling Methods
//    public static String getContinentFromInput() throws NoSuchElementException {
//        String defaultContinent = "Asia";  // Set default continent
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the continent name for the report (default: Asia): ");
//
//        if (scanner.hasNextLine()) {
//            String input = scanner.nextLine();
//            if (input != null && !input.trim().isEmpty()) {
//                return input.trim();
//            }
//        }
//        System.out.println("No input provided. Using default continent: " + defaultContinent);
//        return defaultContinent;
//    }
//
//    public static String getRegionFromInput() throws NoSuchElementException {
//        String defaultRegion = "Eastern Asia";  // Set default region
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the region name for the report (default: Eastern Asia): ");
//
//        if (scanner.hasNextLine()) {
//            String input = scanner.nextLine();
//            if (input != null && !input.trim().isEmpty()) {
//                return input.trim();
//            }
//        }
//        System.out.println("No input provided. Using default region: " + defaultRegion);
//        return defaultRegion;
//    }
//
//    public static int getTopNFromInput() throws NoSuchElementException {
//        int defaultTopN = 5;  // Set default value for top N countries
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the number of top populated countries to display (default: 5): ");
//
//        if (scanner.hasNextLine()) {
//            String input = scanner.nextLine();
//            try {
//                int topN = Integer.parseInt(input.trim());
//                return topN;
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input. Using default value: " + defaultTopN);
//            }
//        }
//        System.out.println("No input provided. Using default value: " + defaultTopN);
//        return defaultTopN;
//    }
//
//    public static String getCountryFromInput() throws NoSuchElementException {
//        String defaultCountry = "China";  // Set default country
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the country name for the report (default: China): ");
//
//        if (scanner.hasNextLine()) {
//            String input = scanner.nextLine();
//            if (input != null && !input.trim().isEmpty()) {
//                return input.trim();
//            }
//        }
//        System.out.println("No input provided. Using default country: " + defaultCountry);
//        return defaultCountry;
//    }
//    public static String getDistrictFromInput() throws NoSuchElementException {
//        String defaultDistrict = "Shandong";  // Set default district
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the district name for the report (default: Central District): ");
//
//        if (scanner.hasNextLine()) {
//            String input = scanner.nextLine();
//            if (input != null && !input.trim().isEmpty()) {
//                return input.trim();
//            }
//        }
//        System.out.println("No input provided. Using default district: " + defaultDistrict);
//        return defaultDistrict;
//    }
//
//    public static int getTopNPopulatedCitiesInput() throws NoSuchElementException {
//        int defaultTopN = 5;  // Set default value for top N cities
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the number of top populated cities to retrieve (default: 5): ");
//
//        if (scanner.hasNextLine()) {
//            String input = scanner.nextLine();
//            try {
//                int topN = Integer.parseInt(input.trim());
//                return topN;
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input. Using default value: " + defaultTopN);
//            }
//        }
//        System.out.println("No input provided. Using default value: " + defaultTopN);
//        return defaultTopN;
//    }
//}
