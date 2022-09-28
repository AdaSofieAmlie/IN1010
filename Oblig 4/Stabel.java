

public class Stabel<T> extends Lenkeliste<T> {

    public void leggPaa(T x){ //legger til på slutten/toppen
        leggTil(x);
    }
    
    public T taAv() { //tar av på slutten/toppen
        return fjern(stoerrelse() - 1);
    }
}


