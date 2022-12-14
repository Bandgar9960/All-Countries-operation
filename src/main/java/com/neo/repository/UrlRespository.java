package com.neo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.entity.UrlUtil;

public interface UrlRespository extends JpaRepository<UrlUtil, Integer> {
	
	
	UrlUtil findByName(String name);

}
