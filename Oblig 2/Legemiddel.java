public class Legemiddel{
    protected int prisen, id;
    protected double virkeS;
    protected String navnet;
    protected static int generator = 1;

    public Legemiddel(String navn, int pris, double virkestoff){
        navnet = navn;
        prisen = pris;
        virkeS = virkestoff;
        id = generator++;
    }

    public int hentID(){
        return id;
    }

    public String hentNavn(){
        return navnet;
    }

    public int hentPris(){
        return prisen;
    }

    public double hentVirkestoff(){
        return virkeS;
    }

    public void settNyPris(int nyPris){
        prisen = nyPris;
    }

    public String toString(){
        return "Legemiddel ID: " + id + ": " + navnet + " koster " + prisen + " kr og har " + virkeS + " mg virkestoff.";
    }
}
