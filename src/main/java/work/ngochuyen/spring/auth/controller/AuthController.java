package work.ngochuyen.spring.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import work.ngochuyen.spring.auth.dto.reponse.IntrospectResponse;
import work.ngochuyen.spring.auth.dto.request.IntrospectRequest;
import work.ngochuyen.spring.auth.dto.request.LoginRequest;
import work.ngochuyen.spring.auth.dto.request.RegisterRequest;
import work.ngochuyen.spring.auth.service.UserService;
import work.ngochuyen.spring.common.dto.BaseResponse;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {//quan ly mapping
    @Autowired
    UserService userService;
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
}
