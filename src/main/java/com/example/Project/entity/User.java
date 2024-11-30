package com.example.Project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)//UUID ,AUTO
    private String userId;//danh dau day la id

    @Column(unique = true, nullable = false, length = 64)
    private String username;
    @Column(length = 512)
    private String password;
    @Column(length = 512)
    private String refreshToken;
    private Long refreshTokenExpired;
}
