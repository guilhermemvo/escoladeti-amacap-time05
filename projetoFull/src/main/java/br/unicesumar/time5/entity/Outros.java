package br.unicesumar.time5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author johnLima
 */
@Entity
@Table(name = "tbcad_outros")
@PrimaryKeyJoinColumn(name = "id")
public class Outros extends Material {

    @Column(name = "type")
    private String type = "outros";

    public String getType() {
        return type;
    }

}
