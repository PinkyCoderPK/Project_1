package work.ngochuyen.spring.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import work.ngochuyen.spring.auth.dto.reponse.UserDTO;
import work.ngochuyen.spring.auth.repository.UserRepository;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService {

    @Autowired
    private UserRepository userRepository;

    @Value("${token.jwt.secret}") // Load secret from application.properties
    private String secretKey;

    @Value("${token.jwt.expiration}") // Load token expiration from config
    private long expirationTime;

    @Bean
    public Key signingKey() {
        return new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    // Generate JWT token
    public void generateToken(UserDTO userDTO) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(userDTO.getUserId()));
        Instant now = Instant.now();
        Instant expired = now.plusSeconds(expirationTime);

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expired))
                .signWith(signingKey(), SignatureAlgorithm.HS256) // Use the signingKey() method
                .compact();

//        userDTO.setAccessToken(accessToken);
//        userDTO.setAccessTokenExpired(expired.toEpochMilli());
    }

    public String extractSubject(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null; // Consider logging the exception for better debugging
        }
    }

    private final Set<String> tokenBlacklist = new HashSet<>(); // Danh sách đen token

    // Thêm token vào blacklist
    public void invalidateToken(String token) {
        tokenBlacklist.add(token); // Thêm token vào danh sách blacklist
    }

    // Kiểm tra token có bị vô hiệu hóa không
    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklist.contains(token); // Kiểm tra token có bị vô hiệu hóa không
    }

    // Phương thức logout
    public void logout(String token) {
        invalidateToken(token); // Thêm token vào blacklist
    }
}
