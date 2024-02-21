package com.example.contrato.controllers;

import com.example.contrato.model.*;
import com.example.contrato.model.dtos.EnderecoDTO;
import com.example.contrato.repositories.BairroRepository;
import com.example.contrato.repositories.CidadeRepository;
import com.example.contrato.repositories.EnderecoRepository;
import com.example.contrato.repositories.LogradouroRepository;
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
    private final CidadeRepository cidadeRepository;
    private final BairroRepository bairroRepository;
    private final LogradouroRepository logradouroRepository;
    @PostMapping
    public ResponseEntity<Endereco> create(@RequestBody EnderecoDTO enderecoform){
        Optional<Cidade> cidadeOpt = cidadeRepository.findById(enderecoform.getIdCidade());
        Optional<Bairro> bairroOpt = bairroRepository.findById(enderecoform.getIdBairro());
        Optional<Logradouro> logradouroOpt = logradouroRepository.findById(enderecoform.getIdLogradouro());

        if(!cidadeOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }
        if(!bairroOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }
        if(!logradouroOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Endereco endereco = new Endereco();

        endereco.setCep(enderecoform.getCep());
        endereco.setCidade(cidadeOpt.get());
        endereco.setBairro(bairroOpt.get());
        endereco.setLogradouro(logradouroOpt.get());
        endereco = enderecoRepository.save(endereco);
        return ResponseEntity.ok(endereco);
    }

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
