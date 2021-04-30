package com.sistema.finacx.controller;


import java.util.List;

import javax.validation.Valid;

import com.sistema.finacx.dto.LancamentoFilter;
import com.sistema.finacx.model.*;
import com.sistema.finacx.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Lancamento> lanchamento(@PathVariable Long id){
        return new ResponseEntity<Lancamento>(lancamentoService.lancamento(id), HttpStatus.OK);
    }   
    
    @GetMapping(value ="/list")
    public ResponseEntity<List<Lancamento>> listaLancamento(){
        return new ResponseEntity<List<Lancamento>>(lancamentoService.listaLancamento(), HttpStatus.OK);
    }

    /**Metodo de busca com nome e paginacao ***************************************************************************************/
    @PostMapping(value = "/")
    public ResponseEntity<Page<Lancamento>> pesquisar(@Valid @RequestBody LancamentoFilter lancamentoFilter){
        return new ResponseEntity<Page<Lancamento>>(lancamentoService.listaLancamentoPirdata(lancamentoFilter), HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Sucesso na busca"),
        @ApiResponse(responseCode = "401", description = "Usuário não tem acesso")
    })
    public ResponseEntity<Lancamento> addLancamento(@Valid @RequestBody Lancamento lancamento) {        
        return new ResponseEntity<Lancamento>(lancamentoService.addLancamento(lancamento), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletarLancamento(@Valid @PathVariable Long id) {
        lancamentoService.deletarLancamento(id);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
