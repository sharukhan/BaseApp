package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>{

}
