import java.util.ArrayList;

public abstract class Rute{
    public int radNr, kolonneNr;
    public Rute nord,sor,oest,vest;

    public abstract char tilTegn();
    public abstract void gaa(Rute komFra, ArrayList<Tuppel> sti);
    public abstract void settNaboer(Rute nordInn, Rute sorInn, Rute oestInn, Rute vestInn);

    public void finnUtVei(){
        //Starter en tom sti
        ArrayList<Tuppel> sti = new ArrayList<Tuppel>();
        //Begyner å gå fra ruten som kaller på finnUtVei();
        gaa(null, sti);
    }

    //Brukes i Aapning
    public void giLabyrint(Labyrint labInn){
        return;
    }

}
