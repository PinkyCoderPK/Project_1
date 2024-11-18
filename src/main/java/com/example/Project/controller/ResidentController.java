package com.example.Project.controller;


import com.example.Project.dto.request.resident.ResidentCreateRequest;
import com.example.Project.dto.request.resident.ResidentSearchRequest;
import com.example.Project.dto.request.resident.ResidentUpdateRequest;
import com.example.Project.dto.response.ApiResponse;
import com.example.Project.entity.Resident;
import com.example.Project.service.ResidentService;
import jakarta.validation.Valid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/project/resident")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResidentController {

    @Autowired
    ResidentService residentService;

    @PostMapping
    ApiResponse<Resident> create(@RequestBody @Valid ResidentCreateRequest request){
        return ApiResponse.<Resident>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(residentService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<Resident>> getAll(){
        return ApiResponse.<List<Resident>>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(residentService.getAll())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<Resident> getById(@PathVariable String id){
        return ApiResponse.<Resident>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(residentService.getById(id))
                .build();
    }

    @PostMapping("/search")
    ApiResponse<List<Resident>> search(@RequestBody @Valid ResidentSearchRequest request){
        return ApiResponse.<List<Resident>>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(residentService.search(request))
                .build();
    }
    @PatchMapping("/{id}")
    ApiResponse<Resident> updateById(@PathVariable String id,@RequestBody @Valid ResidentUpdateRequest request){
        return ApiResponse.<Resident>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(residentService.updateById(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable String id){
        residentService.deleteById(id);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(null)
                .build();
    }
    @DeleteMapping
    ApiResponse<Void> deleteAll(){
        residentService.deleleAll();
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Thành công")
                .result(null)
                .build();
    }

}