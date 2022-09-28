import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Oblig5 extends Thread {                //Hovedprogram
    public static ArrayList<Thread> mineTraader = new ArrayList<Thread>();
    public static ArrayList<Thread> traaderFletting = new ArrayList<Thread>();
    public static ArrayList<Traad> mineT = new ArrayList<Traad>();
    public static int lengde = 3;       //Jeg satt lengde 3, det kan endres

    public static void main (String[] args) throws Exception {
        System.out.println("Oblig5");

        System.out.println("Vær tolmodlig, trådene bruker litt tid");

        //Lese fra fil forbredelser
        File fil = new File("Data/metadata.csv");
        Scanner scanner = null;

        try{
            scanner = new Scanner(fil);
        }catch(FileNotFoundException e){
            System.out.println("Fant ikke filen");
        }

        //Lager en monitor
        Monitor monitor = new Monitor();

        //lager beholdere
        BeholderHM friske = new BeholderHM();       //Har ikke hatt viruset /false
        BeholderHM syke = new BeholderHM();     //Har hatt viruset  /true


        //Flette til en beholder antal traader
        int antTraader = Integer.parseInt(args[0]);
        int deltPaaTo = antTraader/2;


        //Leser fra fil metadata
        String inn = scanner.nextLine();
        while (scanner.hasNextLine()){
            inn = scanner.nextLine();
            String[] info = inn.split(",");

            //Lager tråden
            Traad tr = new Traad(info[0], info[1], lengde, friske, syke, monitor);
            Thread traaden = new Thread(tr);

            //Legger i array for .join() senere
            mineTraader.add(traaden);
            mineT.add(tr);

            //Starter traadne
            traaden.start();

        }

        //passer på at alle traadene som leser fra fil er ferdige
        for (Thread t : mineTraader){
            t.join();
        }

        //lager tråder til fletting
        for (int i=0 ; i < (deltPaaTo); i++){

            //flette tråder til friske
            FletteTraad fletteFirske = new FletteTraad(monitor, friske);
            Thread traadFletteFriske = new Thread(fletteFirske);

            //flette tråder til syke
            FletteTraad fletteSyke = new FletteTraad(monitor, syke);
            Thread traadFletteSyke = new Thread(fletteSyke);
            //System.out.println("Etter syke");

            //Legger i en array for å .join() etterpå.
            traaderFletting.add(traadFletteFriske);
            traaderFletting.add(traadFletteSyke);

            //Starter trådene
            traadFletteFriske.start();
            traadFletteSyke.start();
        }

        //Terminerer flette tråder for å forsikre meg om at det bare er en hm i begge beholderne
        for (Thread tr : traaderFletting){
            tr.join();
        }

        //Enkel test!
        HashMap<String,Subsekvens> friskHash = friske.hentEnUtenFjerning();     //Henter siste hm
        HashMap<String,Subsekvens> sykHash = syke.hentEnUtenFjerning();         //Henter siste hm

        int totaltDominante = 0;
        int diff = 0;

        System.out.println("Test");

        for (String s : sykHash.keySet()){  //Går igjennom alle subsekvensene i hasmap syke
            if (friskHash.containsKey(s)){      //Skjekker om samme finnes i friske
                int antF = friskHash.get(s).antall();
                int antS = sykHash.get(s).antall();
                //System.out.println(antF);
                //System.out.println(antS);
                diff = antS - antF;     //Finner diff
            }
            if (diff >= 5){     //Skjekker om diff er større enn elelr lik 5, om true: skriver ut info
                System.out.println("Subsekvens: " + s );
                System.out.println("Antall friske som har forekmost av sub: " + friskHash.get(s).antall());
                System.out.println("Antall syke som har forekmost av sub: " + sykHash.get(s).antall());
                System.out.println("Differansen: " + diff);
                totaltDominante += 1;
            }

        }
        System.out.println("Totalt: " + totaltDominante);       //Skrive ut total;

        //Ferdig!
        System.out.println("Main ferdig");
        scanner.close();
    }
}
