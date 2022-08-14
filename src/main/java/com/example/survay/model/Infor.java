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
    private String status;
    private String faculty;
    private String income;
}
