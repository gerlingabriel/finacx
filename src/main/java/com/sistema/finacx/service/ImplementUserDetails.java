package com.sistema.finacx.service;

import com.sistema.finacx.model.Pessoa;
import com.sistema.finacx.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImplementUserDetails implements UserDetailsService{

    @Autowired
    private PessoaRepository  pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Pessoa pessoa = pessoaRepository.findByLogin(username);

        if(pessoa == null){
            throw new UsernameNotFoundException(String.format("Usuario não encontrado", username));
        }
        
        return new User(pessoa.getLogin(), pessoa.getSenha(), pessoa.getAuthorities());
    }
    
}
