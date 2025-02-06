package de.prodato.java_training.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User
{

	private int id;
	private String name;
	private Company company;

	//START solution code
	private String website;
	private Address address;
	private String phone;
	private String email;
	private String username;
	//END solution code

	/*
	 * {
	 *   "id": 1,
	 *   "name": "Leanne Graham",
	 * 	 "company": {
	 *     "name": "Romaguera-Crona",
	 *     "catchPhrase": "Multi-layered client-server neural-net",
	 *     "bs": "harness real-time e-markets"
	 *   },
	 *   "username": "Bret",
	 *   "email": "Sincere@april.biz",
	 *   "address": {
	 *     "street": "Kulas Light",
	 *     "suite": "Apt. 556",
	 *     "city": "Gwenborough",
	 *     "zipcode": "92998-3874",
	 *     "geo": {
	 *       "lat": "-37.3159",
	 *       "lng": "81.1496"
	 *     }
	 *   },
	 *   "phone": "1-770-736-8031 x56442",
	 *   "website": "hildegard.org"
	 * }
	 * */

	//Schaffst du das mit einer einzigen Annotation?
	//Vergleiche die Lesbarkeit mit der Customer Klasse
}