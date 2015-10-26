/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author felipesabadinifacina
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{}
