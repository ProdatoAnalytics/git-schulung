package de.prodato.java_training.controller;

import de.prodato.java_training.model.persistence.Customer;
import de.prodato.java_training.model.rest.User;
import de.prodato.java_training.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    CustomerRepository repo;

    @GetMapping(path = "/{id}", produces = "application/json")
    public Customer getCustomer(@PathVariable int id) {
        return repo.findById(id);
    }

}
