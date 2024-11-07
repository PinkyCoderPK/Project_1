package com.project.ABM.mapper;

import com.project.ABM.dto.request.ResidentCreationRequest;
import com.project.ABM.dto.request.ResidentUpdateRequest;
import com.project.ABM.dto.response.ResidentResponse;
import com.project.ABM.entity.Resident;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ResidentMapper {
    Resident toResident(ResidentCreationRequest request);

//    @Mapping(source = "", target = "")
    ResidentResponse toResidentResponse(Resident resident);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateResident(@MappingTarget Resident resident, ResidentUpdateRequest request);
}
