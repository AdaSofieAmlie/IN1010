

public class MResept extends HvitResept {

    public MResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public int prisAaBetale() { //prisen på Mrespeter får 100% rabatt
        legemiddel.settNyPris(0); //oppdaterer prisen til legemiddelt
        return 0;
    }

    
}
