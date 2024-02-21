package com.example.contrato.model.dtos;

import com.example.contrato.model.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class EnderecoDTO {

    private String cep;

    private Long idCidade;

    private Long idBairro;

    private Long idLogradouro;
}
