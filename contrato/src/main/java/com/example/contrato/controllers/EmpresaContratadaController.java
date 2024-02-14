package com.example.contrato.controllers;

import com.example.contrato.model.EmpresaContratada;
import com.example.contrato.model.RepEmpContratada;
import com.example.contrato.repositories.EmpresaContratadaRepository;
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

    @PostMapping
    public ResponseEntity<EmpresaContratada> create(@RequestBody EmpresaContratada empresaform){
        EmpresaContratada empresa = empresaContratadaRepository.save(empresaform);
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