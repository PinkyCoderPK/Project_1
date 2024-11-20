package work.ngochuyen.spring.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import work.ngochuyen.spring.auth.dto.request.LoginRequest;
import work.ngochuyen.spring.auth.dto.request.RegisterRequest;
import work.ngochuyen.spring.auth.service.UserService;
import work.ngochuyen.spring.common.dto.BaseResponse;
import work.ngochuyen.spring.common.dto.ErrorResponse;
import work.ngochuyen.spring.common.security.JwtService;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {//quan ly mapping
    @Autowired
    UserService userService;
    @Autowired
    private JwtService jwtService;
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<?> login(@Valid  @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @GetMapping("/verify")
    public BaseResponse<?> verify() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String userId = authentication.getName();
            return userService.get(userId);
        } else {
            throw new BadCredentialsException("");
        }
    }

    @PostMapping("/logout")
    public BaseResponse<String> logout(HttpServletRequest request) {
        // Lấy token từ header Authorization
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            // Không tìm thấy token hoặc không hợp lệ
            return new BaseResponse<>(HttpStatus.OK.value(), "lgout failure", null);
        }

        String token = authorizationHeader.substring(7); // Bỏ "Bearer " ra khỏi chuỗi token

        // Thêm token vào blacklist
        jwtService.logout(token);

        // Trả về phản hồi thành công
        return new BaseResponse<>(HttpStatus.OK.value(), "logout successful", null);
    }

}
