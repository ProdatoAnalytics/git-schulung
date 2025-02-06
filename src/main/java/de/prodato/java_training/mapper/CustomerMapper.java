package de.prodato.java_training.mapper;

import de.prodato.java_training.model.persistence.Customer;
import de.prodato.java_training.model.rest.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    //Verwende dafuer MapStruct

    //START solution code
    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    @Mapping(target = "name", source = "name")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "zipcode", source = "address.zipcode")
    @Mapping(target = "houseNumber", source = "address.suite")
    @Mapping(target = "latitude", source = "address.geo.lat")
    @Mapping(target = "longitude", source = "address.geo.lng")
    @Mapping(target = "companyName", source = "company.name")
    //END solution code
    Customer toCustomer(User user);
}
