package com.example.Project.mapper;

import com.example.Project.dto.request.apartmentCharge.ApartmentChargeCreateRequest;
import com.example.Project.dto.request.apartmentCharge.ApartmentChargeUpdateRequest;
import com.example.Project.dto.response.ApartmentChargeResponse;
import com.example.Project.entity.ApartmentCharge;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ApartmentChargeMapper {
    ApartmentCharge  toApartmentCharge(ApartmentChargeCreateRequest request);
    @Mapping(source = "apartment.id", target = "apartmentId")
    @Mapping(source = "charge.id", target = "chargeId")
    @Mapping(source = "apartment.apartmentName", target = "apartmentName")
    @Mapping(source = "charge.chargeName", target = "chargeName")
    ApartmentChargeResponse toApartmentChargeResponse(ApartmentCharge apartmentCharge);
    void mapApartmentCharge(@MappingTarget ApartmentCharge apartmentCharge, ApartmentChargeUpdateRequest request);
}
