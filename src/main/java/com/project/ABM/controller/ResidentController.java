package com.project.ABM.controller;

import com.project.ABM.dto.request.ApiResponse;
import com.project.ABM.dto.request.ResidentCreationRequest;
import com.project.ABM.dto.request.ResidentUpdateRequest;
import com.project.ABM.dto.response.ResidentResponse;
import com.project.ABM.entity.Resident;
import com.project.ABM.exception.ErrorCode;
import com.project.ABM.service.ResidentService;
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
    ApiResponse <Resident> createResident(@RequestBody @Valid ResidentCreationRequest request){
        ApiResponse apiResponse = new ApiResponse();
        try{
            apiResponse.setResult(residentService.createRequest(request));
        } catch (Exception e){
            apiResponse.setCode(406);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @GetMapping
    List<Resident> getResidents(){
        return residentService.getResidents();
    }

    @GetMapping("/{residentId}")
    ResidentResponse getResident(@PathVariable String residentId){
        return residentService.getResident(residentId);
    }

    @PutMapping("/{residentId}")
    ResidentResponse updateResident(@PathVariable String residentId,@RequestBody @Valid ResidentUpdateRequest request){
        return residentService.updateResident(residentId,request);
    }

    @DeleteMapping("/{residentId}")
    String deleteResident(@PathVariable String residentId){
        residentService.deleteResident(residentId);
        return "Resident deleted";
    }

}
