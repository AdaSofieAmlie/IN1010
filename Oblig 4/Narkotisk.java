

public class Narkotisk extends Legemiddel {
    private int styrke;

    public Narkotisk(String navn, int pris, double mengdeVirkestoff, int styrke) {
        super(navn, pris, mengdeVirkestoff);
        this.styrke = styrke;
    }

    public int hentNarkotiskStyrke() {
        return styrke;
    }

    @Override
    public String toString() {
        return "Navn: " + navn + ". ID: " + ID + ". Pris: " + pris + "kr" + ". Mg virkestoff: " + mgVirkestoff
                + ". Narkotisk styrke: " + styrke + "\n";
    }

}

