package com.example.contrato.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ItemContrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private float valorItemContrato;

    private LocalDate dataPagamento;

    private LocalDate dataVencimento;

    private int pago;

    private float valorPago;

    private int aprovadoPagar;

    @ManyToOne
    @JoinColumn(name = "fk_contrato")
    private Contrato contrato;
}
