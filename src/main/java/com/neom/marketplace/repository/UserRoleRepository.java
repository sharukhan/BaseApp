package com.neom.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neom.marketplace.model.Role;

@Repository
public interface UserRoleRepository extends JpaRepository<Role,Long>{

}
