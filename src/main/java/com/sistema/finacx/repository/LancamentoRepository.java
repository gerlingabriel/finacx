package com.sistema.finacx.repository;

import java.time.LocalDate;

import com.sistema.finacx.model.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

    //@Query(nativeQuery = true, value = " select * from lancamento where data_vencimento BETWEEN ?1 and ?2 ")
    //Page<Lancamento> findByDescricaoData(LocalDate de, LocalDate ate, Pageable page);

    //@Query(nativeQuery = true, value = " select * from lancamento where data_vencimento BETWEEN ?1 and ?2 ")
   //Page<Lancamento> findByDescricaoValor(BigDecimal de, BigDecimal ate, Pageable page);

    //@Query(nativeQuery = true, value = "select * from lancamento where data_vencimento BETWEEN ?1 and ?2 and descricao like %?3% ")
    //Page<Lancamento> findByDescricaoDataEDescricao(LocalDate de, LocalDate ate, String descricao, Pageable page);

    @Query(nativeQuery = true, value = "select * from lancamento where descricao like %?1% ")
    Page<Lancamento> findByDescricao(String descricao, Pageable page);

    Page<Lancamento> findByDataVencimentoBetween(LocalDate de, LocalDate ate, Pageable page);

    Page<Lancamento> findByDataVencimentoBetweenAndDescricaoContaining(LocalDate de, LocalDate ate, String descricao, Pageable page);

    
}
