

public class HvitResept extends Resept {
    
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge() {
        return "Hvit";
    }

    @Override
    public String toString() {
        return "ReseptID: " + ID + ". Legemiddel: " + legemiddel.toString() + 
        ". Lege: " + utskrivendeLege + ". Resept farge: Hvit. Reit: " + reit + "\n";
    }

    @Override
    public int prisAaBetale() {
        // TODO Auto-generated method stub
        return 0;
    }


}
