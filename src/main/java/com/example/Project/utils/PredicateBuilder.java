package com.example.Project.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PredicateBuilder {
    // Tổng hợp các điều kiện truy vấn
    public <R, T> List<Predicate> createPredicatesToSearch(R request, CriteriaBuilder criteriaBuilder, Root<T> root ) {
        List<Predicate> predicates = new ArrayList<>();
        for(Field field : request.getClass().getDeclaredFields()) {
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
}
