package sk.stuba.fei.uim.oop.karta;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.hraciepole.Rybnik;
import sk.stuba.fei.uim.oop.kacica.Kacica;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;

public class Zamierit extends Karta{
    @Override
    public void activate(Hrac hrac, Rybnik rybnik, List<Kacica> kacice) {
        int pozicia;
        while(true) {
            pozicia = ZKlavesnice.readInt("Na ktory poziciu chces zamierit: ");
            pozicia--;
            if((pozicia+1)>=1 && (pozicia)<=6){
                if(!rybnik.jeVPoziciiZameriavac(pozicia)){
                    break;
                }
            }else{
                System.out.println("Neplatny vstup!!!");
                continue;
            }
            System.out.println(String.format("Na pozicii %d. je zameriavac",pozicia+1));
        }
        rybnik.nastavitZameriavac(pozicia);
    }

    public Zamierit() {
    }

    @Override
    public boolean jeZahratelna(Rybnik rybnik) {
        if(rybnik.jeRybnikPlnyZameriavacov()){
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Zamierit";
    }
}