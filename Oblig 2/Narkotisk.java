public class Narkotisk extends Legemiddel{
    private int styrke;

    public Narkotisk(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public int hentNarkotiskStyrke(){
        return styrke;
    }

    @Override
    public String toString(){
        return "Legemiddel ID: " + id + ": " + navnet + " koster " + prisen + " kr og har " + virkeS + " mg virkestoff. Og har styrke: " + styrke + ". ";
    }
}
