package com.example.Project.service;


import com.example.Project.dto.request.apartment.ApartmentCreateRequest;
import com.example.Project.dto.request.apartment.ApartmentSearchRequest;
import com.example.Project.dto.request.apartment.ApartmentUpdateRequest;
import com.example.Project.entity.Apartment;
import com.example.Project.entity.Resident;
import com.example.Project.mapper.ApartmentMapper;
import com.example.Project.repository.ApartmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Data
@Service

public class ApartmentService {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ApartmentMapper apartmentMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ResidentService residentService;

    public Apartment create(ApartmentCreateRequest apartmentCreateRequest) {
        Apartment apartment = apartmentMapper.mapCreateApartment(apartmentCreateRequest);
        return apartmentRepository.save(apartment);
    }

    public List<Apartment> getAll(){
        return apartmentRepository.findAll();
    }
    public Apartment getById(String id) {
        return apartmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy mã chung cư"));
    }
    public List<Predicate> createPredicates(ApartmentSearchRequest apartmentSearchRequests, CriteriaBuilder criteriaBuilder, Root<Apartment> apartmentRoot) {
        List<Predicate> predicates = new ArrayList<>();
        for(Field field : ApartmentSearchRequest.class.getDeclaredFields()){
            field.setAccessible(true);
            try{
                Object value = field.get(apartmentSearchRequests);
                if(value != null){
                    predicates.add(criteriaBuilder.equal(apartmentRoot.get(field.getName()), value));
                }
            }
            catch (IllegalAccessException e){
                throw new RuntimeException("Không tìm thấy " + field.getName());
            }
        }
        return predicates;
    }
    public List<Apartment> search(@Valid  ApartmentSearchRequest apartmentSearchRequests) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);
        Root<Apartment> apartmentRoot = criteriaQuery.from(Apartment.class);
        List<Predicate> predicates = createPredicates(apartmentSearchRequests, criteriaBuilder, apartmentRoot);
        criteriaQuery.select(apartmentRoot).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public void deleteById(String id) {
        apartmentRepository.deleteById(id);
    }
    public void deleteAll(){
        apartmentRepository.deleteAll();
    }

    public Apartment updateById(String id, ApartmentUpdateRequest apartmentUpdateRequest) {
        Resident resident = residentService.getById(apartmentUpdateRequest.getOwnerId());
        if((resident == null) && (!apartmentUpdateRequest.getStatus().equals("Available"))) {
            throw new NoSuchElementException("Không tìm thấy cư dân");
        }
        Apartment apartment = getById(id);
        apartmentMapper.mapUpdateApartment(apartment, apartmentUpdateRequest);
        return apartmentRepository.save(apartment);
    }
}
