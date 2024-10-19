package com.napier.sem;

public class Country {
    private final String Code;
    private final String Name;
    private final String Continent;
    private final String Region;
    private final String Capital;  // Assuming this should be a String
    private final int Population;


    public Country(String code, String name, String continent, String region, String capital, int population) {
        Code = code;
        Name = name;
        Continent = continent;
        Region = region;
        Capital = capital;
        Population = population;
    }






    public String getCode() {
        return Code;
    }

    public String getName() {
        return Name;
    }

    public String getContinent() {
        return Continent;
    }

    public String getRegion() {
        return Region;
    }

    public String getCapital() {
        return Capital;
    }

    public int getPopulation() {
        return Population;
    }
}


