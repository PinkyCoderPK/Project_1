package work.ngochuyen.spring.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "spring_user") // Table name in database
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//UUID ,AUTO
    private Long userId;//danh dau day la id va tu duoc tang gia tri

    @Column(unique = true, nullable = false, length = 64)
    private String username;
    @Column(length = 512)
    private String password;
    @Column(length = 512)
    private String refreshToken;
    private Long refreshTokenExpired;
}
