package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Bim;

@Repository
public interface BimRepository extends JpaRepository<Bim, Long> {

}
