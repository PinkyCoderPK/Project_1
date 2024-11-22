package com.example.Project.service;

import com.example.Project.dto.request.apartmentCharge.ApartmentChargeCreateRequest;
import com.example.Project.dto.request.apartmentCharge.ApartmentChargeSearchRequest;
import com.example.Project.dto.request.apartmentCharge.ApartmentChargeUpdateRequest;
import com.example.Project.dto.response.ApartmentChargeResponse;
import com.example.Project.entity.Apartment;
import com.example.Project.entity.ApartmentCharge;
import com.example.Project.entity.Charge;
import com.example.Project.mapper.ApartmentChargeMapper;
import com.example.Project.repository.ApartmentChargeRepository;
import com.example.Project.repository.ApartmentRepository;
import com.example.Project.repository.ChargeRepository;
import com.example.Project.utils.PredicateBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    ChargeRepository chargeRepository;

    @Autowired
    PredicateBuilder predicateBuilder;

    @Autowired
    ApartmentService apartmentService;

    @Autowired
    ChargeService chargeService;

    public ApartmentCharge create(ApartmentChargeCreateRequest request) {
        Apartment apartment = apartmentService.getById(request.getApartmentId());
        Charge charge = chargeService.getById(request.getChargeId());

        ApartmentCharge apartmentCharge = apartmentChargeMapper.toApartmentCharge(request);
        apartmentCharge.setApartment(apartment);
        apartmentCharge.setCharge(charge);

        return apartmentChargeRepository.save(apartmentCharge);
    }

    public List<ApartmentCharge> createMultiple(List<ApartmentChargeCreateRequest> requests) {
        List<ApartmentCharge> apartmentCharges = new ArrayList<>();
        for(ApartmentChargeCreateRequest request : requests) {
            Apartment apartment = apartmentService.getById(request.getApartmentId());
            Charge charge = chargeService.getById(request.getChargeId());

            ApartmentCharge apartmentCharge = apartmentChargeMapper.toApartmentCharge(request);
            apartmentCharge.setApartment(apartment);
            apartmentCharge.setCharge(charge);

            apartmentCharges.add(apartmentCharge);
        }
        return apartmentChargeRepository.saveAll(apartmentCharges);
    }

    public List<ApartmentCharge> getAll() {
        return apartmentChargeRepository.findAll();
    }

    public ApartmentCharge getById(String id) {
        return apartmentChargeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy thông tin phí của hộ chung cư"));
    }

    public List<ApartmentCharge> search(@Valid ApartmentChargeSearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Xác định kiểu đối tượng trả về
        CriteriaQuery<ApartmentCharge> query = criteriaBuilder.createQuery(ApartmentCharge.class);

        // Đối tượng root đại diện cho bảng ApartmentCharge, cho phép truy cập vào các trường của bảng
        Root<ApartmentCharge> root = query.from(ApartmentCharge.class);

        // Danh sách các điều kiện truy vấn
        List<Predicate> predicates = predicateBuilder.createPredicatesToSearch(request, criteriaBuilder, root);

        // Thực hiện truy vấn
        query.select(root).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

    public void deleteById(String id) {
        apartmentChargeRepository.deleteById(id);
    }

    public void deleteAll() {
        apartmentChargeRepository.deleteAll();
    }

    public ApartmentCharge updateById(String id, ApartmentChargeUpdateRequest request) {
        ApartmentCharge apartmentCharge = getById(id);

        Apartment apartment = apartmentService.getById(request.getApartmentId());
        Charge charge = chargeService.getById(request.getChargeId());

        apartmentChargeMapper.mapUpdateApartmentCharge(apartmentCharge, request);
        apartmentCharge.setApartment(apartment);
        apartmentCharge.setCharge(charge);

        return apartmentChargeRepository.save(apartmentCharge);
    }

}
