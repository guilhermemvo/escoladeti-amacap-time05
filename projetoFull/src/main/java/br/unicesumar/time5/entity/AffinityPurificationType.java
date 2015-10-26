package br.unicesumar.time5.entity;

import java.io.Serializable;

/**
 *
 * @author PauloHenrique
 */
public class AffinityPurificationType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public AffinityPurificationType() {
    }

    public String toString() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
