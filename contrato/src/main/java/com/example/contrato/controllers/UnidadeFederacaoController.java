package com.example.contrato.controllers;

import com.example.contrato.model.UnidadeFederacao;
import com.example.contrato.repositories.UnidadeFederacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("unidadeFederacao")
public class UnidadeFederacaoController {

    private final UnidadeFederacaoRepository ufRepository;


    public UnidadeFederacaoController(UnidadeFederacaoRepository ufRepository) {
        this.ufRepository = ufRepository;
    }

    @PostMapping
    public ResponseEntity<UnidadeFederacao> creat(@RequestBody UnidadeFederacao ufform){
        UnidadeFederacao uf = ufRepository.save(ufform);
        return ResponseEntity.ok(uf);
    }

    @GetMapping
    public ResponseEntity<List<UnidadeFederacao>> list(){
        List<UnidadeFederacao> ufs = ufRepository.findAll();
        return ResponseEntity.ok(ufs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Optional<UnidadeFederacao> ufOpt = ufRepository.findById(id);

        if(ufOpt.isPresent()){
            return ResponseEntity.ok(ufOpt.get());
        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody UnidadeFederacao uf, @PathVariable Long id){
        uf.setId(id);
        ufRepository.saveAndFlush(uf);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ufRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}