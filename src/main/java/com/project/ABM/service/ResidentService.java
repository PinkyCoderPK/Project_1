package com.project.ABM.service;

import com.project.ABM.dto.request.ResidentCreationRequest;
import com.project.ABM.dto.request.ResidentUpdateRequest;
import com.project.ABM.dto.response.ResidentResponse;
import com.project.ABM.entity.Resident;
import com.project.ABM.exception.AppException;
import com.project.ABM.exception.ErrorCode;
import com.project.ABM.mapper.ResidentMapper;
import com.project.ABM.repository.ResidentRepository;
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

    public Resident createRequest(ResidentCreationRequest request){
        if (residentRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        Resident resident = residentMapper.toResident(request);

        return residentRepository.save(resident);
    }

    public List<Resident> getResidents(){
        return residentRepository.findAll();
    }

    public ResidentResponse getResident(String residentId){
        return residentMapper.toResidentResponse(residentRepository.findById(residentId)
                .orElseThrow(()-> new RuntimeException("Resident not found")));
    }

    public ResidentResponse updateResident(String residentId, ResidentUpdateRequest request){
        Resident resident = residentRepository.findById(residentId)
                .orElseThrow(()-> new RuntimeException("Resident not found"));

        residentMapper.updateResident(resident,request);

        return residentMapper.toResidentResponse(residentRepository.save(resident));
    }

    public void deleteResident(String residentId){
        residentRepository.deleteById(residentId);
    }
}
