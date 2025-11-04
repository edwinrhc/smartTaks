package com.erhc.smarttasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeRestController {

    @GetMapping("/admin/data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminData(){
        return ResponseEntity.ok("Solo los ADMIN pueden acceder a este recurso");
    }

    @GetMapping("/user/data")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<String> userData(){
        return ResponseEntity.ok("Usuarios autenticados pueden ver esto");
    }

}
