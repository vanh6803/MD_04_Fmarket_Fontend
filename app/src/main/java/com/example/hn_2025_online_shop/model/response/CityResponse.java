package com.example.hn_2025_online_shop.model.response;

import androidx.annotation.NonNull;

import com.example.hn_2025_online_shop.model.City;

import java.util.List;

public class CityResponse {
    private List<City> results;

    public CityResponse() {
    }

    @Override
    public String toString() {
        return "CityResponse{" +
                "results=" + results +
                '}';
    }

    public List<City> getResults() {
        return results;
    }

    public void setResults(List<City> results) {
        this.results = results;
    }
}
