package de.prodato.java_training.controller;

import de.prodato.java_training.model.persistence.Customer;
import de.prodato.java_training.model.persistence.Item;
import de.prodato.java_training.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    CustomerRepository repo;

    @Autowired
    UserController userController;

    @Test
    @Transactional
    void getCustomer() throws Exception {
        //Arrange
        Customer customer = new Customer();
        customer.setName("Leanne Graham");
        customer.setCompanyName("Romaguera-Crona");


        Set<Item> items = new HashSet<>();
        items.add(new Item("Windeln", 10.3));
        items.add(new Item("Bier", 30.1));
        customer = repo.save(customer);
        customer.setBoughtItems(items);


        //Act
        mvc.perform(get("/user/"+customer.getId()))
        //Assert
                .andExpect(jsonPath("$.id",is(customer.getId().intValue())))
                .andExpect(jsonPath("$.name",is("Leanne Graham")))
                .andExpect(jsonPath("$.boughtItems.length()",is(2)))
                .andExpect(jsonPath("$.boughtItems[?(@.name=='Windeln')].price",contains(10.3)))
                .andExpect(jsonPath("$.boughtItems[?(@.name=='Bier')].price",contains(30.1)))
                .andDo(print());

    }
}