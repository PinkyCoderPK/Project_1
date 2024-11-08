package com.example.Project.mapper;

import com.example.Project.dto.request.resident.ResidentCreateRequest;
import com.example.Project.dto.request.resident.ResidentUpdateRequest;
import com.example.Project.entity.Resident;
import jakarta.validation.Valid;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ResidentMapper {
    Resident toResident(ResidentCreateRequest request);

//    @Mapping(source = "", target = "")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateResident(@MappingTarget Resident resident, @Valid ResidentUpdateRequest request);

}
