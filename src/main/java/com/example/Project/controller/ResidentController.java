package com.example.Project.controller;


import com.example.Project.dto.request.resident.ResidentCreateRequest;
import com.example.Project.dto.request.resident.ResidentUpdateRequest;
import com.example.Project.dto.response.ApiResponse;
import com.example.Project.entity.Resident;
import com.example.Project.service.ResidentService;
import jakarta.validation.Valid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/residents")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResidentController {
    ResidentService residentService;

    @PostMapping
    ApiResponse<Resident> createResident(@RequestBody @Valid ResidentCreateRequest request){
        ApiResponse apiResponse = new ApiResponse();
        try{
            apiResponse.setResult(residentService.create(request));
        } catch (Exception e){
            apiResponse.setCode(406);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @GetMapping
    List<Resident> getAll(){
        return residentService.getAll();
    }

    @GetMapping("/{residentId}")
    Resident getById(@PathVariable String Id){
        return residentService.getById(Id);
    }

    @PutMapping("/{residentId}")
    Resident updateById(@PathVariable String Id,@RequestBody @Valid ResidentUpdateRequest request){
        return residentService.updateById(Id, request);
    }

    @DeleteMapping("/{residentId}")
    String deleteResident(@PathVariable String residentId){
        residentService.deleteById(residentId);
        return "Resident deleted";
    }

}
