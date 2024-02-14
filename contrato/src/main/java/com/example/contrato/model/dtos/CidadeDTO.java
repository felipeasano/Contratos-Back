package com.example.contrato.model.dtos;

import com.example.contrato.model.UnidadeFederacao;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class CidadeDTO {

    private String nome;

    private Long idUnidadeFederacao;
}
