package com.example.Project.repository;

import com.example.Project.entity.ApartmentCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentChargeRepository extends JpaRepository<ApartmentCharge, String> {
}
