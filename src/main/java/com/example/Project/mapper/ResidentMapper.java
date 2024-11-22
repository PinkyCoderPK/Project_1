package com.example.Project.mapper;

import com.example.Project.dto.request.resident.ResidentCreateRequest;
import com.example.Project.dto.request.resident.ResidentUpdateRequest;
import com.example.Project.dto.response.ApartmentChargeResponse;
import com.example.Project.dto.response.ResidentResponse;
import com.example.Project.entity.ApartmentCharge;
import com.example.Project.entity.Resident;
import jakarta.validation.Valid;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ResidentMapper {
    Resident toResident(ResidentCreateRequest request);

    @Mapping(source = "apartment.id", target = "apartmentId")
    @Mapping(source = "apartment.apartmentName", target = "apartmentName")
    @Mapping(source = "id", target = "id")
    ResidentResponse toResidentResponse(Resident resident);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapUpdateResident(@MappingTarget Resident resident, @Valid ResidentUpdateRequest request);

}
