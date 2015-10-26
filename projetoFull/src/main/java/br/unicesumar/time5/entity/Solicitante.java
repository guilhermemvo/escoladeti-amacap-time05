package br.unicesumar.time5.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_solicitante")
@PrimaryKeyJoinColumn(name = "id")
public class Solicitante extends PessoaFisica{
 
    
}