package com.example.hn_2025_online_shop.model.response;

import com.example.hn_2025_online_shop.model.District;

import java.util.List;

public class DistrictResponse {
    private List<District> results;

    public DistrictResponse() {
    }

    @Override
    public String toString() {
        return "DistrictResponse{" +
                "results=" + results +
                '}';
    }

    public List<District> getResults() {
        return results;
    }

    public void setResults(List<District> results) {
        this.results = results;
    }
}
