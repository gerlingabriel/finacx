package com.sistema.finacx.exceptionhandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SistemafincExceptionHandle extends ResponseEntityExceptionHandler{

    @Autowired
    private MessageSource msg;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        

                String mensagemUsuario = msg.getMessage("mensagem.invalide", null, LocaleContextHolder.getLocale());
                String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();

                List<Erro> lista = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));

        return handleExceptionInternal(ex, lista, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

                List<Erro> lista = criarListaErros(ex.getBindingResult());

        return handleExceptionInternal(ex, lista, headers, status, request);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException (EmptyResultDataAccessException ex,  WebRequest request){

        String mensagemUsuario = msg.getMessage("mensagem.nao-encontrada", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();

        List<Erro> lista = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));

        return handleExceptionInternal(ex, lista, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
        
    }

    @ExceptionHandler({ NoSuchElementException.class })
    public ResponseEntity<Object> handleNoSuchElementException (NoSuchElementException ex,  WebRequest request){

        String mensagemUsuario = msg.getMessage("mensagem.nao-encontrada", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();

        List<Erro> lista = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));

        return handleExceptionInternal(ex, lista, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
        
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException (DataIntegrityViolationException ex,  WebRequest request){

        String mensagemUsuario = msg.getMessage("mensagem.null", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);

        List<Erro> lista = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));

        return handleExceptionInternal(ex, lista, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        
    }

    @ExceptionHandler({ ExceptionPessoaInvalida.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException (ExceptionPessoaInvalida ex){

        String mensagemUsuario = msg.getMessage("mensagem.inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);

        List<Erro> lista = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));

        return ResponseEntity.badRequest().body(lista);
        
    }

    /*@ExceptionHandler({ CredentiaisInvalidException.class })
    public ResponseEntity<Object> handleCredentiaisInvalidException (CredentiaisInvalidException ex){

        String mensagemUsuario = msg.getMessage("mensagem.login", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);

        List<Erro> lista = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));

        return ResponseEntity.badRequest().body(lista);
        
    }*/


    // Lista para pegar os argumentos invalido, pode ser mais de um 
    private List<Erro> criarListaErros(BindingResult bindingResult) {
        List<Erro> lista = new ArrayList<>();

        List<FieldError> errors = bindingResult.getFieldErrors();

        for(FieldError fieldError : errors){

            String mensagemUsuario = msg.getMessage(fieldError, null);
            String mensagemDesenvolvedor= fieldError.toString();
            lista.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));

        }
        return lista;
    }

    public static class Erro {

        private String mensagemUsuario;
        private String mensagemDesenvolvedor;

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }
        public String getMensagemUsuario() {
            return mensagemUsuario;
        }
        public void setMensagemUsuario(String mensagemUsuario) {
            this.mensagemUsuario = mensagemUsuario;
        }
        public String getMensagemDesenvolvedor() {
            return mensagemDesenvolvedor;
        }
        public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }


    }
    
}
