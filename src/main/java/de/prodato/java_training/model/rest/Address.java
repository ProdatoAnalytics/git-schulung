package de.prodato.java_training.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address{
	private String zipcode;
	private Geo geo;
	private String suite;
	private String city;
	private String street;
	private String countryCode;
}