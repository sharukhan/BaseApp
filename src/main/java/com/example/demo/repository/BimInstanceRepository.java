package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BimInstance;

@Repository
public interface BimInstanceRepository extends JpaRepository<BimInstance,Long> {

}
