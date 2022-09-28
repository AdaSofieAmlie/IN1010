

public class Vanedannende extends Legemiddel {
    private int styrke;

    public Vanedannende(String navn, int pris, double mengdeVirkestoff, int styrke) {
        super(navn, pris, mengdeVirkestoff);
        this.styrke = styrke;
    }

    public int hentVanedannendeStyrke() {
        return styrke;
    }

    @Override
    public String toString() {
        return "Navn: " + navn + ". ID: " + ID + ". Pris: " + pris + "kr" + 
        ". Mg virkestoff: " + mgVirkestoff + ". Vanedannende styrke: " + styrke + "\n";
    }
}
