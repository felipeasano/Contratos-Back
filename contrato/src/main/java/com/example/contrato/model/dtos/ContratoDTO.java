package com.example.contrato.model.dtos;

import com.example.contrato.model.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ContratoDTO {

    private String objeto;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private float valorContratado;

    private int numEndereco;

    private String complementoEnd;

    private Long idEndereco;

    private Long idempresa;

    private Long idRepresentante;

    private Long idGestor;

    @OneToMany(mappedBy = "contrato", cascade = {CascadeType.REMOVE})
    private List<ItemContrato> itensContratados;
}
