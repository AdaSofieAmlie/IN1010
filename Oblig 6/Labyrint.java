import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;


public class Labyrint{
    public File fil;
    public Scanner leser;
    public Rute[][] ruter;
    public int rader, kolonner;
    public ArrayList<ArrayList<Tuppel>> utveier;

    public Labyrint(File filInn) throws FileNotFoundException{

        //Starter med lesing fra fil
        fil = filInn;
        //Lager scanner
        leser = new Scanner(fil);

        //Leser første linje
        String linjeEn = leser.nextLine();
        String[] radKol = linjeEn.split(" ");

        // ant ruter og ant Kolonner
        rader = Integer.parseInt(radKol[0]);
        System.out.println(rader);
        kolonner = Integer.parseInt(radKol[1]);
        System.out.println(kolonner);

        //To dimensjonal array:
        ruter = new Rute[rader][kolonner];

        for(int x=0; x<rader; x++){
            char[] tegnPaaNesteLinje = leser.nextLine().toCharArray();
            for (int y=0; y<kolonner; y++){
                //System.out.println(nesteLinje.charAt(i));

                if (tegnPaaNesteLinje[y] == '#'){
                    Rute sortRute = new SortRute(x,y);
                    ruter[x][y] = sortRute;
                } else if (tegnPaaNesteLinje[y] == '.'){
                    //Om en min en rute innafor rutenettet
                    if ((x > 0) && (y > 0) && (x < (ruter.length-1)) && (y < (ruter[x].length-1))){
                        Rute hvitRute = new HvitRute(x,y);
                        ruter[x][y] = hvitRute;
                    } else{ //Om på ytterste lag av ruter
                        HvitRute aapning = new Aapning(x,y);
                        //System.out.println("Ny aapning");
                        ruter[x][y] = aapning;
                    }
                } else{
                    System.out.println("Labyrint i feil format");
                }

            }
        }

        //Setter nabo referanser
        for(int x=0; x<rader; x++){
            for (int y=0; y<kolonner; y++){
                finnNabo(x,y);
            }
        }

        //Sender inn labyrint objektet til alle apnings objekter slik at de
        //kan nå attaylisten ArrayList<ArrayList<Tuppel>> utveier
        for(int x=0; x<rader; x++){
            for (int y=0; y<kolonner; y++){
                if (ruter[x][y] instanceof Aapning){
                    ruter[x][y].giLabyrint(this);
                }
            }
        }

        //Printer ut labyrinten til bruker
        System.out.println(toString());
    }

    public ArrayList<ArrayList<Tuppel>> finnUtveiFra(int kolInn, int radInn){
        //Lager array
        utveier = new ArrayList<ArrayList<Tuppel>>();
        //Finner utveier
        ruter[radInn][kolInn].finnUtVei();
        return utveier;
    }


    //Finner fram naboer til ruten
    public void finnNabo(int xInn, int yInn){
        //Hoveruta
        Rute hoved = ruter[xInn][yInn];
        //Nabo rutene
        Rute nord = null;
        Rute sor = null;
        Rute oest = null;
        Rute vest = null;

        //If sjekker for å passe på at man ikke gpr uttafor labyrinten
        if (xInn-1 >= 0){
            nord = ruter[xInn-1][yInn];
        }
        if (xInn+1 < rader ){
            sor = ruter[xInn+1][yInn];
        }
        if(yInn+1 < kolonner){
            oest = ruter[xInn][yInn+1];
        }
        if(yInn-1 >= 0){
            vest = ruter[xInn][yInn-1];
        }

        //Sender naboene til ruter objektet
        hoved.settNaboer(nord, sor, oest, vest);
    }

    //ToString som skriver ut labyrinten på en fin måte
    public String toString(){
        String ut = "";
        int deltPaaToOgMinusTre = (kolonner/2) - 3;

        //Jeg ville ha en overskrift som var tilpasset alle labyrintene, derfor har jeg de ekstra for løkkene
        for (int g=0; g<deltPaaToOgMinusTre; g++){
            ut += "-";
        }
        ut += "LABYRINT";
        for (int g=0; g<deltPaaToOgMinusTre; g++){
            ut += "-";
        }
        ut += "\n";

        //Skriver ut labyrinten
        for(int x=0; x<rader; x++){
            for (int y=0; y<kolonner; y++){
                ut += ruter[x][y].tilTegn();
            }
            ut += "\n";
        }
        return ut;
    }


}
