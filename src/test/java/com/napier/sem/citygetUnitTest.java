//package com.napier.sem;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class citygetUnitTest {
//
//    @Test
//    public void testCityConstructorAndGetters() {
//        // Create a new City object using the constructor
//        City city = new City("Tokyo", "Japan", "Kanto", 37393128);
//
//        // Test the constructor and getters
//        assertEquals("Tokyo", city.getName());
//        assertEquals("Japan", city.getCountry());
//        assertEquals("Kanto", city.getDistrict());
//        assertEquals(37393128, city.getPopulation());
//    }
//
//    @Test
//    public void testSetName() {
//        // Create a new City object
//        City city = new City("Tokyo", "Japan", "Kanto", 37393128);
//
//        // Set a new name
//        city.setName("Osaka");
//
//        // Test the setter and getter for name
//        assertEquals("Osaka", city.getName());
//    }
//
//    @Test
//    public void testSetCountry() {
//        // Create a new City object
//        City city = new City("Tokyo", "Japan", "Kanto", 37393128);
//
//        // Set a new country
//        city.setCountry("Brazil");
//
//        // Test the setter and getter for country
//        assertEquals("Brazil", city.getCountry());
//    }
//
//    @Test
//    public void testSetDistrict() {
//        // Create a new City object
//        City city = new City("Tokyo", "Japan", "Kanto", 37393128);
//
//        // Set a new district
//        city.setDistrict("Osaka");
//
//        // Test the setter and getter for district
//        assertEquals("Osaka", city.getDistrict());
//    }
//
//    @Test
//    public void testSetPopulation() {
//        // Create a new City object
//        City city = new City("Tokyo", "Japan", "Kanto", 37393128);
//
//        // Set a new population
//        city.setPopulation(5000000);
//
//        // Test the setter and getter for population
//        assertEquals(5000000, city.getPopulation());
//    }
//}
