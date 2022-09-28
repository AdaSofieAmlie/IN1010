

public class Pasient{
    protected String navn, fodselnr;
    protected int id;
    protected static int idTeller = 0;
    public Stabel<Resept> stabel = new Stabel<Resept>();
    
    public Pasient( String inNavn, String fodselsnummer){
        navn = inNavn;
        fodselnr = fodselsnummer;
        id = idTeller ++;
        }
    public void leggTil(Resept r){
        stabel.leggPaa(r);
    }
    
    public Stabel<Resept> hentUt(){
        return stabel; 
    }
    public String hentNavn() {
        return navn;
    }
    public int hentId() {
        return id;
    }
    public String hentFodselsnr() {
        return fodselnr;
    }
    public String toString() {
        return navn + ", " + fodselnr;
    }
}
