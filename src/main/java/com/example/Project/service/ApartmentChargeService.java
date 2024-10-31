package com.example.Project.service;

import com.example.Project.dto.request.ApartmentChargeRequest;
import com.example.Project.entity.ApartmentCharge;
import com.example.Project.mapper.ApartmentChargeMapper;
import com.example.Project.repository.ApartmentChargeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class ApartmentChargeService {
    @Autowired
    private ApartmentChargeRepository apartmentChargeRepository;

    @Autowired
    private ApartmentChargeMapper apartmentChargeMapper;

    @PersistenceContext
    private EntityManager entityManager;

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

    public List<Predicate> _createPredicates(ApartmentChargeRequest request, CriteriaBuilder criteriaBuilder, Root<ApartmentCharge> root ) {
        List<Predicate> predicates = new ArrayList<>();
        for(Field field : ApartmentChargeRequest.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(request);
                if(value != null) {
                    predicates.add(criteriaBuilder.equal(root.get(field.getName()), value));
                }
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return predicates;
    }

    public List<ApartmentCharge> search(ApartmentChargeRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // Xac dinh kieu tra ve
        CriteriaQuery<ApartmentCharge> query = criteriaBuilder.createQuery(ApartmentCharge.class);
        // Xau dung cac dieu kien loc
        Root<ApartmentCharge> root = query.from(ApartmentCharge.class);
        List<Predicate> predicates = _createPredicates(request, criteriaBuilder, root);

        query.select(root).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
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
            apartmentChargeMapper.mapApartmentCharge(apartmentCharge, request);

            return apartmentChargeRepository.save(apartmentCharge);
        }
        else throw new RuntimeException("ApartmentCharge not found with id: " + id);
    }
}
