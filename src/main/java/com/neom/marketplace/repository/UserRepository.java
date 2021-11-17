package com.neom.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neom.marketplace.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
