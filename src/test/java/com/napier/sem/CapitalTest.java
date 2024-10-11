package com.napier.sem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CapitalTest {

    @Test
    public void testCapitalConstructorAndGetters() {
        // Create a new Capital object using the constructor
        Capital capital = new Capital("Berlin", "Germany", 3748148);

        // Test the constructor and getters
        assertEquals("Berlin", capital.getName());
        assertEquals("Germany", capital.getCountry());
        assertEquals(3748148, capital.getPopulation());
    }

    @Test
    public void testCapitalWithDifferentValues() {
        // Create another Capital object with different values
        Capital capital = new Capital("Canberra", "Australia", 462000);

        // Test the constructor and getters
        assertEquals("Canberra", capital.getName());
        assertEquals("Australia", capital.getCountry());
        assertEquals(462000, capital.getPopulation());
    }
}
