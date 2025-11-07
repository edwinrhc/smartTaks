package com.erhc.smarttasks.service.impl;


import com.erhc.smarttasks.model.ComprobantePago;
import com.erhc.smarttasks.model.PaymentReference;
import com.erhc.smarttasks.repository.ComprobantePagoRepository;
import com.erhc.smarttasks.service.FacturacionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FacturacionServiceImpl implements FacturacionService {

    private final ComprobantePagoRepository comprobanteRepo;

    @Override
    public void generarDesdePago(PaymentReference pago) {
        ComprobantePago c = new ComprobantePago();
        c.setSerie("BOLETA");
        c.setNumero("B001");
        c.setTipo("BOLETA");
        c.setNumero(UUID.randomUUID().toString().substring(0,8));
        c.setTotal(pago.getAmount());
        c.setCustomerName(pago.getPayer() != null ? pago.getPayer().getUsername() : "Cliente");
        c.setSunatStatus("SIMULADO");
        c.setCreatedAt(LocalDateTime.now());

        comprobanteRepo.save(c);
        System.out.println("Comprobante generado para pago "+ pago.getRefCode());

    }
}
