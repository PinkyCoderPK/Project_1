package com.example.Project.controller;

import com.example.Project.dto.request.ApartmentChargeCreateRequest;
import com.example.Project.dto.request.ApartmentChargeSearchRequest;
import com.example.Project.dto.request.ApartmentChargeUpdateRequest;
import com.example.Project.dto.response.ApiResponse;
import com.example.Project.entity.ApartmentCharge;
import com.example.Project.service.ApartmentChargeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project/apartmentCharge")
public class ApartmentChargeController {
    @Autowired
    private ApartmentChargeService apartmentChargeService;

    @PostMapping
    public ApiResponse<ApartmentCharge> create(@RequestBody @Valid ApartmentChargeCreateRequest request) {
        return ApiResponse.<ApartmentCharge>builder()
                .result(apartmentChargeService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ApartmentCharge>> getAll() {
        return ApiResponse.<List<ApartmentCharge>>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(apartmentChargeService.getAll())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ApartmentCharge> getById(@PathVariable String id) {
        return ApiResponse.<ApartmentCharge>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(apartmentChargeService.getById(id))
                .build();
    }

    @PostMapping("/search")
    public ApiResponse<List<ApartmentCharge>> search(@RequestBody @Valid ApartmentChargeSearchRequest request) {
        return ApiResponse.<List<ApartmentCharge>>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(apartmentChargeService.search(request))
                .build();
    }

    @PatchMapping("/{id}")
    public ApartmentCharge updateById (@PathVariable String id, @RequestBody @Valid ApartmentChargeUpdateRequest request) {
        return apartmentChargeService.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        apartmentChargeService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        apartmentChargeService.deleteAll();
    }
}
