package com.example.Project.service;
import com.example.Project.dto.request.apartmentCharge.ApartmentChargeCreateRequest;
import com.example.Project.dto.request.bill.BillCreateRequest;
import com.example.Project.dto.request.bill.BillSearchRequest;
import com.example.Project.entity.ApartmentCharge;
import com.example.Project.entity.Bill;
import com.example.Project.mapper.BillMapper;
import com.example.Project.repository.ApartmentChargeRepository;
import com.example.Project.repository.BillRepository;
import com.example.Project.utils.PredicateBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private ApartmentChargeRepository apartmentChargeRepository;

    @Autowired
    PredicateBuilder predicateBuilder;

    @Autowired
    private ApartmentChargeService apartmentChargeService;

    @PersistenceContext
    private EntityManager entityManager;

    public Bill create(BillCreateRequest request) {
        List<ApartmentCharge> apartmentChargeList = new ArrayList<>();
        for(ApartmentChargeCreateRequest request1 : request.getApartmentChargeCreateRequestList()) {
            request1.setApartmentId(request.getApartmentId());
            ApartmentCharge apartmentCharge = apartmentChargeService.create(request1);
            apartmentChargeList.add(apartmentCharge);
        }
        Bill bill = billMapper.toBill(request);
        bill.setApartmentChargeList(apartmentChargeList);
        return billRepository.save(bill);
    }

    public List<Bill> getAll() {
        return billRepository.findAll();
    }

    public Bill getById(String id) {
        return billRepository.findById(id).get();
    }

    public List<Bill> search(@Valid BillSearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bill> query = criteriaBuilder.createQuery(Bill.class);
        Root<Bill> root = query.from(Bill.class);
        List<Predicate> predicates = predicateBuilder.createPredicatesToSearch(request, criteriaBuilder, root);
        query.select(root).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }

    public void deleteById(String id) {
        billRepository.deleteById(id);
    }

    public void deleteAll() {
        billRepository.deleteAll();
    }

//    public Bill updateById(String id, BillUpdateRequest request) {
//        Bill bill = getById(id);
//
//        billMapper.mapBill(bill, request);
//        List<ApartmentCharge> apartmentCharges = apartmentChargeRepository.findAllById(request.getApartmentChargeIds());
//        if (apartmentCharges.isEmpty() || apartmentCharges.size() != request.getApartmentChargeIds().size()) {
//            throw new IllegalArgumentException("Một số ApartmentCharge ID không tồn tại trong hệ thống.");
//        }
//        bill.totalPayment();
//        bill.calculateTotalAmountDue();
//        bill.setApartmentChargeList(apartmentCharges);
//
//        return billRepository.save(bill);
//    }


}
