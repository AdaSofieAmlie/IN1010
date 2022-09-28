import java.util.HashMap;
import java.util.ArrayList;

public class BeholderHM{        //Beholder klasse
    public ArrayList< HashMap<String,Subsekvens> > alleH = new ArrayList< HashMap<String,Subsekvens> >();
    public int  antall = 0;

    //Legg til en ny hm
    public void leggTil(HashMap<String,Subsekvens> hInn) {
        antall++;
        alleH.add(hInn);
    }

    //Hent ut alle hm i en arrayliste
    public ArrayList hentUtAlle(){
        return alleH;
    }

    //Finner lengden på beholderen;
    public int str(){
        return alleH.size();
    }

    //Henter siste hm i arraylisten
    public HashMap<String,Subsekvens> hentEn(){
        antall--;
        return alleH.remove(0);
    }

    //Henter siste hm i arraylisten uten å fjerne
    public HashMap<String,Subsekvens> hentEnUtenFjerning(){
        return alleH.get(0);
    }

    //Fletter
    static HashMap<String,Subsekvens> flett (HashMap<String,Subsekvens> en, HashMap<String,Subsekvens>  to){
        Subsekvens hentet;
        HashMap<String,Subsekvens> ny = new HashMap<String,Subsekvens>(en);

        for (String sKey : to.keySet()){
            if (ny.containsKey(sKey)){
                Subsekvens sub2 = to.get(sKey);
                Subsekvens subNy = ny.get(sKey);
                int antPluss = sub2.antall();
                subNy.leggTil(antPluss);
            } else {
                ny.put(sKey, to.get(sKey));
            }
        }

        return 	ny;
    }

    //Skriv ut
    public void skrivUt(){
        for ( int i=0; i < alleH.size(); i++){
            System.out.println("---------HASHMAP " + i +" --------------");
            for (Subsekvens s : alleH.get(i).values()){
                System.out.println(s.antall() + " av subsekvens " + s.nokkel());
            }

        }
    }

}
