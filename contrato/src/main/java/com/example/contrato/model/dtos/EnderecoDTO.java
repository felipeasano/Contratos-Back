package com.example.contrato.model.dtos;

import com.example.contrato.model.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class EnderecoDTO {

    private String cep;

    @OneToMany(mappedBy = "endereco")
    private List<Contrato> contratos;

    @OneToMany(mappedBy = "endereco")
    private List<EmpresaContratada> empresasContratadas;

    @OneToMany(mappedBy = "endereco")
    private List<RepEmpContratada> repEmpContratada;

    @OneToMany(mappedBy = "endereco")
    private List<GesEmpContratante> gesEmpContratante;

    private Long idCidade;

    private Long idBairro;

    private Long idLogradouro;
}
