package com.example.contrato.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;

    @ManyToOne
    @JoinColumn(name = "fk_cidade")
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "fk_bairro")
    private Bairro bairro;

    @ManyToOne
    @JoinColumn(name = "fk_logradouro")
    private Logradouro logradouro;
}
