public class SortertLenkeliste<T extends Comparable <T>> extends Lenkeliste<T>{

    @Override
    public void leggTil(T x){
        Node ny = new Node(x);
        if (start == null || (ny.data.compareTo(start.data)<=0)){
            ny.neste = start;
            start = ny;
        } else{
            Node peker = start;
            while ((peker.neste != null) && (ny.data.compareTo(peker.neste.data) > 0)){
                peker = peker.neste;
            }
            ny.neste = peker.neste;
            peker.neste = ny;
        }
    }

    @Override
    public T fjern()
        throws UgyldigListeIndeks{
        if (start == null){
            throw new UgyldigListeIndeks(-1);
        }
        Node stor = start;
        Node peker = start;
        int pos = 0;
        if (start == null){
            return null;
        }
        while (peker.neste != null){
            pos += 1;
            if (peker.data.compareTo(stor.data) > 0){
                stor = peker.neste;
            }
            peker = peker.neste;
        }
        T s = super.fjern(pos);
        return s;
    }

    @Override
    public void sett(int pos, T x) throws UnsupportedOperationException{
    throw new UnsupportedOperationException();
    }

    @Override
    public void leggTil(int pos, T x) throws UnsupportedOperationException{
    throw new UnsupportedOperationException();
    }
}
