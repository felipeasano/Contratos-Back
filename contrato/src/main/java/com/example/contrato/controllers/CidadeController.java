package com.example.contrato.controllers;

import com.example.contrato.model.Cidade;
import com.example.contrato.model.UnidadeFederacao;
import com.example.contrato.model.dtos.CidadeDTO;
import com.example.contrato.repositories.CidadeRepository;
import com.example.contrato.repositories.UnidadeFederacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("cidade")
public class CidadeController {

    private final UnidadeFederacaoRepository unidadeFederacaoRepository;
    private final CidadeRepository cidadeRepository;

    @PostMapping
    public ResponseEntity<Cidade> create(@RequestBody CidadeDTO cidadeform){
        Optional<UnidadeFederacao> ufOpt = unidadeFederacaoRepository.findById(cidadeform.getIdUnidadeFederacao());

        if(!ufOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Cidade cidade = new Cidade();

        cidade.setNome((cidadeform.getNome()));
        cidade.setUnidadeFederacao(ufOpt.get());
        cidade = cidadeRepository.save(cidade);

        return ResponseEntity.ok(cidade);
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> list(){
        List<Cidade> cidades = cidadeRepository.findAll();
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Optional<Cidade> cidadeOpt = cidadeRepository.findById(id);

        if(cidadeOpt.isPresent()){
            return ResponseEntity.ok(cidadeOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Cidade cidade, @PathVariable Long id){
        cidade.setId(id);
        cidadeRepository.saveAndFlush(cidade);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cidadeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}