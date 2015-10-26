/*
package br.unicesumar.time5.entity;

import static br.unicesumar.time5.util.nanotime.NanotimeUtil.nanotime;

public class EntidadeDummy extends SuperEntidade implements Cloneable {

    private String value;

    public EntidadeDummy(String value) {
        super();
        this.value = value;
    }

    public String value() {
        return value;
    }

    public EntidadeDummy copy() {
        EntidadeDummy copy = new EntidadeDummy(value);

        copy.nanotime = nanotime;
        copy.id = id;

        return copy;
    }

    public void updateNanotime() {
        nanotime = nanotime();
    }

    @Override
    public String label() {
        return value();
    }

}
*/