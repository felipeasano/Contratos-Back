package com.example.contrato.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class GesEmpContratante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String numEnd;

    private String complementoEnd;

    @ManyToOne
    @JoinColumn(name = "fk_endereco")
    private Endereco endereco;
}
