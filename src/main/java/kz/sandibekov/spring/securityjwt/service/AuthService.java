package kz.sandibekov.spring.securityjwt.service;

import kz.sandibekov.spring.securityjwt.exception.AppError;
import kz.sandibekov.spring.securityjwt.model.dto.JwtRequest;
import kz.sandibekov.spring.securityjwt.model.dto.JwtResponse;
import kz.sandibekov.spring.securityjwt.model.dto.RegistrationUserDto;
import kz.sandibekov.spring.securityjwt.token.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;


    public ResponseEntity<?> createAuthToken(JwtRequest authRequest) {
        log.debug("Trying to authenticate user{}", authRequest.getUsername());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            log.debug("Inside of error case");
            return new ResponseEntity(new AppError(
                    HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(), "Password mismatch!"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(), "Username is already in use!"), HttpStatus.BAD_REQUEST);
        }
        userService.createUser(registrationUserDto);
        return ResponseEntity.ok("User with username " + registrationUserDto.getUsername() + " is created! With the token:");
    }
}
