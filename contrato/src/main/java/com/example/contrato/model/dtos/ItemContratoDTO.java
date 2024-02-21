package com.example.contrato.model.dtos;

import com.example.contrato.model.Contrato;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ItemContratoDTO {

    private String descricao;

    private float valorItemContrato;

    private LocalDate dataPagamento;

    private LocalDate dataVencimento;

    private boolean pago;

    private float valorPago;

    private boolean aprovadoPagar;
}
