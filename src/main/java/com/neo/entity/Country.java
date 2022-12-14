package com.neo.entity;

import java.util.HashMap;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Country {

	private Name name;
	private List<String> tld;
	private String cca2;
	private String ccn3;
	private String cca3;
	private String cioc;
	private boolean independent;
	private String status;
	private boolean unMember;
	private HashMap<String, Currencies> currencies;
	private Idd idd;
	private List<String> capital;
	private List<String> altSpellings;
	private String region;
	private String subregion;
	private HashMap<String, String> languages;
	private HashMap<String, Translations> translations;
	private List<String> latlng;
	private boolean landlocked;
	private List<String> borders;
	private float area;
	private HashMap<String, Demonyms> demonyms;
	private String flag;
	private Maps maps;
	private float population;
	private Gini gini;
	private String fifa;
	private Car car;
	private List<String> timezones;
	private List<String> continents;
	private Flags flags;
	private CoatOfArms coatOfArms;
	private String startOfWeek;
	private CapitalInfo capitalInfo;

}
