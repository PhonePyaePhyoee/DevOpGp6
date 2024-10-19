package com.napier.sem;

public class City {
    private String name, country,district;
    private long population;


    public City(String name, String country, String district, int population) {
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }




    public String getName() {
        return name;
    }
    public String getCountry() {
        return country;
    }
    public String getDistrict() {
        return district;
    }
    public long getPopulation() {
        return population;
    }
}

