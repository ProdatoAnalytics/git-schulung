package de.prodato.java_training.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import uk.recurse.geocoding.reverse.Country;
import uk.recurse.geocoding.reverse.ReverseGeocoder;

import java.util.Optional;

@Service
public class GeoService {

    public String getCountryCodeFromCoordinates(String latitude, String longitude) {
        ReverseGeocoder geocoder = new ReverseGeocoder();
        String countryCode = "";
        Optional<Country> country = geocoder.getCountry(51.507222, -0.1275);
        return country.map(Country::iso).orElse(null);

    }

}
