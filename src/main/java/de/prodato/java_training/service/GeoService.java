package de.prodato.java_training.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import uk.recurse.geocoding.reverse.Country;
import uk.recurse.geocoding.reverse.ReverseGeocoder;

import java.util.Optional;

@Service
public class GeoService {

    public String getCountryCodeFromCoordinates(double latitude, double longitude) {
        ReverseGeocoder geocoder = new ReverseGeocoder();
        Optional<Country> country = geocoder.getCountry(latitude, longitude);
        return country.map(Country::iso).orElse(null);

    }

}
