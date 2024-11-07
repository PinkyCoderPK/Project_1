package com.example.Project.mapper;


import com.example.Project.dto.request.apartment.ApartmentCreateRequest;
import com.example.Project.dto.request.apartment.ApartmentUpdateRequest;
import com.example.Project.entity.Apartment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ApartmentMapper {
    Apartment mapCreateApartment(ApartmentCreateRequest apartmentCreateRequest);
    void mapUpdateApartment(@MappingTarget Apartment apartment, ApartmentUpdateRequest apartmentUpdateRequest);
}
