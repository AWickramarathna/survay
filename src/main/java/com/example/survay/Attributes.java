package com.example.survay;

@javax.persistence.Entity
@lombok.Builder
@javax.persistence.Table(name = "attributes")
@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Attributes {

    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private long id;

    @javax.persistence.Column(name = "username")
    private String username;

    @javax.persistence.Column(name = "scenario")
    private String scenario;

    @javax.persistence.Column(name = "option")
    private String option;

    @javax.persistence.Column(name = "selectedOption")
    private String selectedOption;

    @javax.persistence.Column(name = "connectivity")
    private String connectivity;

    @javax.persistence.Column(name = "automobile_interaction")
    private String automobile_interaction;

    @javax.persistence.Column(name = "road_signs")
    private String road_signs;

    @javax.persistence.Column(name = "street_lightning")
    private String street_lightning;

    @javax.persistence.Column(name = "secure_parking")
    private String secure_parking;

    @javax.persistence.Column(name = "rentability")
    private String Rentability;

    @javax.persistence.Column(name = "cycle_renting")
    private String cycle_renting;

    @javax.persistence.Column(name = "tour_guide")
    private String tour_guide;

}
