package com.example.contrato.model.dtos;

import com.example.contrato.model.Contrato;
import com.example.contrato.model.Endereco;
import com.example.contrato.model.RepEmpContratada;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class EmpresaContratadaDTO {

    private String nome;

    private String cnpj;

    private String numEnd;

    private String complementoEnd;

    private Long idEndereco;
}
