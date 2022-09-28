import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.locks.*;
//import java.oi.InterruptException;


public class Monitor{
    public Lock laas = new ReentrantLock();
    public Condition har2 = laas.newCondition();
    public volatile int hashUte = 0;

    //Legg til
    public void leggTilM(HashMap<String,Subsekvens> hInn, BeholderHM type) {
        laas.lock();
        try{
            type.leggTil(hInn);
            hashUte -= 1;
            har2.signal();
        } finally{
            laas.unlock();
        }
    }


    public ArrayList<HashMap<String,Subsekvens>> hentTo(BeholderHM type) {
        laas.lock();
        //System.out.println("Før vent");
        try{
            while (type.str() < 2 && hashUte > 1){
                har2.await();
            }
            //System.out.println("EtterVent");       //Venter på at det skal være hverfall en hashmap i beholderen
            //System.out.println(hashUte);
            if (hashUte == -2){
                return null;
            }
            ArrayList<HashMap<String,Subsekvens>> toElementer = new ArrayList<HashMap<String,Subsekvens>>();

            HashMap<String,Subsekvens> forste = type.hentEn();
            toElementer.add(forste);
            HashMap<String,Subsekvens> andre = type.hentEn();
            toElementer.add(andre);

            hashUte += 2;
            //System.out.println("toElementer");
            return toElementer;       //
        } catch (InterruptedException ie) {
            System.out.println("Feil");
            System.out.print(ie.getMessage());
            return null;
        }

        finally{
            laas.unlock();
        }

    }

    public boolean flettM (BeholderHM beholderInn){
        ArrayList<HashMap<String, Subsekvens>> foorFletting;
        laas.lock();
        try{
            foorFletting = hentTo(beholderInn);
            if (foorFletting != null){
                if (foorFletting.size() == 2){
                    HashMap<String, Subsekvens> en = foorFletting.get(0);
                    HashMap<String, Subsekvens> to = foorFletting.get(1);

                    HashMap<String, Subsekvens> ny = beholderInn.flett(en,to);
                    leggTilM(ny, beholderInn);

                    if (beholderInn.str() < 2){
                        return false;
                    }
                    return true;
                }else{
                    System.out.println("Ikke nok elementer");
                }
            }
            return false;
        } finally{
            laas.unlock();

        }

    }


}
