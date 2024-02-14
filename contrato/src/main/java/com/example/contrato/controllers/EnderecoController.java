package com.example.contrato.controllers;

import com.example.contrato.model.Contrato;
import com.example.contrato.model.Endereco;
import com.example.contrato.repositories.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("endereco")
public class EnderecoController {

    private final EnderecoRepository enderecoRepository;

    @GetMapping
    public ResponseEntity<List<Endereco>> list(){
        List<Endereco> enderecos = enderecoRepository.findAll();
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);

        if(enderecoOpt.isPresent()){
            return ResponseEntity.ok(enderecoOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Endereco endereco, @PathVariable Long id){
        endereco.setId(id);
        enderecoRepository.saveAndFlush(endereco);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        enderecoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
