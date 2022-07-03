package sk.stuba.fei.uim.oop.kacica;

import sk.stuba.fei.uim.oop.hrac.Hrac;

public class Kacica {
    private Hrac vlastnikKacice;

    public Kacica() {
    }

    public Kacica(Hrac vlastnikKacice) {
        this.vlastnikKacice = vlastnikKacice;
    }

    public Hrac getVlastnikKacice() {
        return vlastnikKacice;
    }


}
