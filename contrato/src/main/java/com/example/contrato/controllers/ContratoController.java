package com.example.contrato.controllers;

import com.example.contrato.model.Contrato;
import com.example.contrato.repositories.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("contrato")
public class ContratoController {

    private final ContratoRepository contratoRepository;

    @GetMapping
    public ResponseEntity<List<Contrato>> list(){
        List<Contrato> contratos = contratoRepository.findAll();
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Optional<Contrato> contratoOpt = contratoRepository.findById(id);

        if(contratoOpt.isPresent()){
            return ResponseEntity.ok(contratoOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Contrato contrato, @PathVariable Long id){
        contrato.setId(id);
        contratoRepository.saveAndFlush(contrato);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        contratoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}