public class Vanlig extends Legemiddel {

    public Vanlig(String navn, int pris, double mengdeVirkestoff) {
        super(navn, pris, mengdeVirkestoff);
        
    }
    
    @Override
    public String toString() {
        return "Navn: " + navn + ". ID: " + ID + ". Pris: "
        + pris + "kr" + ". Mg virkestoff: " + mgVirkestoff + "\n";   
    }
}
