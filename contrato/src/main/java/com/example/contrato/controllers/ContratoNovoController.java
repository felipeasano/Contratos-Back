package com.example.contrato.controllers;

import com.example.contrato.model.Contrato;
import com.example.contrato.model.ContratoNovo;
import com.example.contrato.model.dtos.ContratoNovoDTO;
import com.example.contrato.repositories.ContratoNovoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("contratonovo")
public class ContratoNovoController {

    private final ContratoNovoRepository contratoNovoRepository;

    @PostMapping
    public ResponseEntity<ContratoNovo> create(@RequestBody ContratoNovo contratoform){
        ContratoNovo contrato = contratoNovoRepository.save(contratoform);
        return ResponseEntity.ok(contrato);
    }

    @GetMapping
    public ResponseEntity<List<ContratoNovo>> list(){
        List<ContratoNovo> contratos = contratoNovoRepository.findAll();
        return ResponseEntity.ok(contratos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        contratoNovoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
