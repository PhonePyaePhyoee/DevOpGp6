package com.napier.sem;

// Class to hold language population data
public class languagepopulation {
    private String language;
    private long totalSpeakers;
    private double percentage;

    public languagepopulation(String language, long totalSpeakers, double percentage) {
        this.language = language;
        this.totalSpeakers = totalSpeakers;
        this.percentage = percentage;
    }

    public String getLanguage() {
        return language;
    }

    public long getTotalSpeakers() {
        return totalSpeakers;
    }

    public double getPercentage() {
        return percentage;
    }
}
