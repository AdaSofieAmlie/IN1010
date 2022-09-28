public class PResepter extends HviteResepter{
    private int ntPris;
    private String str;

    public PResepter(Legemiddel legemiddel, Lege utskrivendeLege, int passientId, int reid){
        super(legemiddel, utskrivendeLege, passientId, 3);
    }

    @Override
     public int prisAaBetale(){
         if (legemiddel.hentPris() <= 108){
             return 0;
         }
         return (legemiddel.hentPris() - 108);
     }

     @Override
     public String toString(){
         str = "P resept id: " + id + " for: " + legemiddel.toString() + " For pasient: " + pasId + " skrevet av " + utskrivendeLege.toString();
         str += ". Den koster: " + prisAaBetale() + " kr etter rabatt.";
         return str;
     }
}
