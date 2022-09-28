import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Traad implements Runnable{
    public String filTittel, verdi;
    public String subString;
    public int subSekvensLengde;
    public HashMap<String, Subsekvens> hashMap;
    public BeholderHM friske, syke;
    public Monitor monitor;

    public Traad(String filNavn, String v, int ssl, BeholderHM frisk, BeholderHM syk, Monitor mInn){
        filTittel = filNavn;
        verdi = v;
        subSekvensLengde = ssl;
        hashMap = new HashMap<String, Subsekvens>();
        friske = frisk;
        syke = syk;
        monitor = mInn;
    }

    @Override
    public void run(){
        //Leser fra fil
        File filen = new File("Data/" + filTittel);
        Scanner filInfo = null;

        try{
            filInfo = new Scanner(filen);
        }catch(FileNotFoundException e){
            System.out.println("Fant ikke filen");
        }

        String inn = filInfo.nextLine();

        while (filInfo.hasNextLine()){
            inn = filInfo.nextLine();
            String innInfo = inn;

            //Legger i hm
            for (int i = 0; i + subSekvensLengde <= inn.length(); i ++) {
                subString = innInfo.substring(i,i+subSekvensLengde);
                hashMap.putIfAbsent(subString,new Subsekvens(subString));

            }
        }

        //Plasserer i forksjellige beholdere
        if (verdi.equals("True")){
            monitor.leggTilM(hashMap,syke );
            //syke.leggTil(hashMap);
        } else if (verdi.equals("False")){
            //friske.leggTil(hashMap);
            monitor.leggTilM(hashMap,friske );
        } else {
            System.out.println("FEIL!");
        }


        filInfo.close();
    }

}
