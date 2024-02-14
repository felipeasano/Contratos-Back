package com.example.contrato.controllers;

import com.example.contrato.model.ItemContrato;
import com.example.contrato.repositories.ItemContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("item")
public class ItemContratoController {

    private final ItemContratoRepository itemContratoRepository;

    @PostMapping
    public ResponseEntity<ItemContrato> create(@RequestBody ItemContrato itemContratoform){
        ItemContrato itemContrato = itemContratoRepository.save(itemContratoform);
        return ResponseEntity.ok(itemContrato);
    }

    @GetMapping
    public ResponseEntity<List<ItemContrato>> list(){
        List<ItemContrato> itens = itemContratoRepository.findAll();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Optional<ItemContrato> itemOpt = itemContratoRepository.findById(id);

        if(itemOpt.isPresent()){
            return ResponseEntity.ok(itemOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody ItemContrato item, @PathVariable Long id){
        item.setId(id);
        itemContratoRepository.saveAndFlush(item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        itemContratoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}