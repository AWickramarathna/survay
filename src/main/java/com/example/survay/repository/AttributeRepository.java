package com.example.survay.repository;

import com.example.survay.Attributes;

import java.util.List;

@org.springframework.stereotype.Repository
public interface AttributeRepository extends org.springframework.data.jpa.repository.JpaRepository<com.example.survay.Attributes, Long> {

    com.example.survay.Attributes findByUsernameAndOptionAndScenario(String user_key,String option,String s);

    List<Attributes> findAllByUsername(String username);
}
