package sk.stuba.fei.uim.oop.hrac;

import sk.stuba.fei.uim.oop.hraciepole.Rybnik;
import sk.stuba.fei.uim.oop.kacica.Kacica;
import sk.stuba.fei.uim.oop.karta.Karta;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.List;

public class Hrac {
    private String meno;
    private List<Karta> ruka;
    private int pocetZivychKacic;

    public Hrac() {
    }

    public Hrac(String meno) {
        this.meno = meno;
        this.ruka = new ArrayList<>();
        this.pocetZivychKacic = 5;
    }

    public void potiahnutAkcneKarty(List<Karta> akcneKarty){
        while(ruka.size()<3){
            ruka.add(akcneKarty.get(0));
            akcneKarty.remove(0);
        }
    }

    public boolean jeVHre(){
        return pocetZivychKacic>=1;
    }

    public void ukazatKartyNaRuke(Rybnik rybnik){
        System.out.println("Na ruke mas karty:");
        for (int i = 0; i< ruka.size();i++){
            Karta karta = ruka.get(i);
            System.out.print(String.format("%d. %s ",(i+1),karta));
            boolean zahratelna = karta.jeZahratelna(rybnik);
            karta.setZahratelna(zahratelna);
            if(zahratelna)
                System.out.println("(Zahratelna)");
            else
                System.out.println("(Nezahratelna)");

        }
    }


    public boolean jeVRukeZiadnaZahratelnaKarta(Rybnik rybnik){
        for(Karta karta: ruka){
            if(karta.jeZahratelna(rybnik))
                return false;
        }
        return true;
    }

    public void vyhoditKartu(){
        int cisloKarty;
        while(true){
            cisloKarty = ZKlavesnice.readInt("Zadaj cislo karty na vyhodenie:");
            cisloKarty--;
            if((cisloKarty+1) >=1 && (cisloKarty+1)<=3){
                break;
            }else{
                System.out.println("Neplatny vstup!!!");
            }
        }

        ruka.remove(cisloKarty);
    }
    public void zahratKartu(Rybnik rybnik,List<Kacica> kacice,List<Karta> akcneKarty){
        int cisloAkcnejKarty;
        Karta karta;
        while(true){
            cisloAkcnejKarty = ZKlavesnice.readInt("Zadaj cislo akcnej karty (1 az 3): ");
            cisloAkcnejKarty--;
            if(((cisloAkcnejKarty+1)>=1) && ((cisloAkcnejKarty+1)<=3)) {
            karta = ruka.get(cisloAkcnejKarty);
                if (karta.jeZahratelna(rybnik)) {
                    break;
                }
            }else{
                System.out.println("Neplatny vstup!!!");
                continue;
            }
            System.out.println("Tuto akcnu kartu nemozes zahrat!");
        }
        karta.activate(this,rybnik,kacice);
        this.posunutAkcnuKartuDoBalicku(cisloAkcnejKarty,akcneKarty);
    }

    private void posunutAkcnuKartuDoBalicku(int cisloAkcnejKarty, List<Karta> akcneKarty){
        Karta karta = ruka.get(cisloAkcnejKarty);
        akcneKarty.add(karta);
        ruka.remove(cisloAkcnejKarty);

    }

    public void zastrelitKacicu(Rybnik rybnik, int poziciaVRybniku){
        if(rybnik.jeVPoziciiKacica(poziciaVRybniku)){
            rybnik.potopitKacicu(poziciaVRybniku);
        }
        rybnik.odstranitZameriavac(poziciaVRybniku);
    }

    public void vypisatPocetZivychKacic(){
        System.out.println(String.format("Pocet zivych kacic je %d.",pocetZivychKacic));
    }

    public void odobratZivot(){
        this.pocetZivychKacic--;
    }
    public String getMeno() {
        return meno;
    }

    public void odovzdatAkcneKartyDoBalicku(List<Karta> akcneKarty)
    {
        for(Karta karta: ruka){
            akcneKarty.add(karta);
        }
        ruka.clear();
    }
}
