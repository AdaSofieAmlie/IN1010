import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Finnutvei implements ActionListener{
    public int i, j;
    public TypeRute[][] rut;
    public Labyrint lab;

    public Finnutvei(int i, int j, Labyrint labInn, TypeRute[][] rut){
        this.i = i;
        this.j = j;
        this.rut = rut;
        lab = labInn;
    }
    @Override
    public void actionPerformed (ActionEvent e) {
        System.out.println("trykket ("+i + ", "+j+")");
        loos(i, j, lab);
    }

    public void loos(int i, int j, Labyrint lab){     //Løs
        //Restarter brettet
        int rad = rut.length;
        int kol = rut[0].length;
        System.out.println(rad);
        System.out.println(kol);

        for(int x=0; x<rad; x++){
            for (int y=0; y<kol; y++){
                rut[x][y].restart();
            }
        }
        try {
            ArrayList<ArrayList<Tuppel>> loosninger = lab.finnUtveiFra(j, i);
            ArrayList<Tuppel> valgtLoosning = loosninger.get(0);;
            //System.out.println(loosninger);
            if (loosninger.size() > 1){
                //velger den korteste løsningen
                for (int h=0; h<loosninger.size(); h++){
                    if (valgtLoosning.size() > loosninger.get(h).size()){
                        valgtLoosning = loosninger.get(h);
                    }
                }
            }
            System.out.println(valgtLoosning);

            for (int t=0; t<valgtLoosning.size(); t++){
                Tuppel naaTuppel = valgtLoosning.get(t);
                rut[naaTuppel.x][naaTuppel.y].endreKnapper();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Det finnes ingen utvei");
        }


    }
}
