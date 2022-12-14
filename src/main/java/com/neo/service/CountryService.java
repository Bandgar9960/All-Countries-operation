package com.neo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.entity.Application;
import com.neo.entity.Country;
import com.neo.entity.UrlUtil;
import com.neo.exception.ResourceNotFoundException;
import com.neo.repository.CountryRepository;

import reactor.core.publisher.Mono;


@Service
public class CountryService {

	/*
	 * @Autowired private CountryRepository countryRepository;
	 */
	@Autowired
	private UrlUtilityService urlUtilityService;

	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	RestTemplate restTemplate;

	WebClient webClient = WebClient.create();

	public List<Country> getcountries() {

		UrlUtil urlUtil = urlUtilityService.getUrl("allUrl");

		List<Country> list = webClient
				.get()
				.uri(urlUtil.getUrl())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Country.class)
				.collectList()
				.block();

		return list;

	}

	public Country getSingleCountry(String name) {

		UrlUtil urlUtil = urlUtilityService.getUrl("singleUrl");

		Country country = webClient
				.get()
				.uri(urlUtil.getUrl() + name)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
						clientResponse -> Mono.error(new ResourceNotFoundException("Country not found on server!!")))
				.bodyToFlux(Country.class)
				.blockFirst();

		return country;

	}

	public Set<String> getAllOnlyKeys() throws JsonMappingException, JsonProcessingException {

		UrlUtil urlUtil = urlUtilityService.getUrl("allUrl");
		
	  ResponseEntity<String> response = restTemplate.getForEntity(urlUtil.getUrl(), String.class);
//		 ResponseEntity<List<String>> response = webClient.get().uri(urlUtil.getUrl())
//			.retrieve().toEntityList(String.class).block();
		
		    List<Map<String, Object>> value = new ObjectMapper()
				 .readValue(response.getBody(),new TypeReference<List<Map<String, Object>>>() { });

		    Set<String> set = new HashSet<>();
		   
		   for (Map<String, Object> map : value) {
			   
			    for (Map.Entry<String, Object> entry : map.entrySet()) {
			    	
			        String key = entry.getKey();
			        
					if(set.size()!=10) {
						set.add(key);
					}    
                }
		   }
		   
           return set;			 
	}

	public List<Application> create(Set<String> keys) {
		
		UrlUtil urlUtil = urlUtilityService.getUrl("allUrl");

	    List<Country> coun = webClient
				.get()
				.uri(urlUtil.getUrl())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Country.class)
				.collectList().block();

	   List<Application> list=new ArrayList<>();
	   
	   for(Country country : coun) {
		   
		   Application app=new Application();
		   if(keys.contains("cca2")) { 
		   app.setCca2(country.getCca2());
		   }
		   if(keys.contains("cca3")) {
		   app.setCca3(country.getCca3());
		   }
		   if(keys.contains("ccn3")) {
			   app.setCcn3(country.getCcn3());
		   }
		   if(keys.contains("cioc")) {
			   app.setCioc(country.getCioc());
		   }
		   if(keys.contains("independent")) {
			   app.setIndependent(country.isIndependent());
		   }
		   if(keys.contains("status")) {
			   app.setStatus(country.getStatus());
		   }
		   if(keys.contains("unMember")) {
			   app.setUnMember(country.isUnMember());
		   }
		   if(keys.contains("fifa")) {
			   app.setFifa(country.getFifa());
		   }
		   if(keys.contains("region")) {
			   app.setRegion(country.getRegion());
		   }
		   if(keys.contains("region")) {
			   app.setSubregion(country.getSubregion());
		   }
		 list.add(app);   
	   }
	   
		List<Application> saveApplication = countryRepository.saveAll(list);

		return saveApplication;

	}

}
