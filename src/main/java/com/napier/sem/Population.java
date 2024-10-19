package com.napier.sem;

public class Population {
    private String continent;
    private long totalPopulation;      // Use long for large population numbers
    private long wholiveincities;      // Use long for city-dwelling population
    private long whonotliveincities;   // Use long for non-city-dwelling population

    public Population(String continent, long totalPopulation, long wholiveincities, long whonotliveincities) {
        this.continent = continent;
        this.totalPopulation = totalPopulation;
        this.wholiveincities = wholiveincities;
        this.whonotliveincities = whonotliveincities;
    }

    // Getters and Setters
    public String getName() {
        return continent;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }

    public long getlivePopulation() {
        return wholiveincities;
    }

    public long getnotlivePopulation() {
        return whonotliveincities;
    }

    public double getlivePercentage() {
        // Calculate percentage of population living in cities
        return totalPopulation > 0 ? (wholiveincities / (double) totalPopulation) * 100 : 0;
    }


}
