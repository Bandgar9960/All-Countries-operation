package com.neo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.entity.Application;

public interface CountryRepository extends JpaRepository<Application, Integer> {
	
	

}
