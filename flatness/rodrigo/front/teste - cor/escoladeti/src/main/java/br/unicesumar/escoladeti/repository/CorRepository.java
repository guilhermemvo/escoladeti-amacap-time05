/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unicesumar.escoladeti.repository;

import br.unicesumar.escoladeti.entity.Cor;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author RodrigoCezar
 */
public interface CorRepository extends JpaRepository<Cor, Long> {

    List<Cor> findByNomeContainingOrderByNomeAsc(String value);

    Page<Cor> findByNomeContainingOrderByNomeAsc(String nome, Pageable sortedPage);

}
