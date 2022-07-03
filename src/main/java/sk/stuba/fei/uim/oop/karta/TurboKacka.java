package sk.stuba.fei.uim.oop.karta;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.hraciepole.Rybnik;
import sk.stuba.fei.uim.oop.kacica.Kacica;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.List;

public class TurboKacka extends Karta{
    @Override
    public void activate(Hrac hrac, Rybnik rybnik, List<Kacica> kacice) {
        int poziciaKaciceOdletu;
        while(true){
            poziciaKaciceOdletu = ZKlavesnice.readInt("Zadaj poziciu kacice, ktory chces premiestnit:");
            poziciaKaciceOdletu--;
            if((poziciaKaciceOdletu+1)>=1 && (poziciaKaciceOdletu)<=6) {
                if (rybnik.jeVPoziciiKacica(poziciaKaciceOdletu)) {
                    break;
                }
            }
            System.out.println("Neplatna pozicia odletu kacice!!!");
        }
        rybnik.predbehnutKacice(poziciaKaciceOdletu);

    }

    public TurboKacka() {
    }

    @Override
    public boolean jeZahratelna(Rybnik rybnik) {
        if(rybnik.jeVRybnikuNejakaKacica()){
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Turbo Kacka";
    }
}