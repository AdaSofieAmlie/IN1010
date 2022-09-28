

public class BlaaResept extends Resept{

    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }
    @Override
    public String farge() {
        return "Blaa";
    }

    @Override
    public int prisAaBetale() {
        int nypris = legemiddel.hentPris()/4; //prisen på blaaResepter er 1/4 av vanlig pris
        legemiddel.settNyPris(nypris);
        return nypris;
    }

    @Override
    public String toString() {
        return ("ReseptID: " + ID + ". Legemiddel: " + legemiddel.toString() + 
        ". Lege: " + utskrivendeLege + ". Resept farge: Blaa. Reit: " + reit + "\n");
    }
}
