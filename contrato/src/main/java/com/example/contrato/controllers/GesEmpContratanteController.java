package com.example.contrato.controllers;

import com.example.contrato.model.GesEmpContratante;
import com.example.contrato.repositories.GesEmpContratanteRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("gestor")
public class GesEmpContratanteController {

    private final GesEmpContratanteRepository gesEmpContratanteRepository;

    @PostMapping
    public ResponseEntity<GesEmpContratante> create(@RequestBody GesEmpContratante gestorform){
        GesEmpContratante gestor = gesEmpContratanteRepository.save(gestorform);
        return ResponseEntity.ok(gestor);
    }

    @GetMapping
    public ResponseEntity<List<GesEmpContratante>> list(){
        List<GesEmpContratante> gestores = gesEmpContratanteRepository.findAll();
        return ResponseEntity.ok(gestores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Optional<GesEmpContratante> gestorOpt = gesEmpContratanteRepository.findById(id);

        if(gestorOpt.isPresent()){
            return ResponseEntity.ok(gestorOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody GesEmpContratante gestor, @PathVariable Long id){
        gestor.setId(id);
        gesEmpContratanteRepository.saveAndFlush((gestor));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        gesEmpContratanteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}