package com.example.contrato.controllers;

import com.example.contrato.model.*;
import com.example.contrato.model.dtos.ContratoDTO;
import com.example.contrato.model.dtos.ItemContratoDTO;
import com.example.contrato.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("contrato")
public class ContratoController {

    private final ContratoRepository contratoRepository;
    private final EnderecoRepository enderecoRepository;
    private final EmpresaContratadaRepository empresaContratadaRepository;
    private final RepEmpContratadaRepository repEmpContratadaRepository;
    private final CidadeRepository cidadeRepository;
    private final LogradouroRepository logradouroRepository;
    private final BairroRepository bairroRepository;
    private final GesEmpContratanteRepository gesEmpContratanteRepository;
    private final ItemContratoRepository itemContratoRepository;

    @PostMapping
    public ResponseEntity<Contrato> create(@RequestBody ContratoDTO contratoform){
        Optional<Logradouro> optLogradouro = logradouroRepository.findById(contratoform.getIdLogradouro());
        Optional<Cidade> optCidade  = cidadeRepository.findById(contratoform.getIdCidade());
        Optional<Bairro> optBairro = bairroRepository.findById(contratoform.getIdBairro());

        if (optLogradouro.isEmpty() || optCidade.isEmpty() || optBairro.isEmpty())
            return ResponseEntity.notFound().build();

        Optional<Endereco> optEndereco = enderecoRepository.findByLogradouroAndBairroAndCidade(
                optLogradouro.get(),
                optBairro.get(),
                optCidade.get());

        Endereco endereco;
        if (optEndereco.isPresent()) {
            endereco = optEndereco.get();
        } else {
            endereco = new Endereco();
            endereco.setLogradouro(optLogradouro.get());
            endereco.setCidade(optCidade.get());
            endereco.setBairro(optBairro.get());
        }

        Optional<EmpresaContratada> empresaContratadaOpt = empresaContratadaRepository.findById(contratoform.getIdEmpresa());
        Optional<RepEmpContratada> repEmpContratadaOpt = repEmpContratadaRepository.findById(contratoform.getIdRepresentante());
        Optional<GesEmpContratante> gesEmpContratanteOpt = gesEmpContratanteRepository.findById(contratoform.getIdGestor());

        /*if(!empresaContratadaOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }
        if(!repEmpContratadaOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }
        if(!gesEmpContratanteOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }*/

        Contrato contrato = new Contrato();

        contrato.setObjeto(contratoform.getObjeto());
        contrato.setDataInicio(contratoform.getDataInicio());
        contrato.setDataFim((contratoform.getDataFim()));
        contrato.setNumEndereco(contratoform.getNumEndereco());
        contrato.setComplementoEnd(contratoform.getComplementoEnd());
        contrato.setEndereco(endereco);
        contrato.setEmpresaContratada(empresaContratadaOpt.get());
        contrato.setRepEmpContratada(repEmpContratadaOpt.get());
        contrato.setGesEmpContratante(gesEmpContratanteOpt.get());
        contrato.setItensContratados(new ArrayList<>());

        float total = 0;
        for (ItemContratoDTO itemDto : contratoform.getItensContrato()) {
            ItemContrato item = new ItemContrato();
            item.setDescricao(itemDto.getDescricao());
            item.setValorItemContrato(itemDto.getValorItemContrato());
            item.setDataPagamento(itemDto.getDataPagamento());
            item.setDataVencimento(itemDto.getDataVencimento());
            item.setPago(itemDto.isPago());
            item.setValorPago(itemDto.getValorPago());
            item.setAprovadoPagar(itemDto.isAprovadoPagar());
            contrato.getItensContratados().add(item);

            total += item.getValorItemContrato();
        }
        contrato.setValorContratado(total);
        contrato = contratoRepository.save(contrato);
        return ResponseEntity.ok(contrato);
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> list(){
        List<Contrato> contratos = contratoRepository.findAll();
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Optional<Contrato> contratoOpt = contratoRepository.findById(id);

        if(contratoOpt.isPresent()){
            return ResponseEntity.ok(contratoOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Contrato contrato, @PathVariable Long id){
        contrato.setId(id);
        contratoRepository.saveAndFlush(contrato);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        contratoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}