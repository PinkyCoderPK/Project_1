package com.example.Project.service;

import com.example.Project.dto.request.resident.ResidentCreateRequest;
import com.example.Project.dto.request.resident.ResidentSearchRequest;
import com.example.Project.dto.request.resident.ResidentUpdateRequest;
import com.example.Project.entity.Resident;
import com.example.Project.mapper.ResidentMapper;
import com.example.Project.repository.ApartmentRepository;
import com.example.Project.repository.ResidentRepository;
import com.example.Project.utils.PredicateBuilder;
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

import java.util.List;
import java.util.NoSuchElementException;

@Data
@Service
public class ResidentService {
    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private ResidentMapper residentMapper;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PredicateBuilder predicateBuilder;

    public Resident create(@Valid ResidentCreateRequest request){

        Resident resident = residentMapper.toResident(request);

        return residentRepository.save(resident);
    }

    public List<Resident> getAll(){
        return residentRepository.findAll();
    }

    public Resident getById(String id){
        return residentRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Không tìm thấy cư dân"));
    }

    public List<Resident> search(@Valid ResidentSearchRequest request){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Resident> criteriaQuery = criteriaBuilder.createQuery(Resident.class);
        Root<Resident> root = criteriaQuery.from(Resident.class);
        List<Predicate> predicates = predicateBuilder.createPredicatesToSearch(request,criteriaBuilder,root);
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getResultList();

    }
    public Resident updateById(String id, @Valid ResidentUpdateRequest request){
        Resident resident = getById(id);
        residentMapper.updateResident(resident, request);
        return residentRepository.save(resident);
    }

    public void deleteById(String id){
        residentRepository.deleteById(id);
    }
    public void deleleAll(){
        residentRepository.deleteAll();
    }
}