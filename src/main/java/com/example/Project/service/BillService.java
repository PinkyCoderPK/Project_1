package com.example.Project.service;
import com.example.Project.dto.request.apartmentCharge.ApartmentChargeRequest;
import com.example.Project.dto.request.bill.BillRequest;
import com.example.Project.dto.request.bill.BillSearchRequest;
import com.example.Project.entity.ApartmentCharge;
import com.example.Project.entity.Bill;
import com.example.Project.mapper.ApartmentChargeMapper;
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
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    @Autowired
    private ApartmentChargeMapper apartmentChargeMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Bill create(BillRequest request) {
        List<ApartmentCharge> apartmentChargeList = new ArrayList<>();
        Bill bill = billMapper.toBill(request);
        for(ApartmentChargeRequest request1 : request.getApartmentChargeRequestList()) {
            request1.setApartmentId(request.getApartmentId());
            ApartmentCharge apartmentCharge = apartmentChargeService.create(request1);
            apartmentCharge.setBill(bill);
            apartmentChargeList.add(apartmentCharge);
        }
        bill.setApartmentChargeList(apartmentChargeList);
        return billRepository.save(bill);
    }

    public List<Bill> getAll() {
        return billRepository.findAll();
    }

    public Bill getById(String id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy hóa đơn"));
    }

    public List<Bill> search(@Valid BillSearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bill> query = criteriaBuilder.createQuery(Bill.class);
        Root<Bill> root = query.from(Bill.class);
        List<Predicate> predicates = predicateBuilder.createPredicatesToSearch(request, criteriaBuilder, root);
        query.select(root).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }

    @Transactional
    public void deleteById(String id) {
        billRepository.deleteById(id);
    }

    public void deleteAll() {
        billRepository.deleteAll();
    }

    @Transactional
    public Bill updateById(String id, BillRequest request) {
        Bill bill = getById(id);
        List<ApartmentCharge> apartmentChargeList = bill.getApartmentChargeList();
        List<ApartmentChargeRequest> apartmentChargeRequestList = request.getApartmentChargeRequestList();
        List<ApartmentCharge> updateApartmentChargeList = new ArrayList<>();
        for (int i = 0;i < apartmentChargeList.size();i++)
        {
            ApartmentCharge apartmentCharge = apartmentChargeList.get(i);
            ApartmentChargeRequest apartmentChargeRequest = apartmentChargeRequestList.get(i);
            apartmentChargeMapper.mapApartmentCharge(apartmentCharge, apartmentChargeRequest);
            updateApartmentChargeList.add(apartmentCharge);
        }
        bill.setApartmentChargeList(updateApartmentChargeList);
        billMapper.mapBill(bill, request);
        return billRepository.save(bill);
    }

}
