

public class PResept extends HvitResept {

    //siden PRespet alltid skal ha 3 reit, gjernet jeg argumentet den skal ta inn og skrev inn at reit = 3
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(legemiddel, utskrivendeLege, pasient, 3); 
    }
    
    @Override
    public int prisAaBetale() {
        int nypris = legemiddel.hentPris() - 108; //prisen på PRespeter har 108kr rabatt

        if (nypris < 0) {
            legemiddel.settNyPris(0);
            return 0;
        } else {
            legemiddel.settNyPris(nypris);
            return nypris;
        }
    }
}


