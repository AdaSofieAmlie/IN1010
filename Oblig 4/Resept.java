

abstract class Resept {
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;
    protected int ID;
    private static int curID = 0; 

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        curID++;
        ID = curID;
        prisAaBetale();        
    }
    public int hentID() {
        return ID;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }
    
    public int hentPasientId() {
        return pasient.hentId();
        
    }
    
    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit == 0) {
            return false;
        }
        reit = reit - 1;
        return true;
    }

    abstract public String farge();
    
    abstract public int prisAaBetale();

    abstract public String toString();
}
