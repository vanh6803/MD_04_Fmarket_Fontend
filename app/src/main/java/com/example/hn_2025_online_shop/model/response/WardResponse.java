package com.example.hn_2025_online_shop.model.response;

import com.example.hn_2025_online_shop.model.Ward;

import java.util.List;

public class WardResponse {
    private List<Ward> results;


    public WardResponse() {
    }

    @Override
    public String toString() {
        return "WardResponse{" +
                "results=" + results +
                '}';
    }

    public List<Ward> getResults() {
        return results;
    }

    public void setResults(List<Ward> results) {
        this.results = results;
    }
}
