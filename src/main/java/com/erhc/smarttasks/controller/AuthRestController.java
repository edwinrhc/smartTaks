package com.erhc.smarttasks.controller;

import com.erhc.smarttasks.model.User;
import com.erhc.smarttasks.repository.UserRepository;
import com.erhc.smarttasks.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Asignar rol por defecto si no lo envía
        if(user.getRole() == null || user.getRole().isEmpty()){
            user.setRole("ROLE_USER");
        }
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
         // Busca el usuario en la BD para obtener su rol
            User dbUser = userRepository.findByUsername(user.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Genera el token incluyendo username + rol
            String token = jwtUtil.generateToken(dbUser.getUsername(), dbUser.getRole());


            return ResponseEntity.ok("Bearer "+ token);
        } catch (AuthenticationException e){
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
