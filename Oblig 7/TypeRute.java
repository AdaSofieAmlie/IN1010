import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TypeRute extends JButton{
    public Rute rute;
    public boolean hvit;
    public int i, j;
    public TypeRute[][] rut;
    public Labyrint lab;

    public TypeRute(int i, int j, TypeRute[][] rut, Rute ruteInn, Labyrint labInn){
        //super(ruteInn.tilString());     //For å få knappen til å vise om den er vegg eller ikke
        this.i = i;
        this.j = j;
        this.rut = rut;
        rute = ruteInn;
        lab = labInn;
    }

    public void initGUI () {
        setText(rute.tilString());
        addActionListener(new Finnutvei(i, j, lab, rut));
    }

    public void endreKnapper(){
        setText("X");
    }

    public void restart(){
        setText(rute.tilString());
    }
}
