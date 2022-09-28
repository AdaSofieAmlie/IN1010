public class Lenkeliste<T> implements Liste<T>{
    public class Node{
        Node neste = null;
        T data;     //Samme som int tall??      FJERN!!!!
        Node( T x){
            data = x;
        }
    }

    Node start = null;

    @Override
    public int stoerrelse(){
        //this.toString();
        int teller = 0;
        Node peker = start;
        while (peker != null){
            peker = peker.neste;
            teller ++;
        }
        //this.toString();
        //System.out.println(teller);
        return teller;
    }

    @Override
    public void leggTil(T x){
        Node ny = new Node(x);
        if (start == null){
            start = ny;
        }
        Node peker = start;
        while (peker.neste != null){
            peker = peker.neste;
        }
        peker.neste = ny;
        ny.neste = null;
        //this.toString();
    }

    @Override
    public void leggTil(int pos, T x)
        throws UgyldigListeIndeks{
        if (this.stoerrelse() < pos){
            throw new UgyldigListeIndeks(pos);
        }
        if (pos < 0){
            throw new UgyldigListeIndeks(pos);
        }
        if (pos == 0){
            Node nyNode = new Node(x);
            nyNode.neste = start;
            start = nyNode;
        } else {
            Node peker = start;
            Node nyNode = new Node(x);
            //System.out.println(nyNode.data);
            for (int j=0; j < pos-1; j++){
                if (peker != null){
                    peker = peker.neste;
                }
            }
            if (peker != null){
                nyNode.neste = peker.neste;
                peker.neste = nyNode;
            }
        }
        //this.toString();
    }

    @Override
    public void sett(int pos, T x)
        throws UgyldigListeIndeks{
        if (start == null){
            throw new UgyldigListeIndeks(-1);
        }
        if (this.stoerrelse()-1 < pos){
            throw new UgyldigListeIndeks(pos);
        }
        if (pos < 0){
            throw new UgyldigListeIndeks(pos);
        }
        Node peker = start;
        if (pos == 0){
            Node nyNode = new Node(x);
            nyNode.neste = peker.neste;
            start = nyNode;
        } else {
            Node nyNode = new Node(x);
            //System.out.println(nyNode.data);
            for (int j=0; j<pos-1; j++){
                if (peker != null){
                    peker = peker.neste;
                }
            }
            nyNode.neste = peker.neste.neste;
            peker.neste = nyNode;

        }
        //this.toString();
    }

    @Override
    public T hent(int pos)
        throws UgyldigListeIndeks{
        if (start == null){
            throw new UgyldigListeIndeks(-1);
        }
        if (this.stoerrelse()-1 < pos){
            throw new UgyldigListeIndeks(pos);
        }
        if (pos < 0){
            throw new UgyldigListeIndeks(pos);
        }
        int teller = 0;
        Node peker = start;
        if (peker == null){
            return null;
        }
        while (peker != null){
            if (teller == pos){
                return peker.data;
            }
            teller += 1;
            peker = peker.neste;
        }
        return null;
    }

    @Override
    public T fjern(int pos)
        throws UgyldigListeIndeks{
        if (start == null){
            throw new UgyldigListeIndeks(-1);
        }
        if (this.stoerrelse()-1 < pos){
            throw new UgyldigListeIndeks(pos);
        }
        if (pos < 0){
            throw new UgyldigListeIndeks(pos);
        }
        T r = null;
        int teller = 1;
        Node peker = start;
        if (pos == 0){
            r = peker.data;
            start = peker.neste;
        }
        if (pos >= 1){
            while (teller < pos){
                teller += 1;
                peker = peker.neste;
            }
            r = peker.neste.data;
            peker.neste = peker.neste.neste;
        }
        //System.out.println(r);
        //this.toString();
        return r;
    }

    @Override
    public T fjern()
        throws UgyldigListeIndeks{
        if (start == null){
            throw new UgyldigListeIndeks(-1);
        }
        T s;
        Node peker = start;
        if (start == null){
            return null;
        }
        start = start.neste;
        return peker.data;
    }

    public String toString(){
        String s = "";
        Node peker = start;
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
}
