import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Dataklynge{
    private ArrayList<Rack> listeAvRacks;
    private int antNoderPerRack, antP, antN, antallNoderFil, minnePerNodeFil, antProsPerNodeFil;

    public Dataklynge(String filnavn){
        listeAvRacks = new ArrayList<Rack>();
        /*if (noderPerRack > 0){
            antNoderPerRack = noderPerRack;
        } else {
            antNoderPerRack = 1;
        } */
        File fil = new File(filnavn);
        Scanner in;
        try{
            in = new Scanner(fil);
        }catch(FileNotFoundException exception){
            System.out.println("Fant ikke filen");
            in = new Scanner("");
        }
        antNoderPerRack = in.nextInt();

        while (in.hasNextInt()){
            antallNoderFil = in.nextInt();
            System.out.println(antallNoderFil);
            minnePerNodeFil = in.nextInt();
            System.out.println(minnePerNodeFil);
            antProsPerNodeFil = in.nextInt();
            System.out.println(antProsPerNodeFil);
            for (int i = 0; i < antallNoderFil; i++){
                Node nyNode = new Node(minnePerNodeFil, antProsPerNodeFil);
                leggTilNode(nyNode);
            }
        }
        in.close();

    }

    public void leggTilNode(Node node){
        Rack ledigRack = null;
        int i = 0;
        while ((ledigRack == null) && (i < listeAvRacks.size())){
            if (listeAvRacks.get(i).getAntNoder() < antNoderPerRack){
                ledigRack = listeAvRacks.get(i);
            }
            i += 1;
        }
        if (ledigRack == null){
            ledigRack = new Rack();
            listeAvRacks.add(ledigRack);
        }
        ledigRack.settInn(node);
    }

    public int antProssesorer(){
        antP = 0;
        for (Rack r : listeAvRacks){
            antP += r.antProssesorer();
        }
        return antP;
    }

    public int noderMedNokMinne(int paakrevdMinne){
        antN = 0;
        for (Rack r : listeAvRacks){
            antN += r.noderMedNokMinne(paakrevdMinne);
        }
        return antN;
    }

    public int antRacks(){
        return listeAvRacks.size();
    }

}
