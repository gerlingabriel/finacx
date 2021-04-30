package com.sistema.finacx.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.sistema.finacx.dto.LancamentoFilter;
import com.sistema.finacx.exceptionhandle.*;
import com.sistema.finacx.model.*;
import com.sistema.finacx.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Lancamento> listaLancamento() {

        return lancamentoRepository.findAll();
    }

    public Lancamento lancamento(Long id) {
         // forma diferente de fazer exceçõs que para mim será muito mais simples
        return lancamentoRepository.findById(id).orElseThrow(() -> new BadRequestExpection("Lancamento não encontrado!"));
    }

    public Lancamento addLancamento(Lancamento lancamento) {

        
        Pessoa auxPessoa = pessoaRepository.findById(lancamento.getPessoa().getId()).get();

        if (auxPessoa.getAtivo() == false) {
            throw new ExceptionPessoaInvalida();
        }

        return lancamentoRepository.save(lancamento);
    }

    public Page<Lancamento> listaLancamentoPirdata(LancamentoFilter lancamentoFilter) {

        Page<Lancamento> list = null;

        PageRequest pageRequest = PageRequest.of(lancamentoFilter.getPage(), 5, Sort.by("id"));

        // Caso não tenha descricao
        if (lancamentoFilter.getDescricao() == null) {

            if (lancamentoFilter.getVencimentoAte() == null) { // Se datavencimentoAte não for preenchida vou
                                                                   // colocar dia de hoje

                list = metodoParaPegarDataAtual(lancamentoFilter, pageRequest);

            } else { // Se não a data ATE foi preenchida
                //list = lancamentoRepository.findByDescricaoData(lancamentoFilter.getDataVendimentoDe(),
                //       lancamentoFilter.getDataVencimentoAte(), pageRequest);
                list = lancamentoRepository.findByDataVencimentoBetween(lancamentoFilter.getVendicmentoDe(), lancamentoFilter.getVencimentoAte(), pageRequest);

            }
            // Caso tenhoa descricao
            // Se não colocou data De então não bsuca por data
        } else if (lancamentoFilter.getVendicmentoDe() == null) {

            list = lancamentoRepository.findByDescricao(lancamentoFilter.getDescricao(), pageRequest);

        } else { // tem data
            if (lancamentoFilter.getVencimentoAte() == null) { // Se datavencimentoAte não for preenchida vou
                                                                   // colocar dia de hoje

                list = metodoParaPegarDataAtual(lancamentoFilter, pageRequest);

            } else { // Todos campos foram colocado
                list = lancamentoRepository.findByDataVencimentoBetweenAndDescricaoContaining(lancamentoFilter.getVendicmentoDe(),
                        lancamentoFilter.getVencimentoAte(), lancamentoFilter.getDescricao(), pageRequest);
            }

        }

        return list;
    }

    public void deletarLancamento(Long id) {
        lancamentoRepository.deleteById(id);
    }

    // Metodo de apoio
    /** Metodo para pegar data atual e colocar no vencimentoATE */
    private Page<Lancamento> metodoParaPegarDataAtual(LancamentoFilter lancamentoFilter, Pageable pageRequest) {

        Page<Lancamento> list;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDateTime now = LocalDateTime.now();

        LocalDate dateTime = LocalDate.parse(dtf.format(now), dtf);

        lancamentoFilter.setVencimentoAte(dateTime);

        System.out.println(lancamentoFilter);

        if (lancamentoFilter.getDescricao() == null) {

            list = lancamentoRepository.findByDataVencimentoBetween(lancamentoFilter.getVendicmentoDe(),
                lancamentoFilter.getVencimentoAte(), pageRequest);
            
        } else {

            list = lancamentoRepository.findByDataVencimentoBetweenAndDescricaoContaining(lancamentoFilter.getVendicmentoDe(),
                lancamentoFilter.getVencimentoAte(), lancamentoFilter.getDescricao(), pageRequest);

        }
        
        return list;
    }

}
