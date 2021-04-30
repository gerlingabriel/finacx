package com.sistema.finacx.controller;

import java.util.List;

import javax.validation.Valid;

import com.sistema.finacx.model.*;
import com.sistema.finacx.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@Valid @RequestBody Pessoa pessoa){
        return new ResponseEntity<Pessoa>(pessoaService.addPessoa(pessoa), HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<Pessoa>> listaDePessoas() {
        //pessoaService.verificarPessoaAtiva2(principal);
        return new ResponseEntity<List<Pessoa>>(pessoaService.listaPessoa(), HttpStatus.OK);
    } 

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletarPessoa(@PathVariable Long id) {

        pessoaService.deletarPessoa(id);

        return new ResponseEntity<>("OK", HttpStatus.OK);
        
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa){
        return new ResponseEntity<Pessoa>(pessoaService.atualizarPessoa(id, pessoa), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/ativ")
    public ResponseEntity<Pessoa> atualizarPropAtivo(@PathVariable Long id, @RequestBody Boolean ativo){
        return new ResponseEntity<Pessoa>(pessoaService.atualizarProAtivo(id, ativo), HttpStatus.OK);
    }

    @GetMapping(value = "teste/teste/")
    public List<Pessoa> teste(){
        List<Pessoa> pessoa = pessoaService.listPessoasRole();
        return pessoa;
    }

}
