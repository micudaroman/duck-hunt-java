package sk.stuba.fei.uim.oop.strelenekacice;


import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.hraciepole.Rybnik;
import sk.stuba.fei.uim.oop.kacica.Kacica;
import sk.stuba.fei.uim.oop.karta.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StreleneKacice {
    final int MIN_POCET_HRACOV = 2;
    final int MAX_POCET_HRACOV = 6;
    private List<Hrac> hraci;
    private List<Karta> akcneKarty;
    private List<Kacica> kacice;
    private int sucasnyHrac;
    private Rybnik rybnik;

    public StreleneKacice(){
        int pocetHracov;
        this.sucasnyHrac = 0;
        this.hraci = new ArrayList<>();
        this.kacice = new ArrayList<>();
        this.akcneKarty = new ArrayList<>();
        System.out.println("Vitaj v hre Strelene Kacice!!!");
        do{
            pocetHracov= ZKlavesnice.readInt("Zadaj pocet hracov (2 az 6): ");
        }while(!(pocetHracov>=MIN_POCET_HRACOV && pocetHracov<=MAX_POCET_HRACOV));

        for (int i = 0; i<pocetHracov;i++){
            String meno = ZKlavesnice.readString(String.format("Zadaj meno %d. hraca: ",(i+1)));
            this.hraci.add(new Hrac(meno));
        }
        this.inicializovatHru();
        this.zacatHru();
    }

    private void inicializovatHru(){
        this.inicializovatAkcneKarty();
        this.zamiesatAkcneKarty();
        this.inicializovatKacky();
        this.zamiesatKacky();
        this.rybnik = new Rybnik(kacice);
        this.rozdatAkcneKartyHracom();

    }

    private void inicializovatAkcneKarty(){
        for (int i = 0; i < 10;i++){
            akcneKarty.add(new Zamierit());
        }
        for (int i = 0; i < 12;i++){
            akcneKarty.add(new Vystrelit());
        }
        for (int i = 0; i < 2;i++){
            akcneKarty.add(new DivokyBill());
        }
        for (int i = 0; i< 6; i++){
            akcneKarty.add(new KacaciPochod());
        }
        for (int i = 0; i<1;i++){
            akcneKarty.add(new TurboKacka());
        }
        for (int i = 0; i<2;i++){
            akcneKarty.add(new Rosambo());
        }
        for (int i = 0; i<1;i++){
            akcneKarty.add(new KacaciTanec());
        }
    }


    private void zamiesatAkcneKarty(){
        Collections.shuffle(this.akcneKarty);
    }

    private void inicializovatKacky(){
        for(int i = 0; i < hraci.size();i++){
            for(int j = 0; j <5;j++){
                Hrac vlastnikKacice = hraci.get(i);
                Kacica kacica = new Kacica(vlastnikKacice);
                kacice.add(kacica);
            }
        }
        for(int i = 0; i<5;i++){
            kacice.add(null);
        }
    }
    private void zamiesatKacky(){
        Collections.shuffle(this.kacice);
    }

    private void rozdatAkcneKartyHracom(){
        for(Hrac hrac: hraci){
            hrac.potiahnutAkcneKarty(akcneKarty);
        }
    }

    private void zacatHru(){
        System.out.println("~~~~ Zacina kartova hra Strelene Kacice ~~~~");
        for (sucasnyHrac = 0; getPocetAktivnychHracov() > 1; this.navysitPocitadlo()){
            Hrac aktivnyHrac = this.hraci.get(this.sucasnyHrac);
            if (!aktivnyHrac.jeVHre()){
                aktivnyHrac.odovzdatAkcneKartyDoBalicku(akcneKarty);
                continue;
            }
            this.rybnik.vypisatHraciePole();
            System.out.println(String.format("~~~ Hrac %s je na tahu ~~~",aktivnyHrac.getMeno()));
            aktivnyHrac.vypisatPocetZivychKacic();
            aktivnyHrac.ukazatKartyNaRuke(rybnik);
            if(aktivnyHrac.jeVRukeZiadnaZahratelnaKarta(rybnik)){
                aktivnyHrac.vyhoditKartu();
                aktivnyHrac.potiahnutAkcneKarty(akcneKarty);
                continue;
            }
            aktivnyHrac.zahratKartu(rybnik,kacice,akcneKarty);
            aktivnyHrac.potiahnutAkcneKarty(akcneKarty);


            rybnik.urobitPriletKacic(kacice);
        }
        this.rybnik.vypisatHraciePole();
        System.out.println("~~~ Hra skoncila ~~~");
        Hrac vytaz = this.zistitVytaza();
        System.out.println(String.format("!!! Vytaz je %s!!!",vytaz.getMeno()));
    }

    private Hrac zistitVytaza(){
        for(Hrac hrac: hraci){
            if(hrac.jeVHre()){
                return hrac;
            }
        }
        return null;
    }


    private void navysitPocitadlo(){
        this.sucasnyHrac++;
        this.sucasnyHrac %= this.hraci.size();
    }

    private int getPocetAktivnychHracov(){
        int pocetAktivnychHracov = 0;
        for( int i = 0; i< this.hraci.size();i++){
            Hrac hrac = this.hraci.get(i);
            if (hrac.jeVHre()){
                pocetAktivnychHracov++;
            }
        }
        return pocetAktivnychHracov;
    }




}