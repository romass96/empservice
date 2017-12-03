package com.employeeservice.model;

import java.util.List;

public class Country {
    private Long id;
    private String name;
    private List<Region> regions;

    public Country() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    @Override
    public String toString() {
        return String.format("Country{id = %d, name = %s}", id, name);
    }
}
