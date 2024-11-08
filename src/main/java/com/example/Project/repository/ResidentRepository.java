package com.example.Project.repository;

import com.example.Project.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, String> {
    boolean existsByUsername(String username);

}
