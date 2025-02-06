package de.prodato.java_training.mapper;

import de.prodato.java_training.model.rest.Address;
import de.prodato.java_training.model.rest.Geo;
import de.prodato.java_training.model.rest.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CountryCodeMapperTest {

    @Test
    public void testAddCountryCode() {
        //Arrange
        CountryCodeMapper ccm = new CountryCodeMapper((lat,lng) -> "DE");
        List<User> user = new ArrayList<>();
        user.add(User.builder().address(Address.builder().geo(Geo.builder().lng("100").lat("120").build()).build()).build());
        user.add(User.builder().address(Address.builder().geo(Geo.builder().lng("200").lat("120").build()).build()).build());
        user.add(User.builder().address(Address.builder().geo(Geo.builder().lng("300").lat("120").build()).build()).build());

        //Act
        user = ccm.addCountryCode(user);

        //Assert
        assertThat(user).hasSize(3);
        assertThat(user.get(1)).returns("DE", from( u -> u.getAddress().getCountryCode()));
    }

}