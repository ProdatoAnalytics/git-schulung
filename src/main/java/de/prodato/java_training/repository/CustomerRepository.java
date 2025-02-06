package de.prodato.java_training.repository;

import java.util.List;

import de.prodato.java_training.model.persistence.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByName(String username);

    Customer findById(long id);
}