package com.example.Project.controller;

import com.example.Project.dto.request.apartment.ApartmentCreateRequest;
import com.example.Project.dto.request.apartment.ApartmentSearchRequest;
import com.example.Project.dto.request.apartment.ApartmentUpdateRequest;
import com.example.Project.dto.response.ApiResponse;
import com.example.Project.entity.Apartment;
import com.example.Project.service.ApartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project/apartment")
public class ApartmentController {
    private ApartmentService apartmentService;

    @PostMapping
    public ApiResponse<Apartment> createApartment(@RequestBody @Valid ApartmentCreateRequest apartmentCreateRequest) {
        return ApiResponse.<Apartment>builder()
                .result(apartmentService.create(apartmentCreateRequest))
                .build();
    }
    @GetMapping
    public ApiResponse<List<Apartment>> getAllApartments() {
        return  ApiResponse.<List<Apartment>>builder()
                .code(HttpStatus.OK.value())
                .message("Thanh Cong")
                .result(apartmentService.getAll())
                .build();

    }
    @GetMapping("/{id}")
    public ApiResponse<Apartment> getApartmentById(@PathVariable String id) {
        return ApiResponse.<Apartment>builder()
                .code(HttpStatus.OK.value())
                .result(apartmentService.getById(id))
                .build();
    }
    @GetMapping("/search")
    public ApiResponse<List<Apartment>> searchApartment(@RequestBody @Valid ApartmentSearchRequest apartmentSearchRequest) {
        return ApiResponse.<List<Apartment>>builder()
                .code(HttpStatus.OK.value())
                .result(apartmentService.search(apartmentSearchRequest))
                .build();
    }
    @PatchMapping("/{id}")
    public Apartment updateById(@PathVariable String id, @RequestBody @Valid ApartmentUpdateRequest apartmentUpdateRequest) {
        return apartmentService.updateById(id, apartmentUpdateRequest);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        apartmentService.deleteById(id);
    }
    @DeleteMapping
    public void deleteAll(){
        apartmentService.deleteAll();
    }

}
