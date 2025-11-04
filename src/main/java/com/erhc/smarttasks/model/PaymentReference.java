package com.erhc.smarttasks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_references")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String refCode;
    private Double amount;
    private String description;

    @Column(nullable = false)
    private String status; // PENDING, PAID

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User payer; // Padre o usuario que paga

}


