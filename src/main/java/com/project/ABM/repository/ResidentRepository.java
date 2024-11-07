package com.project.ABM.repository;

import com.project.ABM.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, String> {
    boolean existsByUsername(String username);

}
