package com.sistema.finacx.repository;

import com.sistema.finacx.model.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

    Pessoa findByLoginAndSenha(String login, String senha);

    Pessoa findByLogin(String auxPessoa);
    
}
