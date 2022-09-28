public class Hovedprogram{
    public static void main(String[] args){
        Dataklynge dataklynge = new Dataklynge("dataklynge.txt");
        System.out.println("Noder med minst 32GB: " + dataklynge.noderMedNokMinne(32));
        System.out.println("Noder med minst 64GB: " + dataklynge.noderMedNokMinne(64));
        System.out.println("Noder med minst 128GB: " + dataklynge.noderMedNokMinne(128));
        System.out.println();
        System.out.println("Antall prossessorer: " + dataklynge.antProssesorer());
        System.out.println("Antall racks: " + dataklynge.antRacks());

    }
}
