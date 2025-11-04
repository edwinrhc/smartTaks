package com.erhc.smarttasks.service;


import com.erhc.smarttasks.dto.PaymentRequest;
import com.erhc.smarttasks.model.PaymentReference;

public interface PaymentService {

    PaymentReference createReference(PaymentRequest request);
    void confirmPayment(String refCode);
}
