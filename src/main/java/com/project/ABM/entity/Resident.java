package com.project.ABM.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String residentId;

    private String apartmentId;

    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    private String username;
    @Column(name = "role", columnDefinition = "BOOLEAN")
    private Boolean role;
    private String phoneNumber;
    private LocalDate birthday;
    private String permanentAddress;
    private String temporaryAddress;
    private LocalDate createAt;
    private LocalDate updateAt;

}
