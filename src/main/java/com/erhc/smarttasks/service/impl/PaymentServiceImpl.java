package com.erhc.smarttasks.service.impl;

import com.erhc.smarttasks.dto.PaymentRequest;
import com.erhc.smarttasks.model.PaymentReference;
import com.erhc.smarttasks.repository.PaymentReferenceRepository;
import com.erhc.smarttasks.service.FacturacionService;
import com.erhc.smarttasks.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentReferenceRepository referenceRepository;
    private final FacturacionService facturacionService;

    @Override
    public PaymentReference createReference(PaymentRequest request) {
        PaymentReference ref = PaymentReference.builder()
                .refCode(UUID.randomUUID().toString().substring(0, 8))
                .amount(request.getAmount())
                .description(request.getDescription())
                .status("PENDING")
                .payer(request.getUser()) ///  opcional si pasas el usuario autenticado
                .build();
        return referenceRepository.save(ref);
    }

    @Override
    public void confirmPayment(String refCode) {
        PaymentReference ref = referenceRepository.findByRefCode(refCode)
                .orElseThrow( () -> new RuntimeException("Referencia no encontrada"));

        ref.setStatus("PAID");
        referenceRepository.save(ref);

        facturacionService.generarDesdePago(ref);

    }
}
