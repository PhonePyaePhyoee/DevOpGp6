package com.napier.sem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class populationUnitTest {

    @Test
    public void testConstructorAndGetters() {
        // Create a Population object
        Population population = new Population("Asia", 4500000000L, 2500000000L, 2000000000L);

        // Test getters
        assertEquals("Asia", population.getName());
        assertEquals(4500000000L, population.getTotalPopulation());
        assertEquals(2500000000L, population.getlivePopulation());
        assertEquals(2000000000L, population.getnotlivePopulation());
    }

    @Test
    public void testGetlivePercentage() {
        // Create a Population object
        Population population = new Population("Asia", 4500000000L, 2500000000L, 2000000000L);

        // Calculate expected live percentage
        double expectedPercentage = (2500000000L / 4500000000.0) * 100;

        // Test live percentage calculation
        assertEquals(expectedPercentage, population.getlivePercentage(), 0.0001);
    }

    @Test
    public void testGetlivePercentageWithZeroTotalPopulation() {
        // Create a Population object with 0 total population
        Population population = new Population("Unknown", 0L, 0L, 0L);

        // Test live percentage should be 0 when total population is 0
        assertEquals(0, population.getlivePercentage(), 0.0001);
    }
}
