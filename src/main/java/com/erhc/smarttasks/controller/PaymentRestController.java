package com.erhc.smarttasks.controller;

import com.erhc.smarttasks.dto.PaymentRequest;
import com.erhc.smarttasks.model.PaymentReference;
import com.erhc.smarttasks.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentService paymentService;

    // Crear una referencia de pago
    @PostMapping("/create")
    public ResponseEntity<PaymentReference> createPayment(@RequestBody PaymentRequest request){
        PaymentReference ref = paymentService.createReference(request);
        return ResponseEntity.ok(ref);
    }

    // Simular un pago
    @PostMapping("/simulate")
    public ResponseEntity<String> simulatePayment(@RequestParam String refCode){
        paymentService.confirmPayment(refCode);
        return ResponseEntity.ok("Pago simulado y comprobante generado");
    }
}
