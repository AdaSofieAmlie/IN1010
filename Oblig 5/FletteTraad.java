import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FletteTraad implements Runnable{
    public Monitor monitor;
    public BeholderHM beholder;

    public FletteTraad(Monitor monInn, BeholderHM beholderInn){
        monitor = monInn;
        beholder = beholderInn;
    }

    @Override
    public void run() {
        //Start fletting
        boolean mulig = monitor.flettM(beholder);
        while (mulig){  //Fortsetter å flette hvis fortsatt mulig
            mulig = monitor.flettM(beholder);
        }
    }
}
