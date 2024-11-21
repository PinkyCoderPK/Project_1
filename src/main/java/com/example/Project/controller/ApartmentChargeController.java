package com.example.Project.controller;

import com.example.Project.dto.request.apartmentCharge.ApartmentChargeCreateRequest;
import com.example.Project.dto.request.apartmentCharge.ApartmentChargeSearchRequest;
import com.example.Project.dto.request.apartmentCharge.ApartmentChargeUpdateRequest;
import com.example.Project.dto.response.ApartmentChargeResponse;
import com.example.Project.dto.response.ApiResponse;
import com.example.Project.entity.ApartmentCharge;
import com.example.Project.mapper.ApartmentChargeMapper;
import com.example.Project.service.ApartmentChargeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project/apartmentCharge")
public class ApartmentChargeController {
    @Autowired
    private ApartmentChargeService apartmentChargeService;

    @Autowired
    private ApartmentChargeMapper apartmentChargeMapper;

    @PostMapping
    public ApiResponse<ApartmentChargeResponse> create(@RequestBody @Valid ApartmentChargeCreateRequest request) {
        ApartmentCharge apartmentCharge = apartmentChargeService.create(request);
        ApartmentChargeResponse response = apartmentChargeMapper.toApartmentChargeResponse(apartmentCharge);
        return ApiResponse.<ApartmentChargeResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(response)
                .build();
    }

    @PostMapping("/createMultiple")
    public ApiResponse<List<ApartmentChargeResponse>> createMultiple(@RequestBody @Valid List<ApartmentChargeCreateRequest> requests) {
        List<ApartmentCharge> apartmentCharges = apartmentChargeService.createMultiple(requests);
        List<ApartmentChargeResponse> responses = new ArrayList<>();
        for(ApartmentCharge apartmentCharge : apartmentCharges) {
            ApartmentChargeResponse response = apartmentChargeMapper.toApartmentChargeResponse(apartmentCharge);
            responses.add(response);
        }
        return ApiResponse.<List<ApartmentChargeResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(responses)
                .build();
    }
    @GetMapping
    public ApiResponse<List<ApartmentChargeResponse>> getAll() {
        List<ApartmentCharge> apartmentCharges = apartmentChargeService.getAll();
        List<ApartmentChargeResponse> responses = new ArrayList<>();
        for(ApartmentCharge apartmentCharge : apartmentCharges) {
            ApartmentChargeResponse response = apartmentChargeMapper.toApartmentChargeResponse(apartmentCharge);
            responses.add(response);
        }
        return ApiResponse.<List<ApartmentChargeResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(responses)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ApartmentChargeResponse> getById(@PathVariable String id) {
        ApartmentCharge apartmentCharge = apartmentChargeService.getById(id);
        ApartmentChargeResponse response = apartmentChargeMapper.toApartmentChargeResponse(apartmentCharge);
        return ApiResponse.<ApartmentChargeResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(response)
                .build();
    }

    @PostMapping("/search")
    public ApiResponse<List<ApartmentChargeResponse>> search(@RequestBody @Valid ApartmentChargeSearchRequest request) {
        List<ApartmentCharge> apartmentCharges = apartmentChargeService.search(request);
        List<ApartmentChargeResponse> responses = new ArrayList<>();
        for(ApartmentCharge apartmentCharge : apartmentCharges) {
            ApartmentChargeResponse response = apartmentChargeMapper.toApartmentChargeResponse(apartmentCharge);
            responses.add(response);
        }
        return ApiResponse.<List<ApartmentChargeResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(responses)
                .build();
    }

    @PatchMapping("/{id}")
    public ApiResponse<ApartmentChargeResponse> updateById (@PathVariable String id, @RequestBody @Valid ApartmentChargeUpdateRequest request) {
        ApartmentCharge apartmentCharge = apartmentChargeService.updateById(id, request);
        ApartmentChargeResponse response = apartmentChargeMapper.toApartmentChargeResponse(apartmentCharge);
        return ApiResponse.<ApartmentChargeResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(response)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteById(@PathVariable String id) {
        apartmentChargeService.deleteById(id);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(null)
                .build();
    }

    @DeleteMapping
    public ApiResponse<Void> deleteAll() {
        apartmentChargeService.deleteAll();
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(null)
                .build();
    }
}
