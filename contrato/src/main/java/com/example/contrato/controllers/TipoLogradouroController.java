package com.example.contrato.controllers;

import com.example.contrato.model.TipoLogradouro;
import com.example.contrato.repositories.TipoLogradouroRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tipoLogradouro")
public class TipoLogradouroController {

    private final TipoLogradouroRepository typeRepository;

    public TipoLogradouroController(TipoLogradouroRepository typeRepository) {

        this.typeRepository = typeRepository;
    }

    @PostMapping
    public ResponseEntity<TipoLogradouro> create(@RequestBody TipoLogradouro typeform) {
        TipoLogradouro type = typeRepository.save(typeform);
        return ResponseEntity.ok(type);
    }

    @GetMapping
    public ResponseEntity<List<TipoLogradouro>> list() {
        List<TipoLogradouro> types = typeRepository.findAll();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Optional<TipoLogradouro> typeOpt = typeRepository.findById(id);

        if (typeOpt.isPresent()) {
            return ResponseEntity.ok(typeOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody TipoLogradouro type, @PathVariable Long id) {
        type.setId(id);
        typeRepository.saveAndFlush(type);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        typeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}