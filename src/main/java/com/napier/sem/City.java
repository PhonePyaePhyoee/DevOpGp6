package com.napier.sem;

public class City {
    private String name, country,district;
    private int population;
    public City(String name, String country, String district, int population) {
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setPopulation(int population) {
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
    public int getPopulation() {
        return population;
    }
}

