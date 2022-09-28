public abstract class Resept{
    protected int pasId, reid, id;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected static int generator = 1;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reid){
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        pasId = pasientId;
        this.reid = reid;
        id = generator++;
    }

    public int hentID(){
        return id;
    }

    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    public Lege hentLege(){
        return utskrivendeLege;
    }

    public int hentPasientId(){
        return pasId;
    }

    public int hentReid(){
        return reid;
    }

    public boolean bruk(){
        if (reid == 0){
            return false;
        }
        reid = reid - 1;
        return true;
    }

    abstract public String farge();

    abstract public int prisAaBetale();

    public String toString(){
        return "Resept id:" + id + " for: " + legemiddel.toString() + " For pasient: " + pasId + " skrevet av " + utskrivendeLege.toString();
    }
}
