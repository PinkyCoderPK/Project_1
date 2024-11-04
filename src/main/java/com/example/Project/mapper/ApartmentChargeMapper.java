package com.example.Project.mapper;

import com.example.Project.dto.request.ApartmentChargeCreateRequest;
import com.example.Project.entity.ApartmentCharge;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ApartmentChargeMapper {
    ApartmentCharge  toApartmentCharge(ApartmentChargeCreateRequest request);
    void mapApartmentCharge(@MappingTarget ApartmentCharge apartmentCharge, ApartmentChargeCreateRequest request);
}
