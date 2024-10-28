package com.example.Project.mapper;

import com.example.Project.dto.request.ApartmentChargeRequest;
import com.example.Project.entity.ApartmentCharge;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Optional;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ApartmentChargeMapper {
    ApartmentCharge  toApartmentCharge(ApartmentChargeRequest request);
    void updateApartmentCharge(@MappingTarget ApartmentCharge apartmentCharge, ApartmentChargeRequest request);
}
