package com.neo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.neo.entity.Application;
import com.neo.entity.Country;
import com.neo.entity.UrlUtil;
import com.neo.service.CountryService;
import com.neo.service.UrlUtilityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CountryController {

	@Autowired
	private UrlUtilityService urlUtilityService;

	@Autowired
	private CountryService countryService;

	@GetMapping("/countries")
	public ResponseEntity<List<Country>> get() {
		List<Country> getcountries = countryService.getcountries();
		return new ResponseEntity<>(getcountries, HttpStatus.OK);

	}
     

	@GetMapping("/{name}")
	public ResponseEntity<Country> getSingle(@PathVariable String name) {

		Country singleCountry = countryService.getSingleCountry(name);

		return new ResponseEntity<>(singleCountry, HttpStatus.OK);
	}

	@GetMapping("/countrieskey")
	public ResponseEntity<Set<String>> getAllKeys() throws JsonMappingException, JsonProcessingException {

		Set<String> onlyKeys = countryService.getAllOnlyKeys();

		return new ResponseEntity<>(onlyKeys, HttpStatus.OK);

	}

	@PostMapping("/url")
	public ResponseEntity<UrlUtil> saveUrl(@RequestBody UrlUtil urlUtil) {
		UrlUtil saveUrl = urlUtilityService.saveUrl(urlUtil);
		return new ResponseEntity<>(saveUrl, HttpStatus.CREATED);

	}

	@PostMapping("/save")
	public ResponseEntity<List<Application>> create(@Valid @RequestBody Set<String> keys) {
		List<Application> list = countryService.create(keys);
		return new ResponseEntity<>(list, HttpStatus.CREATED);
	}

}
