package com.example.swc.asteroids.surrounding_systems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NearEarthObjectDto {
    public LinksDto links;
    public String id;
    public String neo_reference_id;
    public String name;
    public String nasa_jpl_url;
    public String absolute_magnitude_h;
    public EstimatedDiameterDto estimated_diameter;
    public boolean is_potentially_hazardous_asteroid;
    public List<CloseApproachDataDto> close_approach_data;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public OrbitalDataDto orbital_data;
    public boolean is_sentry_object;
}
