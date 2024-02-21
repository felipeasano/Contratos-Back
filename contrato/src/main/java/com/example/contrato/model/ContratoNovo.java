package com.example.contrato.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class ContratoNovo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String objeto;

    private String dataInicio;

    private String dataFim;

    private String valorContratado;

    private String numEndereco;

    private String complementoEnd;

    private String logradouro;

    private String cidade;

    private String bairro;

    private String empresa;

    private String representante;

    private String gestor;
}
