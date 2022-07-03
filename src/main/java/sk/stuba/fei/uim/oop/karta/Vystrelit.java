package sk.stuba.fei.uim.oop.karta;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.hraciepole.Rybnik;
import sk.stuba.fei.uim.oop.kacica.Kacica;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;

public class Vystrelit extends Karta{
    @Override
    public void activate(Hrac hrac, Rybnik rybnik, List<Kacica> kacice) {
        int poziciaVRybniku;
        while(true) {
            poziciaVRybniku = ZKlavesnice.readInt("Zadaj poziciu, kde chces vystrelit: ");
            poziciaVRybniku--;
            if ((poziciaVRybniku+1)>=1 && (poziciaVRybniku+1)<=6) {
                if (rybnik.jeVPoziciiZameriavac(poziciaVRybniku)) {
                    break;
                }
            }else{
                System.out.println("Neplatny vstup!!!");
                continue;
            }
            System.out.println("Musis zadat poziciu, kte mas zameriavac!!!");
        }
        Hrac vlastnikZastrelenejKacice = rybnik.getVlastnikZastrelenejKacice(poziciaVRybniku);
        hrac.zastrelitKacicu(rybnik,poziciaVRybniku);
        if(vlastnikZastrelenejKacice != null){
            vlastnikZastrelenejKacice.odobratZivot();
        }


    }

    public Vystrelit() {
    }

    @Override
    public boolean jeZahratelna(Rybnik rybnik) {
        if(rybnik.jeVRybnikuNejakyZameriavac()){
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Vystrelit";
    }
}
