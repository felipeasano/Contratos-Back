package com.example.contrato.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String objeto;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private float valorContratado;

    private int numEndereco;

    private String complementoEnd;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="fk_endereco")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "fk_empresa")
    private EmpresaContratada empresaContratada;

    @ManyToOne
    @JoinColumn(name = "fk_repEmpContratada")
    private RepEmpContratada repEmpContratada;

    @ManyToOne
    @JoinColumn(name = "fk_gesEmpContratante")
    private GesEmpContratante gesEmpContratante;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "fk_contrato")
    private List<ItemContrato> itensContratados;
}
