package sk.stuba.fei.uim.oop.karta;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.hraciepole.Rybnik;
import sk.stuba.fei.uim.oop.kacica.Kacica;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;

public class DivokyBill extends Karta{
    @Override
    public void activate(Hrac hrac, Rybnik rybnik, List<Kacica> kacice) {
        int poziciaVRybniku;
        while(true) {
            poziciaVRybniku = ZKlavesnice.readInt("Zadaj poziciu, kde chces vystrelit: ");
            poziciaVRybniku--;
            if((poziciaVRybniku+1)>=1 && (poziciaVRybniku+1)<=6){
                break;
            }else{
                System.out.println("Neplatny vstup!!!");
            }
        }
        Hrac vlastnikZastrelenejKacice = rybnik.getVlastnikZastrelenejKacice(poziciaVRybniku);
        hrac.zastrelitKacicu(rybnik,poziciaVRybniku);
        if(vlastnikZastrelenejKacice != null){
            vlastnikZastrelenejKacice.odobratZivot();
        }
    }

    public DivokyBill() {
    }

    @Override
    public boolean jeZahratelna(Rybnik rybnik) {
        return true;
    }


    @Override
    public String toString() {
        return "Divoky Bill";
    }
}