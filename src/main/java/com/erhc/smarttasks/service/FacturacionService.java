package com.erhc.smarttasks.service;

import com.erhc.smarttasks.model.PaymentReference;

public interface FacturacionService {

    void generarDesdePago(PaymentReference pago);
}
