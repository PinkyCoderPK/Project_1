package com.example.Project.service;

import com.example.Project.dto.request.resident.ResidentCreateRequest;
import com.example.Project.dto.request.resident.ResidentUpdateRequest;
import com.example.Project.entity.Resident;
import com.example.Project.mapper.ResidentMapper;
import com.example.Project.repository.ResidentRepository;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResidentService {
    ResidentRepository residentRepository;
    ResidentMapper residentMapper;

    public Resident create(@Valid ResidentCreateRequest request){

        Resident resident = residentMapper.toResident(request);

        return residentRepository.save(resident);
    }

    public List<Resident> getAll(){
        return residentRepository.findAll();
    }

    public Resident getById(String id){
        return residentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy thông tin cư dân"));
    }

    public Resident updateById(String id, @Valid ResidentUpdateRequest request){
        Resident resident = getById(id);
        residentMapper.updateResident(resident, request);
        return residentRepository.save(resident);
    }

    public void deleteById(String residentId){
        residentRepository.deleteById(residentId);
    }
}
