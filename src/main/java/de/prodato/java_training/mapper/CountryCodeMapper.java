package de.prodato.java_training.mapper;

import de.prodato.java_training.model.rest.User;
import de.prodato.java_training.service.GeoService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

public class CountryCodeMapper {

    private final BiFunction<Double,Double,String> mapper;

    public CountryCodeMapper(BiFunction<Double,Double,String> mapper) {
        this.mapper = mapper;
    }

    public List<User> addCountryCode(List<User> user) {
        for(User u : user) {
            String countryCode = mapper.apply(u.getAddress().getGeo().getLat(),u.getAddress().getGeo().getLng()) ;
            u.getAddress().setCountryCode(countryCode);
        }
        return user;
    }

}
