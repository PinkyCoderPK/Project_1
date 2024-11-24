package com.example.Project.mapper;

import com.example.Project.dto.request.bill.BillCreateRequest;
import com.example.Project.dto.request.bill.BillUpdateRequest;
import com.example.Project.dto.response.BillResponse;
import com.example.Project.entity.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BillMapper {
    @Mapping(source = "apartmentId", target = "apartmentId")
    Bill toBill(BillCreateRequest request);

    BillResponse toBillResponse(Bill bill);

    void mapBill(@MappingTarget Bill bill, BillUpdateRequest request);

}
