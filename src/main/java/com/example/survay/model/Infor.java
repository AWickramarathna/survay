package com.example.survay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@javax.persistence.Entity
@lombok.Builder
@javax.persistence.Table(name = "infor")
@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Infor {
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private long id;

    @javax.persistence.Column(name = "gender")
    private String gender;

    @javax.persistence.Column(name = "nationalty")
    private String nationalty;

    @javax.persistence.Column(name = "province")
    private String province;

    @javax.persistence.Column(name = "region")
    private String region;

    @javax.persistence.Column(name = "age")
    private String age;

    @javax.persistence.Column(name = "job")
    private String job;

    @javax.persistence.Column(name = "visits")
    private String visits;

    @javax.persistence.Column(name = "stay")
    private String stay;

    @javax.persistence.Column(name = "exp")
    private String exp;

    @javax.persistence.Column(name = "username")
    private String username;

    @javax.persistence.Column(name = "income")
    private String income;
}
