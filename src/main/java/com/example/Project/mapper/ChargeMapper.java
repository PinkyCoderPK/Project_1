package com.example.Project.mapper;

import com.example.Project.dto.request.charge.ChargeCreateRequest;
import com.example.Project.dto.request.charge.ChargeUpdateRequest;
import com.example.Project.entity.Charge;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChargeMapper {
    Charge mapCreateCharge(ChargeCreateRequest request);
    void mapUpdateCharge(@MappingTarget Charge charge, ChargeUpdateRequest request);
}
