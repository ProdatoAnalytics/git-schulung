package de.prodato.java_training.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.prodato.java_training.mapper.CountryCodeMapper;
import de.prodato.java_training.model.rest.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class UserService {

    private final RestClient restClient;

    @Autowired
    GeoService geoService;

    private CountryCodeMapper ccm;

    public UserService(RestClient.Builder restClientBuilder, @Value("${user.url}") String userUrl) {
        this.restClient = restClientBuilder.baseUrl(userUrl).build();
        ccm = new CountryCodeMapper((lat,lng) -> geoService.getCountryCodeFromCoordinates(lat,lng));
    }


    public String getUserString(int id) {
        return this.restClient.get().uri("/users/{id}",id).retrieve().body(String.class);
    }

    public User getUser(int id) {
        //START solution code
        return toUser(getUserString(id));
        //END solution code

    }

//    public User getUserSimple(int id) {
//        //START solution code
//        return this.restClient.get().uri("/users/{id}",id).retrieve().body(User.class);
//        //END solution code
//    }

     public List<User> getUsers() {
        List<User> user = this.restClient.get().uri("/users").retrieve().body(new ParameterizedTypeReference<List<User>>(){});
        user = ccm.addCountryCode(user);
        return user;
    }

    public User toUser(String userString) {
        //START solution code
        ObjectMapper om = new ObjectMapper();
        try {
            return om.readValue(userString, User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        //END solution code
    }

}
