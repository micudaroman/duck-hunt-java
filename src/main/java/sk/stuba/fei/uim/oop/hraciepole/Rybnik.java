package sk.stuba.fei.uim.oop.hraciepole;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.kacica.Kacica;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rybnik {
    private final int VELKOST_HRACIEHO_POLA=6;
    private List<Kacica> rybnik;
    private List<Boolean> oblaky;

    public Rybnik() {
    }

    public Rybnik(List<Kacica> kacice) {
        this.rybnik = new ArrayList<>();
        urobitPriletKacic(kacice);
        this.oblaky = new ArrayList<>();
        urobitPriletOblakov();
    }

    public void vypisatHraciePole() {
        System.out.println("~~~~~~~~ Rybnik ~~~~~~~~");
        for (int pozicia = 0; pozicia < VELKOST_HRACIEHO_POLA; pozicia++) {
            if(pozicia == 0){
                System.out.print(String.format("%d. /|\\",pozicia+1));
            }else{
                System.out.print(String.format("%d.  | ",pozicia+1));
            }
            if(jeVPoziciiZameriavac(pozicia)){
                System.out.print(" Zameriavac ");
            }else{
                System.out.print("    Oblak   ");
            }
            Kacica kacica = rybnik.get(pozicia);
            if(this.jeTuVoda(kacica)){
                System.out.println("Voda");
            }else{
                Hrac vlastnikKacice = kacica.getVlastnikKacice();
                System.out.println(String.format("Kacica hraca %s", vlastnikKacice.getMeno()));
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public boolean jeTuVoda(Kacica kacica){
        return kacica == null;
    }
    public boolean jeVPoziciiZameriavac(int poziciaVRybniku){
        return !oblaky.get(poziciaVRybniku);
    }

    public void urobitPriletKacic(List<Kacica> kacice){
        while(rybnik.size()<VELKOST_HRACIEHO_POLA){
            rybnik.add(kacice.get(0));
            kacice.remove(0);
        }
    }

    private void urobitPriletOblakov(){
        for(int i = 0;i<VELKOST_HRACIEHO_POLA;i++){
            this.oblaky.add(true);
        }
    }

    public boolean jeRybnikPlnyZameriavacov(){
        for(boolean oblak: oblaky){
            if(oblak == true)
                return false;
        }
        return true;
    }

    public boolean jeVRybnikuNejakyZameriavac(){
        for(boolean oblak: oblaky){
            if(oblak == false)
                return true;
        }
        return false;
    }

    public boolean jeVRybnikuNejakaKacica(){
        for(Kacica kacica : rybnik){
            if(kacica != null)
                return true;
        }
        return false;
    }

    public void nastavitZameriavac(int pozicia){
        oblaky.set(pozicia,false);
    }

    public List<Kacica> getRybnik() {
        return rybnik;
    }

    public void setRybnik(List<Kacica> rybnik) {
        this.rybnik = rybnik;
    }

    public void potopitKacicu(int poziciaVRybniku){
        rybnik.remove(poziciaVRybniku);
    }
    public boolean jeVPoziciiKacica(int poziciaVRybniku){
        if(rybnik.get(poziciaVRybniku) != null) {
            return true;
        }
        return false;

    }
    public void pochodovatKacice(List<Kacica> kacice){
        Kacica kacica = rybnik.get(0);
        kacice.add(kacica);
        rybnik.remove(0);
    }
    public void urobitOdletKacic(List<Kacica> kacice){
        for(int i =0;i<VELKOST_HRACIEHO_POLA;i++){
            Kacica kacica = rybnik.get(i);
            kacice.add(kacica);
        }
        rybnik.clear();
        premiesatKacice(kacice);
    }
    private void premiesatKacice(List<Kacica> kacice){
        Collections.shuffle(kacice);
    }
    public void premiesatKaciceVRybniku(){
        Collections.shuffle(rybnik);
    }
    public void predbehnutKacice(int poziciaKaciceOdletu){
        Kacica kacica = rybnik.get(poziciaKaciceOdletu);
        rybnik.remove(poziciaKaciceOdletu);
        rybnik.add(0,kacica);

    }

    public void odstranitZameriavac(int poziciaVRybniku){
        oblaky.set(poziciaVRybniku,true);
    }

    public Hrac getVlastnikZastrelenejKacice(int poziciaVRybniku){
        Kacica zastrelenaKacica = this.rybnik.get(poziciaVRybniku);
        if(this.jeTuVoda(zastrelenaKacica)){
            return null;
        }
        return zastrelenaKacica.getVlastnikKacice();
    }
}