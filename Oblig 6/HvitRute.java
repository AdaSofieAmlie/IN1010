import java.util.ArrayList;

public class HvitRute extends Rute{
    public int radNr, kolonneNr;
    public Rute nord,sor,oest,vest;

    public HvitRute(int radNrInn, int kolonneNrInn){
        radNr = radNrInn;
        kolonneNr = kolonneNrInn;
    }

    @Override
    public void gaa(Rute komFra, ArrayList<Tuppel> sti){
        //System.out.println("Rad: " + radNr + ". Kolonne: " + kolonneNr);

        //Lager ny sti/ kopierer siten den kom fra
        ArrayList<Tuppel> nySti = new ArrayList<>(sti);
        //Lager et nytt tuppel med koordinater fra this rute
        Tuppel tup = new Tuppel(radNr, kolonneNr);
        //Legger det nye tuppelet til i stien
        nySti.add(tup);

        //Går videre til neste rute om mulig
        if( nord != komFra){
            nord.gaa(this, nySti);
        }
        if (sor != komFra){
            sor.gaa(this, nySti);
        }
        if (oest != komFra){
            oest.gaa(this, nySti);
        }
        if (vest != komFra){
            vest.gaa(this, nySti);
        }
        //GENERL KOMENTAR: sender inn this for å ungå å gå tilbake der vi kom fra
    }

    @Override
    public char tilTegn(){
        return '.';
    }

    @Override
    public void settNaboer(Rute nordInn, Rute sorInn, Rute oestInn, Rute vestInn){
        //Setter naboer
        nord = nordInn;
        sor = sorInn;
        oest = oestInn;
        vest = vestInn;
    }

}
