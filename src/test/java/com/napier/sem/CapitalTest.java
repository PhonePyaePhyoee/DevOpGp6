package com.napier.sem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CapitalTest {

    @Test
    void testCapitalConstructorAndGetters() {
        // Arrange: Create a new Capital object
        String name = "Washington DC";
        String country = "USA";
        long population = 705749;  // Use long for consistency with the class definition

        Capital capital = new Capital(name, country, (int) population); // Cast to int for constructor

        // Act: Retrieve the values using getters
        String actualName = capital.getName();
        String actualCountry = capital.getCountry();
        long actualPopulation = capital.getPopulation();

        // Assert: Verify that the values are as expected
        assertEquals(name, actualName, "Capital name should match");
        assertEquals(country, actualCountry, "Capital country should match");
        assertEquals(population, actualPopulation, "Capital population should match");
    }
}
