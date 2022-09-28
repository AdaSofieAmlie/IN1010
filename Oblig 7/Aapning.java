import java.util.ArrayList;

public class Aapning extends HvitRute{
    public int radNr, kolonneNr;
    public Labyrint lab;

    public Aapning(int radNrInn, int kolonneNrInn, Labyrint labInn){
        super(radNrInn, kolonneNrInn);
        radNr = radNrInn;
        kolonneNr = kolonneNrInn;
        lab = labInn;
    }

    @Override
    public void gaa(Rute komFra, ArrayList<Tuppel> sti){
        //KOpierer sti
        ArrayList<Tuppel> nySti = new ArrayList<>(sti);
        //lager siste kordinater
        Tuppel tup = new Tuppel(radNr, kolonneNr);
        //Legger til i slutten av stien
        nySti.add(tup);
        //Legger til hele stien i labyrint array utveier
        lab.utveier.add(nySti);
    }

    @Override
    public char tilTegn(){
        return '!';     // ! for aa markere Aapning
    }

    /*
    @Override
    public void giLabyrint(Labyrint labInn){
        //Får tak i labyrint for å legge til stien som ender i aapningen this
        lab = labInn;
    }
    */

}
