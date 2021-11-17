package com.neom.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neom.marketplace.model.BimInstance;

@Repository
public interface BimInstanceRepository extends JpaRepository<BimInstance,Long> {

}
