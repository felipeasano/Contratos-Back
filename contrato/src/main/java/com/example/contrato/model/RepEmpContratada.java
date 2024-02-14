package com.example.contrato.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RepEmpContratada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private int numEnd;

    private String complementoEnd;

    @ManyToOne
    @JoinColumn(name = "fk_endereco")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "fk_empresaContratada")
    private EmpresaContratada empresa;
}
