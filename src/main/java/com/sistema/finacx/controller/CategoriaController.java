package com.sistema.finacx.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistema.finacx.model.*;
import com.sistema.finacx.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**Seria o mesmo qa PASTA CONTROLLER */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    @CachePut(value = "listapessoa")
    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
        Categoria auxCategoria = categoriaRepository.save(categoria);

        //URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(auxCategoria.getId()).toUri();

        //esponse.addHeader("Location", uri.toASCIIString());

        return new ResponseEntity<Categoria>(auxCategoria, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> buscarCategoria(@PathVariable Long id) {

        var categoria =  categoriaRepository.findById(id);
        
        return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletarCategoria(@PathVariable Long id) {

        categoriaRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
        
    }
    
}
