package com.example.Project.service;

import com.example.Project.dto.request.charge.ChargeSearchRequest;
import com.example.Project.dto.request.resident.ResidentRequest;
import com.example.Project.dto.request.resident.ResidentSearchRequest;
import com.example.Project.entity.Apartment;
import com.example.Project.entity.Charge;
import com.example.Project.entity.Resident;
import com.example.Project.mapper.ResidentMapper;
import com.example.Project.repository.ResidentRepository;
import com.example.Project.utils.PredicateBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
    @Autowired
    PredicateBuilder predicateBuilder;
    @PersistenceContext
    EntityManager entityManager;

    public Resident create(@Valid ResidentRequest request){
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

    public Resident updateById(String id, @Valid ResidentRequest request){
        Resident resident = getById(id);
        Apartment apartment = apartmentService.getById(request.getApartmentId());
        residentMapper.mapResident(resident, request);
        resident.setApartment(apartment);

        return residentRepository.save(resident);
    }

    public void deleteById(String id){
        residentRepository.deleteById(id);
    }

    public List<Resident> search(@Valid ResidentSearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Resident> criteriaQuery = criteriaBuilder.createQuery(Resident.class);
        Root<Resident> root = criteriaQuery.from(Resident.class);
        List<Predicate> predicates = predicateBuilder.createPredicatesToSearch(request, criteriaBuilder, root);
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));
        return  entityManager.createQuery(criteriaQuery).getResultList();
    }
}
