public class BlaaResepter extends Resept{
    private int nyPris;
    private String str;

    public BlaaResepter(Legemiddel legemiddel, Lege utskrivendeLege, int passientId, int reid){
        super(legemiddel, utskrivendeLege, passientId, reid);
    }

    @Override
    public String farge(){
        return "Blaa";
    }

    @Override
    public int prisAaBetale(){
        nyPris = (int)(legemiddel.hentPris() * 0.25);
        return nyPris;
    }

    @Override
    public String toString(){
        str = "Blaa resept id: " + id + " for: " + legemiddel.toString() + " For pasient: " + pasId + " skrevet av " + utskrivendeLege.toString();
        str += ". Den koster: " + prisAaBetale() + " kr etter rabatt.";
        return str;
    }
}
