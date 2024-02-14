package com.example.contrato.controllers;

import com.example.contrato.model.EmpresaContratada;
import com.example.contrato.model.Endereco;
import com.example.contrato.model.RepEmpContratada;
import com.example.contrato.model.dtos.RepEmpContratadaDTO;
import com.example.contrato.repositories.EmpresaContratadaRepository;
import com.example.contrato.repositories.EnderecoRepository;
import com.example.contrato.repositories.RepEmpContratadaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("representante")
public class RepEmpContratadaController {

    private final RepEmpContratadaRepository repEmpContratadaRepository;
    private final EmpresaContratadaRepository empresaContratadaRepository;
    private final EnderecoRepository enderecoRepository;
    @PostMapping
    public ResponseEntity<RepEmpContratada> create(@RequestBody RepEmpContratadaDTO representanteform){
        Optional<EmpresaContratada> empresaOpt = empresaContratadaRepository.findById(representanteform.getIdEmpresa());
        Optional<Endereco> enderecoOpt = enderecoRepository.findById(representanteform.getIdEndereco());

        if(!empresaOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }

        if(!enderecoOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }

        RepEmpContratada representante = new RepEmpContratada();
        Endereco endereco = new Endereco();

        representante.setNome(representanteform.getNome());
        representante.setEmpresa(empresaOpt.get());
        representante.setCpf(representanteform.getCpf());
        representante.setNumEnd(representanteform.getNumEnd());
        representante.setComplementoEnd(representanteform.getComplementoEnd());
        representante.setEndereco(enderecoOpt.get());
        representante = repEmpContratadaRepository.save(representante);
        return ResponseEntity.ok(representante);
    }

    @GetMapping
    public ResponseEntity<List<RepEmpContratada>> list(){
        List<RepEmpContratada> representante = repEmpContratadaRepository.findAll();
        return ResponseEntity.ok(representante);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Optional<RepEmpContratada> representanteOpt = repEmpContratadaRepository.findById(id);

        if(representanteOpt.isPresent()){
            return ResponseEntity.ok(representanteOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody RepEmpContratada representante, @PathVariable Long id){
        representante.setId(id);
        repEmpContratadaRepository.saveAndFlush(representante);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        repEmpContratadaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}