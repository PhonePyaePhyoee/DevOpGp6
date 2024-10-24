package com.napier.sem;

public class Capital {
    private String name;
    private String country;
    private long population;

    public Capital(String name, String country, int population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public long getPopulation() {
        return population;
    }
}
