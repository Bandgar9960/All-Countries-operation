package com.neo.entity;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Name {

	private String common;
	private String official;
	private HashMap<String, NativeName> nativeName;

}
