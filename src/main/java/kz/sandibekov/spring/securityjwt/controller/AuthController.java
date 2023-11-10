package kz.sandibekov.spring.securityjwt.controller;

import kz.sandibekov.spring.securityjwt.model.dto.JwtRequest;
import kz.sandibekov.spring.securityjwt.model.dto.RegistrationUserDto;
import kz.sandibekov.spring.securityjwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping("/test")
    public String test() {
        return "Working";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationUserDto", new RegistrationUserDto());
        return "registration";
    }

    @GetMapping("/authenticate")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<?> registerNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}
