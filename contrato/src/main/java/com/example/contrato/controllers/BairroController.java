package com.example.contrato.controllers;

import com.example.contrato.model.Bairro;
import com.example.contrato.repositories.BairroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("bairro")
public class BairroController {

    private final BairroRepository bairroRepository;

    @PostMapping
    public ResponseEntity<Bairro> create(@RequestBody Bairro bairroform){
        Bairro bairro = bairroRepository.save(bairroform);
        return ResponseEntity.ok(bairro);
    }

    @GetMapping
    public ResponseEntity<List<Bairro>> list(){
        List<Bairro> bairros = bairroRepository.findAll();
        return ResponseEntity.ok(bairros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Optional<Bairro> bairroOpt = bairroRepository.findById(id);

        if(bairroOpt.isPresent()){
            return ResponseEntity.ok(bairroOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Bairro bairro, @PathVariable Long id){
        bairro.setId(id);
        bairroRepository.saveAndFlush(bairro);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bairroRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}