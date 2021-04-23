package com.qone.myapplication2;

public class ModelOceny {
    private String name;
    private int mark;

    public ModelOceny(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getNazwa() {
        return name;
    }

    public void setNazwa(String nazwa) {
        this.name = nazwa;
    }

    public int getOcena() {
        return mark;
    }

    public void setOcena(int ocena) {
        this.mark = ocena;
    }
}
