package com.example.contrato.repositories;

import com.example.contrato.model.Bairro;
import com.example.contrato.model.Cidade;
import com.example.contrato.model.Endereco;
import com.example.contrato.model.Logradouro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByLogradouroAndBairroAndCidade(Logradouro logradouro, Bairro bairro, Cidade cidade);
    Optional<Endereco> findByCep(String cep);
}
