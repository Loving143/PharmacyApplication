package com.pharmacy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer>{

	Optional<Role> findByName(String string);

}
