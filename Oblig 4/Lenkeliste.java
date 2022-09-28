

import java.util.Iterator;

public class Lenkeliste<T> implements Liste<T> {
    
    class Node { //indre klasse node som holder på data og ref til neste node
        Node neste = null; //referanse til neste node
        T data; //data

        //konstruktør til Node
        Node (T x) { 
            data = x;
        }
    }

    protected Node start = null; //referanse til første node

    @Override
    public int stoerrelse() {
        int teller = 0;
        Node peker = start;

        while (peker != null) { //teller antall noder
            teller++;
            peker = peker.neste;
        }
        return teller;
    }

    @Override
    public void leggTil(int pos, T x) throws UgyldigListeIndeks { //legger til i oppgitt pos

        if (start == null & pos == 0 || pos == stoerrelse()) { //dvs. tom liste og oppgitt pos = 0, da skal man kunne legge til eller hvis vi prøver å legge inn helt på slutten av listen
            leggTil(x);
        } else {
            sjekkIndex(pos); 
            /*
            hvis man ikke prøver å legge til tom liste ved pos = 0 eller helt på slutten av liste
            må man sjekke indexen som vanlig
            */
            Node peker = start;
            Node nynode = new Node(x);
    
            if (pos == 0) { //hvis legger til først i lista med ikke tom liste
                start = nynode;
                nynode.neste = peker;
            
            } else {
                for (int i = 0; i < pos - 1; i++) { //teller oss frem til objeket før der vi skal legges til
                    peker = peker.neste;
                }
                nynode.neste = peker.neste;
                peker.neste = nynode;
            }
        }
    }


    @Override
    public void leggTil(T x) { //legger til på slutten av listen
        Node peker = start;

        if (peker == null) { //hvis tom liste
            start = new Node(x);
        } else {
            for (int i = 0; i < stoerrelse() - 1; i++) { //teller oss frem til objektet før der vi skal legge til
                peker = peker.neste;
            }
            peker.neste = new Node(x);
        }   
    }

    @Override
    public void sett(int pos, T x) { //over skriv data i en node i gitt pos med gitt data
        sjekkTomListe(pos);
        sjekkIndex(pos);
        
        Node peker = start;

        for (int i = 0; i < pos; i++) { //teller oss frem til objektet
            peker = peker.neste;
        }
        peker.data = x; //overkriver dataen
    }

    @Override
    public T hent(int pos) { //henter T i node fra oppgitt pos
        sjekkTomListe(pos);
        sjekkIndex(pos);
        
        Node peker = start;
        
        for (int i = 0; i < pos; i++) { //finner frem til noden med opgitt pos
            peker = peker.neste;
        }
        return peker.data; //returnerer T i noden
    }

    @Override
    public T fjern(int pos) { //fjerner node fra oppgitt pos og returnerer dataen i den fjerna noden
        sjekkTomListe(pos);
        sjekkIndex(pos);

        Node peker = start;

        if (pos == 0) { //hvis noden er første i lista
            return fjern();

        } else {
            for (int i = 0; i < pos - 1; i++) { //teller oss frem til objeket før det som skal fjernes
                peker = peker.neste;
            }

            T res = peker.neste.data; //tar vare på data fra noden vi skal fjerne

            if (peker.neste.neste == null) { //hvis node er siste i lista
                peker.neste = null;
            } else { //ellers
                peker.neste = peker.neste.neste; //overskriver node vi ønsker å fjerne med den neste noden i lista
            }

            return res;
        }
    }

    @Override
    public T fjern() { //fjerne og returnere node på starten av listen
        sjekkTomListe(0);

        Node peker = start;
        T res = peker.data;

        start = peker.neste; //setter start til den neste noden, 

        return res;
    }

    private void sjekkIndex(int i) throws UgyldigListeIndeks {
        if (!(0 <= i && i < stoerrelse())) { //hvis indexen ikke er i range(0 til og med størrelsen)
            throw new UgyldigListeIndeks(i);    
        }
    }

    protected void sjekkTomListe(int i) throws UgyldigListeIndeks {
        if (start == null) { //hvis tom liste
            throw new UgyldigListeIndeks(i);
        } 
    }

    public String toString(){
        String s = "";
        Node peker = start;

        if(start == null){
            s = "listen er tom";
        }
        while (peker != null) {
            s += peker.data;
            peker = peker.neste;
            if (peker != null)
                s += ",";
        }
        s += "\n";
        System.out.println(s);
        return s;
    }

    @Override
    public Iterator<T> iterator() {
        return new LenkelisteIterator();
    }

    private class LenkelisteIterator implements Iterator<T>{
        private int gjeldendeIndeks = 0;

        public T next(){
            return hent(gjeldendeIndeks++);
        }

        public boolean hasNext(){
            return gjeldendeIndeks < stoerrelse();
        }
    }
}
