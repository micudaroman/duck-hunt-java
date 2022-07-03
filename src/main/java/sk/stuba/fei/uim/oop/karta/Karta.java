package sk.stuba.fei.uim.oop.karta;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.hraciepole.Rybnik;
import sk.stuba.fei.uim.oop.kacica.Kacica;

import java.util.List;

public abstract class Karta {
    protected boolean zahratelna;
    public Karta() {
    }
    public abstract void activate(Hrac hrac, Rybnik rybnik, List<Kacica> kacice);

    public Karta(boolean zahratelna) {
        this.zahratelna = zahratelna;
    }

    public abstract boolean jeZahratelna(Rybnik rybnik);

    public void setZahratelna(boolean zahratelna) {
        this.zahratelna = zahratelna;
    }

}