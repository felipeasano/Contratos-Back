package com.example.contrato.model.dtos;

import com.example.contrato.model.EmpresaContratada;
import com.example.contrato.model.Endereco;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class RepEmpContratadaDTO {

    private String nome;

    private String cpf;

    private int numEnd;

    private String complementoEnd;

    Long idEndereco;

    Long idEmpresa;
}
