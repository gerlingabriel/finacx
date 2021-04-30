package com.sistema.finacx.dto;

import java.io.Serializable;
import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistema.finacx.model.Lancamento;

import io.swagger.v3.oas.annotations.media.Schema;

public class LancamentoFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    private String descricao;

    @JsonFormat(pattern="dd-MM-yyyy") 
    @Schema(description = "Data de inicio da pesquisa")
    private LocalDate vendicmentoDe;

    @JsonFormat(pattern="dd-MM-yyyy") 
    @Schema(description = "Verificar tpé data que deseja, não colocano nada será dado o dia de acesso")
    private LocalDate vencimentoAte;

    private int page;

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LocalDate getVendicmentoDe() {
        return vendicmentoDe;
    }
    public void setVendicmentoDe(LocalDate vendicmentoDe) {
        this.vendicmentoDe = vendicmentoDe;
    }
    public LocalDate getVencimentoAte() {
        return vencimentoAte;
    }
    public void setVencimentoAte(LocalDate vencimentoAte) {
        this.vencimentoAte = vencimentoAte;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public Lancamento converterLancamentoFilter(LancamentoFilter lanc){

        Lancamento lancamento = new Lancamento();
        lancamento.setDescricao(lanc.getDescricao());
        lancamento.setDataVencimento(lanc.getVendicmentoDe());

        if (lanc.getVencimentoAte() != null) {
            lancamento.setDataPagamento(lanc.getVencimentoAte());
        }
        lancamento.setPage(lanc.getPage());

        return lancamento;
    }
    
    
}
