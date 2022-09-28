import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
//GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Oblig7 {
    // 2021  Versjon 1.0
    public static void main(String[] args) {
        String filnavn = null;

        //GUI
        JFrame vindu = new JFrame("LABYRINT");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        vindu.add(panel);

        //Finne labyrint fil
        JFileChooser velgeFil = new JFileChooser();
        int resultat = velgeFil.showOpenDialog(null);
        if (resultat != JFileChooser.APPROVE_OPTION){
            System.exit(1);
        }
        File fil = velgeFil.getSelectedFile();
        Labyrint lab = null;
        try {
            lab = new Labyrint(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }

        int rader = lab.rader;
        int kolonner = lab.kolonner;


        TypeRute[][] rut = new TypeRute[rader][kolonner];

        //Lager ruter
        JPanel labyrintKnapper = new JPanel();
        labyrintKnapper.setLayout(new GridLayout(rader, kolonner));

        TypeRute rute = null;
        //lager knapper med tegn fra rutene
        for (int i=0; i<rader; i++){
            for (int j=0; j<kolonner; j++){
                if (lab.ruter[i][j] instanceof HvitRute){
                    rut[i][j] = new TypeRute(i, j, rut, lab.ruter[i][j], lab);
                } else{
                    rut[i][j] = new TypeRute(i, j, rut, lab.ruter[i][j], lab);
                }
                rut[i][j].initGUI();
                labyrintKnapper.add(rut[i][j]);
            }
        }
        panel.add(labyrintKnapper);

        

        //Avsluttning
        JButton exitKnapp = new JButton("Exit");
        class Stopper implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
                System.exit(0);
            }
        }
        exitKnapp.addActionListener(new Stopper());
        panel.add(exitKnapp);


        vindu.pack();
        vindu.setVisible(true);
    }

}
