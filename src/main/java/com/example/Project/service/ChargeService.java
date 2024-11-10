package com.example.Project.service;

import com.example.Project.dto.request.charge.ChargeCreateRequest;
import com.example.Project.dto.request.charge.ChargeSearchRequest;
import com.example.Project.dto.request.charge.ChargeUpdateRequest;
import com.example.Project.entity.Charge;
import com.example.Project.mapper.ChargeMapper;
import com.example.Project.repository.ChargeRepository;
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

import java.io.File;
import java.lang.reflect.Field;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class ChargeService {
    @Autowired
    private ChargeRepository chargeRepository;

    @Autowired
    private ChargeMapper chargeMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public Charge create(ChargeCreateRequest chargeCreateRequest) {
        Charge charge = chargeMapper.mapCreateCharge(chargeCreateRequest);
        return chargeRepository.save(charge);
    }
    public Charge getById(String id) {
        return chargeRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay id phi"));
    }
    public List<Charge> getAll() {
        return chargeRepository.findAll();
    }
    public List<Predicate> createPredicates(ChargeSearchRequest request, CriteriaBuilder criteriaBuilder, Root<Charge> root) {
        List<Predicate> predicates = new ArrayList<>();
        for(Field field : ChargeSearchRequest.class.getDeclaredFields()){
            field.setAccessible(true);
            try{
                Object value = field.get(request);
                if(value != null){
                    predicates.add(criteriaBuilder.equal(root.get(field.getName()), value));
                }
            }
            catch(IllegalAccessException e){
                throw new RuntimeException("Khong tim thay " + field.getName());
            }
        }
        return predicates;
    }
    public List<Charge> search(@Valid ChargeSearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Charge> criteriaQuery = criteriaBuilder.createQuery(Charge.class);
        Root<Charge> root = criteriaQuery.from(Charge.class);
        List<Predicate> predicates = createPredicates(request, criteriaBuilder, root);
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));
        return  entityManager.createQuery(criteriaQuery).getResultList();
    }
    public void deleteById(String id) {
        chargeRepository.deleteById(id);
    }
    public void deleteAll(){
        chargeRepository.deleteAll();
    }
    public Charge updateById(String id, ChargeUpdateRequest chargeUpdateRequest) {
        Charge charge = getById(id);
        chargeMapper.mapUpdateCharge(charge, chargeUpdateRequest);
        return chargeRepository.save(charge);
    }

}
