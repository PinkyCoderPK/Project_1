package com.example.Project.repository;

import com.example.Project.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, String> {
}
