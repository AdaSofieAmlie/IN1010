import java.util.ArrayList;

public class SortRute extends Rute{
    public int radNr, kolonneNr;
    public Rute nord,sor,oest,vest;


    public SortRute(int radNrInn, int kolonneNrInn){
        radNr = radNrInn;
        kolonneNr = kolonneNrInn;
    }

    @Override
    public void gaa(Rute komFra, ArrayList<Tuppel> sti){
        return;         //Stopper siden det ikke er mulig gå gå her
    }

    @Override
    public char tilTegn(){
        return '#';
    }

    @Override
    public void settNaboer(Rute nordInn, Rute sorInn, Rute oestInn, Rute vestInn){
        //setter naboer
        nord = nordInn;
        sor = sorInn;
        oest = oestInn;
        vest = vestInn;
    }



}
