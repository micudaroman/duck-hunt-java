package sk.stuba.fei.uim.oop.karta;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.hraciepole.Rybnik;
import sk.stuba.fei.uim.oop.kacica.Kacica;

import java.util.ArrayList;
import java.util.List;

public class KacaciPochod extends Karta{
    @Override
    public void activate(Hrac hrac, Rybnik rybnik, List<Kacica> kacice) {
        rybnik.pochodovatKacice(kacice);
    }

    public KacaciPochod() {
    }

    @Override
    public boolean jeZahratelna(Rybnik rybnik) {
        return true;
    }


    @Override
    public String toString() {
        return "Kacaci Pochod";
    }
}