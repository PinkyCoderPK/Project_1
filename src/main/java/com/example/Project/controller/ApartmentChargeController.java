package com.example.Project.controller;

import com.example.Project.dto.request.ApartmentChargeRequest;
import com.example.Project.entity.ApartmentCharge;
import com.example.Project.service.ApartmentChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project/apartmentCharge")
public class ApartmentChargeController {
    @Autowired
    private ApartmentChargeService apartmentChargeService;

    @PostMapping
    public ApartmentCharge create(@RequestBody ApartmentChargeRequest request) {
        return apartmentChargeService.create(request);
    }

    @GetMapping
    public List<ApartmentCharge> getAll() {
        return apartmentChargeService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<ApartmentCharge> getById(@PathVariable String id) {
        return apartmentChargeService.getById(id);
    }

    @GetMapping("/apartment/{apartmentId}")
    public List<ApartmentCharge> getByApartmentId(@PathVariable String apartmentId) {
        return apartmentChargeService.getByApartmentId(apartmentId);
    }

    @PatchMapping("/{id}")
    public ApartmentCharge updateById (@PathVariable String id, @RequestBody ApartmentChargeRequest request) {
        return apartmentChargeService.updateById(id, request);
    }
}
