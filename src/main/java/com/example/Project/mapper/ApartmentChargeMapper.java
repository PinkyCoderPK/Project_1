package com.example.Project.mapper;

import com.example.Project.dto.request.apartmentCharge.ApartmentChargeRequest;
import com.example.Project.dto.response.ApartmentChargeForBillResponse;
import com.example.Project.dto.response.ApartmentChargeResponse;
import com.example.Project.entity.ApartmentCharge;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ApartmentChargeMapper {

    ApartmentCharge toApartmentCharge(ApartmentChargeRequest request);

    @Mapping(source = "apartment.id", target = "apartmentId")
    @Mapping(source = "charge.id", target = "chargeId")
    @Mapping(source = "apartment.apartmentName", target = "apartmentName")
    @Mapping(source = "charge.chargeName", target = "chargeName")
    ApartmentChargeResponse toApartmentChargeResponse(ApartmentCharge apartmentCharge);

    List<ApartmentChargeResponse> toApartmentChargeResponseList(List<ApartmentCharge> apartmentChargeList);

    void mapApartmentCharge(@MappingTarget ApartmentCharge apartmentCharge, ApartmentChargeRequest request);

    @Mapping(source = "charge.id", target = "chargeId")
    @Mapping(source = "charge.chargeName", target = "chargeName")
    ApartmentChargeForBillResponse toApartmentChargeForBillResponse(ApartmentCharge apartmentCharge);

    List<ApartmentChargeForBillResponse> toApartmentChargeForBillResponseList(List<ApartmentCharge> apartmentChargeList);
}
