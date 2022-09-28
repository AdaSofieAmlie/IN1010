public class Spesialist extends Lege implements Godkjenningsfritak{

    private String kontrollId;

    public Spesialist(String navn, String kontrollID){
        super(navn);
        kontrollId = kontrollID;
    }

    @Override
    public String hentKontrollId(){
        return kontrollId;
    }

    @Override
    public String toString(){
        return "Lege (Spesialist) " + navn + " og har id: " + id + ". kontrollID: " + kontrollId;
    }
}
