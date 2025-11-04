package com.erhc.smarttasks.dto;

import com.erhc.smarttasks.model.User;
import lombok.Data;

@Data
public class PaymentRequest {
    private Double amount;
    private String description;
    private User user;

}
