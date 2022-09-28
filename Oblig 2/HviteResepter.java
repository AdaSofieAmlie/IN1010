abstract public class HviteResepter extends Resept{

    public HviteResepter(Legemiddel legemiddel, Lege utskrivendeLege, int passientId, int reid){
        super(legemiddel, utskrivendeLege, passientId, reid);
    }

    @Override
    public String farge(){
        return "Hvit";
    }
    @Override
    public String toString(){
        return "Hvit resept id: " + id + " for: " + legemiddel.toString() + " For pasient: " + pasId + " skrevet av " + utskrivendeLege.toString();
    }

}
