package com.erhc.smarttasks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comprobante_pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComprobantePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    * Tipo de comprobante BOLETA o FACTURA
    */
    @Column(nullable = false, length = 10)
    private String tipo;


    /*
    * Serie: por ejemplo B001 o F001
    * */
    @Column(nullable = false, length = 10)
    private String serie;

    /**
     * Número correlativo del comprobante
     */
    @Column(nullable = false, length = 20)
    private String numero;

    /**
     * Nombre del cliente (padre, apoderado, empresa, etc.)
     */
    @Column(nullable = false, length = 100)
    private String customerName;

    /**
     * Documento del cliente (DNI o RUC)
     */
    @Column(length = 20)
    private String customerDoc;

    /**
     * Monto total del comprobante
     */
    @Column(nullable = false)
    private Double total;

    /**
     * Estado frente a SUNAT u OSE (PENDIENTE, ENVIADO, ACEPTADO, RECHAZADO, SIMULADO)
     */
    @Column(nullable = false, length = 20)
    private String sunatStatus;

    /**
     * Fecha de emisión del comprobante
     */
    private LocalDateTime createdAt;

    /**
     * Ruta o referencia al XML generado (opcional)
     */
    private String xmlPath;

    /**
     * Ruta o referencia al PDF generado (opcional)
     */
    private String pdfPath;

    /**
     * Relación con el pago o referencia original
     */
    @ManyToOne
    @JoinColumn(name = "payment_reference_id")
    private PaymentReference paymentReference;
}
