public class Stabel<T> extends Lenkeliste<T>{

    public void leggPaa(T x){
        super.leggTil(this.stoerrelse(), x);
    }
    public T taAv(){
        return super.fjern((this.stoerrelse()-1));
    }
}
