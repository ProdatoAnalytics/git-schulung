package de.prodato.java_training;

import de.prodato.java_training.mapper.CustomerMapper;
import de.prodato.java_training.model.persistence.Customer;
import de.prodato.java_training.model.persistence.Item;
import de.prodato.java_training.model.rest.Address;
import de.prodato.java_training.model.rest.Company;
import de.prodato.java_training.model.rest.Geo;
import de.prodato.java_training.model.rest.User;
import de.prodato.java_training.repository.CustomerRepository;
import de.prodato.java_training.service.UserService;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

//TODO Was ist das hier? Was sagt das ueber die Testart aus?
//Antwort: Das startet einen Spring Boot Container
//Damit starten REST-Services, Schedules, Messaging, DB Zugriffe
//Es erlaubt also Component oder Integration Tests bei denen ein größerer Teil der Applikation
// im Zusammenspiel mit externen Abhängigkeiten getestet wird
//Infrastruktur kann verwendet oder gemockt werden, externe Services können verwendet oder gemockt werden
@SpringBootTest
class JavaTrainingApplicationTests {

	@Autowired
	CustomerRepository repo;

	@Autowired
	UserService userService;

	@Test
	void getUserWithCountryCodeTest() {
		//TODO Was fuer eine Testart ist das hier eigentlich?
		//Antwort: Es ist ein Integrationtest, weil REST-Services aufgerufen werden

		//Arrange
		//Act
		List<User> user = userService.getUsers();
		//Assert
		assertThat(user).hasSize(10);
		assertThat(user.get(6))
				.returns(7, from(User::getId))
				.returns("Kurtis Weissnat", from(User::getName))
				.returns("Johns Group", from( u -> u.getCompany().getName()))
				.returns("LY", from( u -> u.getAddress().getCountryCode()));
	}

	@Test
	void userServiceIntegrationTest() {

		//Arrange
		//Act
		User user = userService.getUser(1);
		//Assert
		assertThat(user)
				.returns(1, from(User::getId))
				.returns("Leanne Graham", from(User::getName))
				.returns("Romaguera-Crona", from( u -> u.getCompany().getName()));
	}

	@Test
	void beanMappingTest() {
		//Arrange
		User user = User.builder()
				.id(1)
				.name("Leanne Graham")
				.address(Address.builder()
						.street("TestStreet")
						.city("TestCity")
						.zipcode("12345")
						.geo(Geo.builder()
								.lat(120.0)
								.lng(100.0)
								.build())
						.build())
				.company(Company.builder()
						.name("Romaguera-Crona")
						.build())
				.build();

		//Act
		Customer customer = CustomerMapper.INSTANCE.toCustomer(user);

		//Assert
		assertThat(customer)
				.returns(1L, from(Customer::getId))
				.returns("Leanne Graham", from(Customer::getName))
				.returns("Romaguera-Crona", from(Customer::getCompanyName))
				.returns("TestCity", from(Customer::getCity))
				.returns("TestStreet", from(Customer::getStreet))
				.returns("12345", from(Customer::getZipcode))
				.returns(120.0, from(Customer::getLatitude))
				.returns(100.0, from(Customer::getLongitude))
				;

	}

	@Test
	@Transactional //wird fuer JPA gebraucht
	void repoTest() {
		//Arrange
		Customer customer = new Customer();
		customer.setName("Leanne Graham");
		customer.setCompanyName("Romaguera-Crona");


		Set<Item> items = new HashSet<>();
		items.add(new Item("Windeln", 10.3));
		items.add(new Item("Bier", 30.1));

		//Act
		customer = repo.save(customer);

		customer.setBoughtItems(items);

		//Assert
		assertThat(repo.findByName("Leanne Graham").get(0))
				.returns("Romaguera-Crona", from(Customer::getCompanyName));

		assertThat(repo.findByName("Leanne Graham").get(0).getBoughtItems())
				.containsExactlyInAnyOrder(new Item("Windeln", 10.3), new Item("Bier", 30.1));

	}

}
