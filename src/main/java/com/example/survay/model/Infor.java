package com.example.survay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Infor {
    private String gender;
    private String nationalty;
    private String province;
    private String region;
    private String age;
    private String job;
    private String visits;
    private String stay;
    private String exp;
    private String username;
    private String income;
}
