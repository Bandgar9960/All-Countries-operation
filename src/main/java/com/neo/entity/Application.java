package com.neo.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Application implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@Size(min = 2, max = 5, message = "Pass argument is not valid")
	private String cca2;
	@NotEmpty
	@Size(min = 2, max = 5, message = "Pass argument is not valid")
	private String ccn3;
	@NotEmpty
	@Size(min = 2, max = 5, message = "Pass argument is not valid")
	private String cca3;
	@NotEmpty
	@Size(min = 2, max = 5, message = "Pass argument is not valid")
	private String cioc;

	private boolean independent;

	@NotEmpty
	@Size(min = 2, max = 25, message = "Pass argument is not valid")
	private String status;


	private boolean unMember;
	
	private String fifa;
	private String region;
	private String subregion;

}
