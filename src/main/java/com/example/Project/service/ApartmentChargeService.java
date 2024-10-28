package com.example.Project.service;

import com.example.Project.dto.request.ApartmentChargeRequest;
import com.example.Project.entity.ApartmentCharge;
import com.example.Project.mapper.ApartmentChargeMapper;
import com.example.Project.repository.ApartmentChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentChargeService {
    @Autowired
    private ApartmentChargeRepository apartmentChargeRepository;

    @Autowired
    private ApartmentChargeMapper apartmentChargeMapper;

    public ApartmentCharge create(ApartmentChargeRequest request) {
        ApartmentCharge apartmentCharge = apartmentChargeMapper.toApartmentCharge(request);
        return apartmentChargeRepository.save(apartmentCharge);
    }

    public List<ApartmentCharge> getAll() {
        return apartmentChargeRepository.findAll();
    }

    public Optional<ApartmentCharge> getById(String id) {
        return apartmentChargeRepository.findById(id);
    }

    public List<ApartmentCharge> getByApartmentId(String apartmentId) {
        return apartmentChargeRepository.findByApartmentId(apartmentId);
    }

    public void deleteById(String id) {
        apartmentChargeRepository.deleteById(id);
    }

    public void deleteAll() {
        apartmentChargeRepository.deleteAll();
    }

    public ApartmentCharge updateById(String id, ApartmentChargeRequest request) {
        Optional<ApartmentCharge> optionalApartmentCharge = getById(id);

        if (optionalApartmentCharge.isPresent()) {
            ApartmentCharge apartmentCharge = optionalApartmentCharge.get();
            apartmentChargeMapper.updateApartmentCharge(apartmentCharge, request);
            return apartmentChargeRepository.save(apartmentCharge);
        }
        else throw new RuntimeException("ApartmentCharge not found with id: " + id);
    }
}
