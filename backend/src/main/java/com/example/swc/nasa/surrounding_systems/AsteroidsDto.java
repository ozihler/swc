package com.example.swc.nasa.surrounding_systems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

class LinksDto {
    public String next;
    public String prev;
    public String self;
}

class DiameterDto {
    public double estimated_diameter_min;
    public double estimated_diameter_max;
}

class EstimatedDiameterDto {
    public DiameterDto kilometers;
    public DiameterDto meters;
    public DiameterDto miles;
    public DiameterDto feet;
}

class RelativeVelocityDto {
    public String kilometers_per_second;
    public String kilometers_per_hour;
    public String miles_per_hour;
}

class MissDistanceDto {
    public String astronomical;
    public String lunar;
    public String kilometers;
    public String miles;
}

class CloseApproachDataDto {
    public String close_approach_date;
    public String close_approach_date_full;
    public long epoch_date_close_approach;
    public RelativeVelocityDto relative_velocity;
    public MissDistanceDto miss_distance;
    public String orbiting_body;
}

class OrbitalClassDto {
    public String orbit_class_type;
    public String orbit_class_description;
    public String orbit_class_range;
}

class OrbitalDataDto {
    public String orbit_id;
    public String orbit_determination_date;
    public String first_observation_date;
    public String last_observation_date;
    public int data_arc_in_days;
    public int observations_used;
    public String orbit_uncertainty;
    public String minimum_orbit_intersection;
    public String jupiter_tisserand_invariant;
    public String epoc_osculation;
    public String eccentricity;
    public String semi_major_axis;
    public String inclination;
    public String ascending_node_longitude;
    public String orbital_period;
    public String perihelion_distance;
    public String perihelion_argument;
    public String aphelion_distance;
    public String perihelion_time;
    public String mean_anomaly;
    public String mean_motion;
    public String equinox;
    public OrbitalClassDto oribital_class;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class NearEarthObjectDto {
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

public class AsteroidsDto {
    public LinksDto links;
    public int element_count;
    public Map<String, List<NearEarthObjectDto>> near_earth_objects;
}

