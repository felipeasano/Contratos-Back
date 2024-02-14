package com.example.contrato.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class EmpresaContratada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cnpj;

    private String numEnd;

    private String complementoEnd;

    @ManyToOne
    @JoinColumn(name="fk_endereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "empresaContratada")
    private List<Contrato> contratos;

    @OneToMany(mappedBy = "empresa")
    private List<RepEmpContratada> representante;
}
