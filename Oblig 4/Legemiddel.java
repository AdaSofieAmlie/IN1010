

abstract class Legemiddel {
    protected String navn;
    protected int pris;
    protected double mgVirkestoff;
    protected int ID;
    private static int curID = 0; //variabel for å holde rede på neste ID man kan assigne

    public Legemiddel(String navn, int pris, double mgVirkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.mgVirkestoff = mgVirkestoff;
        curID++;
        ID = curID;
    }
    
    public int hentID() {
        return ID;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return mgVirkestoff;
    }

    public void settNyPris(int nyPris) {
        pris = nyPris;
    }  
    
    abstract public String toString();
}
