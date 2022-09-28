public class Militaerresepter extends HviteResepter{
    private String str;

    public Militaerresepter(Legemiddel legemiddel, Lege utskrivendeLege, int passientId, int reid){
        super(legemiddel, utskrivendeLege, passientId, reid);
    }

    @Override
     public int prisAaBetale(){
         return (legemiddel.hentPris() - (1 * legemiddel.hentPris()));       //hundre prosent rabatt
     }

     @Override
     public String toString(){
         str = "Militaerresept id: " + id + " for: " + legemiddel.toString() + " For pasient: " + pasId + " skrevet av " + utskrivendeLege.toString();
         str += ". Den koster: " + prisAaBetale() + " kr etter rabatt.";
         return str;
     }
}
