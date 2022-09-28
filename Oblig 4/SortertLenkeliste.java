

public class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {

    @Override
    public void leggTil(T x) { //sette inn elementer i sortert rekkefølge, fra minst til størst.
        Node peker = start;
        Node nynode = new Node(x);     

        if (peker == null) { //legge til i tom liste
            start = nynode;

        } else if (peker.data.compareTo(x) > 0) { //legge til først i ikke tom liste, elementet er minst
            nynode.neste = peker;
            start = nynode;

        } else {
            
            while (peker.neste != null && peker.neste.data.compareTo(x) <= 0) { // så lenge peker.neste.data er mindre enn x
                peker = peker.neste;

            }

            if (peker.neste != null) { //legge til midt i lista og hvis to objekter er like(legger inn bak)
                nynode.neste = peker.neste;
            }
            peker.neste = nynode;
            
        }
    }

    @Override
    public T fjern() { //det største elementet skal tas ut, det ligger alltid bakerst
        sjekkTomListe(0); //kan ikke fjerne noe hvis lista er tom
        
        Node peker = start;
        T res = null;

        if (peker.neste == null) { //bare ett element i lista
            res = peker.data;

            start = peker.neste; //setter start til den neste noden, 
    
            return res;

        } else {

            for (int i = 0; i < stoerrelse() - 2; i++) { //teller oss frem til objektet før der vi skal fjerne
                peker = peker.neste;
            }

            res = peker.neste.data; 

            if (peker.neste.neste == null) { //hvis node er siste i lista
                peker.neste = null;
            } else { //ellers
                peker.neste = peker.neste.neste; //overskriver node vi ønsker å fjerne med den neste noden i lista
            }

            return res;
            }
    }

    @Override
    public void sett(int pos, T x) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    } 

    @Override
    public void leggTil(int pos, T x) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }
}

