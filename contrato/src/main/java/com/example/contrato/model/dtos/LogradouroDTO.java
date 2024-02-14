package com.example.contrato.model.dtos;

import com.example.contrato.model.TipoLogradouro;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class LogradouroDTO {

    private String nome;

    private Long idTipoLogradouro;
}
