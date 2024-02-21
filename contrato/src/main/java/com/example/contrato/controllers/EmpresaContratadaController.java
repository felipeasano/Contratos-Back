package com.example.contrato.controllers;

import com.example.contrato.model.EmpresaContratada;
import com.example.contrato.model.Endereco;
import com.example.contrato.model.RepEmpContratada;
import com.example.contrato.model.dtos.EmpresaContratadaDTO;
import com.example.contrato.repositories.EmpresaContratadaRepository;
import com.example.contrato.repositories.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("empresa")
public class EmpresaContratadaController {

    private final EmpresaContratadaRepository empresaContratadaRepository;
    private final EnderecoRepository enderecoRepository;

    @PostMapping
    public ResponseEntity<EmpresaContratada> create(@RequestBody EmpresaContratadaDTO empresaform){
        Optional<Endereco> enderecoOpt = enderecoRepository.findById(empresaform.getIdEndereco());

        if(!enderecoOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }

        EmpresaContratada empresa = new EmpresaContratada();

        empresa.setEndereco(enderecoOpt.get());
        empresa.setCnpj(empresaform.getCnpj());
        empresa.setNome(empresaform.getNome());
        empresa.setNumEnd(empresaform.getNumEnd());
        empresa.setComplementoEnd(empresaform.getComplementoEnd());

        empresa = empresaContratadaRepository.save(empresa);
        return ResponseEntity.ok(empresa);
    }

    @GetMapping
    public ResponseEntity<List<EmpresaContratada>> list(){
        List<EmpresaContratada> representantes = empresaContratadaRepository.findAll();
        return ResponseEntity.ok(representantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Optional<EmpresaContratada> empresaOpt = empresaContratadaRepository.findById(id);

        if(empresaOpt.isPresent()){
            return ResponseEntity.ok(empresaOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody EmpresaContratada empresa, @PathVariable Long id){
        empresa.setId(id);
        empresaContratadaRepository.saveAndFlush(empresa);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        empresaContratadaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}