package com.example.contrato.controllers;

import com.example.contrato.model.Logradouro;
import com.example.contrato.model.TipoLogradouro;
import com.example.contrato.model.dtos.LogradouroDTO;
import com.example.contrato.repositories.LogradouroRepository;
import com.example.contrato.repositories.TipoLogradouroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("logradouro")
public class LogradouroController {

    private final LogradouroRepository logradouroRepository;
    private final TipoLogradouroRepository tipoLogradouroRepository;

    @PostMapping
    public ResponseEntity<Logradouro> create(@RequestBody LogradouroDTO logradouroform){
        Optional<TipoLogradouro> tipoOpt = tipoLogradouroRepository.findById(logradouroform.getIdTipoLogradouro());

        if(!tipoOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Logradouro logradouro = new Logradouro();

        logradouro.setNome(logradouroform.getNome());
        logradouro.setTipoLogradouro(tipoOpt.get());
        logradouro = logradouroRepository.save(logradouro);
        return ResponseEntity.ok(logradouro);
    }

    @GetMapping
    public ResponseEntity<List<Logradouro>> list(){
        List<Logradouro> logradouros = logradouroRepository.findAll();
        return ResponseEntity.ok(logradouros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Optional<Logradouro> logradouroOpt = logradouroRepository.findById(id);

        if(logradouroOpt.isPresent()){
            return ResponseEntity.ok(logradouroOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Logradouro logradouro, @PathVariable Long id){
        logradouro.setId(id);
        logradouroRepository.saveAndFlush(logradouro);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        logradouroRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
