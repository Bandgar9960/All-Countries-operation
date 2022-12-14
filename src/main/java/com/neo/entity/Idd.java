package com.neo.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Idd {

	private String root;
	private List<String> suffixes;

}
