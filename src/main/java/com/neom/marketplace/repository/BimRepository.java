package com.neom.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neom.marketplace.model.Bim;

@Repository
public interface BimRepository extends JpaRepository<Bim, Long> {

}
