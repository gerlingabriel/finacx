package com.sistema.finacx.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sistema.finacx.dto.LoginFilter;
import com.sistema.finacx.model.Pessoa;
import com.sistema.finacx.service.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


//@RestController
//@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    private ResponseEntity<Pessoa> login(@RequestBody LoginFilter loginFilter, HttpServletResponse response) {

        Pessoa pessoa = pessoaService.verificarPessoaAtiva(loginFilter);

        String token = JWT.create().withClaim("idLogado", pessoa.getId()).sign(Algorithm.HMAC256("teste*teste"));

        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/"); // está em todas requisições
        cookie.setHttpOnly(true); // Não deixa exposto ao JavaScript
        cookie.setMaxAge(60 * 30); // 30 min - Vi algum sobre número negativo é valido até fechr navegador
        response.addCookie(cookie);

        return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
    }

}