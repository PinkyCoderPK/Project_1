package com.example.Project.service;

import com.example.Project.dto.request.ApartmentChargeCreateRequest;
import com.example.Project.dto.request.ApartmentChargeSearchRequest;
import com.example.Project.dto.request.ApartmentChargeUpdateRequest;
import com.example.Project.entity.ApartmentCharge;
import com.example.Project.mapper.ApartmentChargeMapper;
import com.example.Project.repository.ApartmentChargeRepository;
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

@Data
@Service
public class ApartmentChargeService {
    @Autowired
    private ApartmentChargeRepository apartmentChargeRepository;

    @Autowired
    private ApartmentChargeMapper apartmentChargeMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public ApartmentCharge create(ApartmentChargeCreateRequest request) {
        ApartmentCharge apartmentCharge = apartmentChargeMapper.toApartmentCharge(request);
        return apartmentChargeRepository.save(apartmentCharge);
    }

    public List<ApartmentCharge> getAll() {
        return apartmentChargeRepository.findAll();
    }

    public ApartmentCharge getById(String id) {
        return apartmentChargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin phí của hộ chung cư"));
    }

    // Tổng hợp các điều kiện truy vấn
    public List<Predicate> _createPredicates(ApartmentChargeSearchRequest request, CriteriaBuilder criteriaBuilder, Root<ApartmentCharge> root ) {
        List<Predicate> predicates = new ArrayList<>();
        for(Field field : ApartmentChargeSearchRequest.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(request);
                if(value != null) {
                    predicates.add(criteriaBuilder.equal(root.get(field.getName()), value));
                }
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException("Thất bại trong truy cập trường: " + field.getName());
            }
        }
        return predicates;
    }

    public List<ApartmentCharge> search(@Valid ApartmentChargeSearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Xác định kiểu đối tượng trả về
        CriteriaQuery<ApartmentCharge> query = criteriaBuilder.createQuery(ApartmentCharge.class);

        // Đối tượng root đại diện cho bảng ApartmentCharge, cho phép truy cập vào các trường của bảng
        Root<ApartmentCharge> root = query.from(ApartmentCharge.class);

        // Danh sách các điều kiện truy vấn
        List<Predicate> predicates = _createPredicates(request, criteriaBuilder, root);

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
        apartmentChargeMapper.mapApartmentCharge(apartmentCharge, request);
        return apartmentChargeRepository.save(apartmentCharge);
    }
}
