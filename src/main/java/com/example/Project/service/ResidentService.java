package com.example.Project.service;

import com.example.Project.dto.request.resident.ResidentCreateRequest;
import com.example.Project.dto.request.resident.ResidentUpdateRequest;
import com.example.Project.entity.Apartment;
import com.example.Project.entity.Resident;
import com.example.Project.mapper.ResidentMapper;
import com.example.Project.repository.ResidentRepository;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResidentService {
    @Autowired
    ResidentRepository residentRepository;
    @Autowired
    ResidentMapper residentMapper;
    @Autowired
    ApartmentService apartmentService;

    public Resident create(@Valid ResidentCreateRequest request){
        Resident resident = residentMapper.toResident(request);
        Apartment apartment = apartmentService.getById(request.getApartmentId());
        resident.setApartment(apartment);
        return residentRepository.save(resident);
    }

    public List<Resident> getAll(){
        return residentRepository.findAll();
    }

    public Resident getById(String id){
        return residentRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Không tìm thấy cư dân"));
    }

    public Resident updateById(String id, @Valid ResidentUpdateRequest request){
        Resident resident = getById(id);
        Apartment apartment = apartmentService.getById(request.getApartmentId());
        residentMapper.mapUpdateResident(resident, request);
        resident.setApartment(apartment);

        return residentRepository.save(resident);
    }

    public void deleteById(String id){
        residentRepository.deleteById(id);
    }
}