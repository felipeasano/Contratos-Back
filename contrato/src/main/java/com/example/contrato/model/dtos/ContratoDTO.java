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

    private int numEndereco;

    private String complementoEnd;

    private Long idEndereco;

    private Long idLogradouro;
    private Long idCidade;
    private Long idBairro;
    // private String cep;

    private Long idEmpresa;

    private Long idRepresentante;

    private Long idGestor;

    private List<ItemContratoDTO> itensContrato;
}
