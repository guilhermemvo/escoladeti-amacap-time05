/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Outros;
import java.io.Serializable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author johnLima
 */
public interface OutrosRepository extends JpaRepository<Outros, Serializable>{
        Page<Outros> findByNomeLike(Pageable pageAble, String nome);

}
