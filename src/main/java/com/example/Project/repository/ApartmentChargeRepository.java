package com.example.Project.repository;

import com.example.Project.entity.ApartmentCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApartmentChargeRepository extends JpaRepository<ApartmentCharge, String> {
    List<ApartmentCharge> findByApartmentId(String apartmentId);
}
