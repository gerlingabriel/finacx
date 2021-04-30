package com.sistema.finacx.service;

import java.security.Principal;
import java.util.List;

import com.sistema.finacx.dto.LoginFilter;
import com.sistema.finacx.model.*;
import com.sistema.finacx.repository.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 * PessoaService
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizarPessoa(Long id, Pessoa pessoa){
        
        Pessoa auxPessoa = pessoaRepository.findById(id).get();

        /** não foi necessário criar essa regra, pois na chamada acima quando não acha a PESSOA
         * já é chamado o erro da "NoSuchElementException"
         * 
        if(auxPessoa == null ){
            throw new EmptyResultDataAccessException(1); // Já tenho tratamento desse Exception
        }*/

        /**Esse metodo faz que na terceira coluna será ignorada (exemplo pegou tudo de PESSAO para AUXPESSOA menos "ID") */
        BeanUtils.copyProperties(pessoa, auxPessoa, "id");

        return pessoaRepository.save(auxPessoa);
    }

    public void deletarPessoa(Long id){
        pessoaRepository.deleteById(id);
    }

    public Pessoa addPessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listaPessoa(){
        return pessoaRepository.findAll();
    }

    public Pessoa atualizarProAtivo(Long id, Boolean ativo) {
        Pessoa auxPessoa = pessoaRepository.findById(id).get();
        auxPessoa.setAtivo(ativo);        
        return pessoaRepository.save(auxPessoa);
    }

    public Pessoa verificarPessoaAtiva(LoginFilter session) {
        /** Metodo para verificar se existe pessoa autorizada */
        Pessoa pessoa = pessoaRepository.findByLoginAndSenha(session.getLogin(), session.getSenha());
        if (pessoa.getAtivo() == false) {
            throw new EmptyResultDataAccessException(1); // Já tenho tratamento desse Exception
        }/** Fim do metodo ************************************** */

        return pessoa;
    }

    public void verificarPessoaAtiva2(Principal principal) {

       String auxPessoa = principal.getName();
       Pessoa pessoa = pessoaRepository.findByLogin(auxPessoa);
       
        if (pessoa.getAtivo() == false) {
            throw new EmptyResultDataAccessException(1); // Já tenho tratamento desse Exception
        }/** Fim do metodo ************************************** */


    }

    public List<Pessoa> listPessoasRole(){

        List<Pessoa> lista = pessoaRepository.findAll();

        for (Pessoa pessoa : lista) {
            
            for (Role cargo : pessoa.getRoles()) {
                System.out.println(cargo.getNomeRoles());
            }
            
        }
        return lista;
    }

    
}