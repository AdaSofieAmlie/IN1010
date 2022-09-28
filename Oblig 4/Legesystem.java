

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Legesystem {
    static Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
    static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
    static Lenkeliste<Lege> leger = new Lenkeliste<Lege>();
    static Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();
    public static void main(String[] args) throws Exception {
        
        Legesystem.lesFraFil("Storeksempelfil.txt");

        Scanner input = new Scanner(System.in);

        System.out.println("\nkommando for aa... (s = stopp)");
        System.out.println("skrive ut informasjon om: p = pasienter, l = leger, le = legemidler, r = respeter");
        System.out.println("legge til: legg til pasienter, legg til leger, legg til legmidler, legg til resepter.");
        System.out.println("bruke en resept: bruk resept");
        System.out.println("skrive ut statistikk: statistikk");
        System.out.println("skrive til fil: skriv fil");
        
        String kommando = input.nextLine();

        while (!kommando.equals("s")) {
            if (kommando.equals("p")) {
                System.out.println("PASIENTER: ");

                for (Pasient p: pasienter) {
                    System.out.println(p.toString() + "\n");      
                }
            } else if(kommando.equals("l")) {

                System.out.println("LEGER: ");
                SortertLenkeliste<String> navn = new SortertLenkeliste<String>();

                for(Lege l : leger){
                    navn.leggTil(l.hentNavn());
                  }

                  for(String s : navn){
                    for(Lege l : leger){
                      if(s.equals(l.hentNavn())){
                        System.out.println(s + ": ");
                        l.hentUt();
                      }
                    }
                }

            } else if(kommando.equals("le")) {
                System.out.println("LEGEMIDLER: ");
                for (Legemiddel le: legemidler) {
                    System.out.println(le);
                }

            } else if(kommando.equals("r")) {

                System.out.println("RESEPTER: ");
                for(Resept r: resepter) {
                    System.out.println(r);
                }

            } else if(kommando.equals("legg til pasienter")) {

                System.out.println("Skriv inn navn paa pasient: ");
                Scanner info = new Scanner(System.in);
                String navn = info.nextLine();
                System.out.println("skriv inn foedselsnr: ");
                info = new Scanner(System.in);
                String fodselsnr = info.nextLine();

                pasienter.leggTil(new Pasient(navn, fodselsnr));

            } else if(kommando.equals("legg til legemidler")) {

                System.out.println("skriv inn paa format: navn,type,pris,virkestoff,styrke(type vanlig har ikke styrke).");
                Scanner info = new Scanner(System.in);

                String infoInn = info.nextLine();
                String[] biter = infoInn.split(",");

                String navn = biter[0];
                String type = biter[1];

                int pris = Integer.parseInt(biter[2]);
                double virkestoff = Double.parseDouble(biter[3]);

                if(!type.contains("vanlig") || !type.contains("vanedannende") || !type.contains("narkotisk")){

                    System.out.println("ikke godkjent type. Godkjente typer er vanlig, vanedannende, narkotisk. \nSkriv inn type: ");
                    info = new Scanner(System.in);
                    type = info.nextLine();
                }

                if(type.equals("vanlig")){

                    Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
                    legemidler.leggTil(vanlig);
                    System.out.println("lagt til vanlig");

                } else if(type.equals("vanedannende")){

                    int styrke = Integer.parseInt(biter[4]);
                    Vanedannende vane = new Vanedannende(navn, pris, virkestoff, styrke);
                    legemidler.leggTil(vane);
                    System.out.println("lagt til vane");

                } else if(type.equals("narkotisk")){
                    int styrke = Integer.parseInt(biter[4]);
                    Narkotisk narko = new Narkotisk(navn, pris, virkestoff, styrke);
                    legemidler.leggTil(narko);
                    System.out.println("lagt til narko");

                }
            } else if(kommando.equals("legg til leger")) {

                System.out.println("skriv inn paa format: navn,kontrollid(0 hvis vanlig lege).");

                Scanner info = new Scanner(System.in);
                String infoInn = info.nextLine();
                String[] biter = infoInn.split(",");

                String navn = biter[0];
                String kontrollId = biter[1];

                if(kontrollId.equals("0")){
                    Lege lege = new Lege(navn);
                    leger.leggTil(lege);
                    System.out.println("lagt til vanlig");

                } else {

                    Spesialist sp = new Spesialist(navn, kontrollId);
                    leger.leggTil(sp);
                    System.out.println("lagt til spesialist");

                }
            } else if(kommando.equals("legg til resepter")) {

                System.out.println("skriv inn type resept: ");

                Scanner info = new Scanner(System.in);

                String typeResept = info.nextLine();

                if(typeResept.contains("hvit") || typeResept.contains("blaa") || typeResept.contains("militaer") || typeResept.contains("p")){
                    System.out.println("godkjent type");

                } else {

                    System.out.println("ikke godkjent type. Godkjente typer er hvit, blaa, militaer og p. \nSkriv inn type: ");
                    info = new Scanner(System.in);
                    typeResept = info.nextLine();
                }

                System.out.println("\nALLE LEGER");
                System.out.println(leger.toString());

                System.out.println("navn paa lege: ");
                info = new Scanner(System.in);
                String legeNavn = info.nextLine();

                Lege lege = null;

                //sjekker om legen eksisterer fra foer
                if(!Legesystem.finnesLege(legeNavn)){
                    System.out.println("legen eksisterer ikke");

                } else{

                    lege = Legesystem.finnLege(legeNavn);

                    if (lege instanceof Spesialist) {

                        System.out.println("Kontroll id: ");
                        info = new Scanner(System.in);
                        String kontrollId = info.nextLine();
                        Spesialist spes = new Spesialist(legeNavn, kontrollId);
                        leger.leggTil(spes);

                    } else {

                        lege = new Lege(legeNavn);
                        leger.leggTil(lege);

                    }
                }
                System.out.println("\nALLE LEGEMIDLER");
                System.out.println(legemidler.toString());

                System.out.println("id paa legemiddelet: ");
                info = new Scanner(System.in);
                int lmId = info.nextInt();

                Legemiddel lm = null;

                //sjekker om id er gyldig
                if(lmId > legemidler.stoerrelse()){

                    System.out.println("ugyldig id paa legemiddel, finnes ikke");
                    info = new Scanner(System.in);
                    lmId = info.nextInt();

                } else {
                    lm = Legesystem.finnLm(lmId);
                }

                System.out.println("\nALLE PASIENTER");
                System.out.println(pasienter.toString());

                System.out.println("pasient id: ");

                info = new Scanner(System.in);

                int pasientId2 = info.nextInt();

                Pasient pasient = null;

                //sjekker om id er gyldig

                if(pasientId2 > pasienter.stoerrelse()){

                    System.out.println("ugyldig id");
                    info = new Scanner(System.in);
                    pasientId2 = info.nextInt();

                } else {
                    pasient = Legesystem.finnPasient(pasientId2);
                }

                System.out.println("har din type resept reit? (p har ikke reit)");
                info = new Scanner(System.in);
                String svar = info.nextLine();

                if(svar.equals("ja")){
                    System.out.println("skriv ant reit: ");
                    info = new Scanner(System.in);
                    int reit = info.nextInt();

                    try{
                        System.out.println("type resept " + typeResept);
                        if(typeResept.equalsIgnoreCase("hvit")){
                            //oppretter et legemiddel avhengig av hvilken type det er, og setter alt inn i resept
                            if(lm instanceof Narkotisk){

                                Narkotisk legemiddel = Legesystem.narkotiskLm(lm);  
                                Resept nyResept = lege.skrivHvitResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);                   

                            } else if(lm instanceof Vanedannende){

                                Vanedannende legemiddel = Legesystem.vanedannendeLm(lm);
                                Resept nyResept = lege.skrivHvitResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);    

                            } else if(lm instanceof Vanlig){

                                Vanlig legemiddel = Legesystem.vanligLm(lm);
                                Resept nyResept = lege.skrivHvitResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);    
                        }
                        } else if(typeResept.equalsIgnoreCase("blaa")){

                            if(lm instanceof Narkotisk){

                                Narkotisk legemiddel = Legesystem.narkotiskLm(lm);  
                                Resept nyResept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept); 
                                pasient.leggTil(nyResept);                   

                            } else if(lm instanceof Vanedannende){

                                Vanedannende legemiddel = Legesystem.vanedannendeLm(lm);
                                Resept nyResept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);    

                            } else if(lm instanceof Vanlig){

                                Vanlig legemiddel = Legesystem.vanligLm(lm);
                                Resept nyResept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);    

                            }

                        } else if(typeResept.equalsIgnoreCase("militaer")){
                            if(lm instanceof Narkotisk){

                                Narkotisk legemiddel = Legesystem.narkotiskLm(lm);  
                                Resept nyResept = lege.skrivMillitaerResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);                   

                            } else if(lm instanceof Vanedannende){

                                Vanedannende legemiddel = Legesystem.vanedannendeLm(lm);
                                Resept nyResept = lege.skrivMillitaerResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);    
                                System.out.println("lagt til militaer, vanedannende");

                            } else if(lm instanceof Vanlig){

                                Vanlig legemiddel = Legesystem.vanligLm(lm);
                                Resept nyResept = lege.skrivMillitaerResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);    
                            }
                        }
                    } catch(UlovligUtskrift e){
                        System.out.println("legen skriver ut ulovlig\n");
                    }
                } else {
                    try{
                        if(typeResept.equals("p")){
                            if(lm instanceof Narkotisk){

                                Narkotisk legemiddel = Legesystem.narkotiskLm(lm);  
                                Resept nyResept = lege.skrivPResept(legemiddel, pasient);
                                resepter.leggTil(nyResept);                

                            } else if(lm instanceof Vanedannende){

                                Vanedannende legemiddel = Legesystem.vanedannendeLm(lm);
                                Resept nyResept = lege.skrivPResept(legemiddel, pasient);
                                resepter.leggTil(nyResept);

                            } else if(lm instanceof Vanlig){

                                Vanlig legemiddel = Legesystem.vanligLm(lm);
                                Resept nyResept = lege.skrivPResept(legemiddel, pasient);
                                resepter.leggTil(nyResept);
                            }
                        }
                    }catch(UlovligUtskrift e){
                        System.out.println("legen skriver ut ulovlig");
                    }
                }

            } else if(kommando.equals("bruk resept")){

                System.out.println("\nOVERSIKT OVER ALLE PASIENTER: ");
                System.out.println(pasienter.toString());
                System.out.println("\nhvilken pasient vil du se resepter for? (skriv id til pasient)");

                Scanner info = new Scanner(System.in);
                int id = info.nextInt();

                if(!Legesystem.finnesPasient(id)){ //dersom pasienten ikke finnes
                    System.out.println("\nugyldig id / pasient finnes ikke. Gyldig id er fra 0 til " + (pasienter.stoerrelse() -1));

                } else {
                    Pasient pasient = pasienter.hent(id - 1); //bruker hent(int pos) fra Lenkeliste
                    System.out.println("\nvalgt pasient: " + pasient.hentNavn() + " (fnr " + pasient.hentFodselsnr() + ")");

                    Stabel<Resept> stabelResepter = pasient.hentUt(); //vi lager en ny stabel med resepter
                   
                    System.out.println("\n stabel med resepter for denne pasienten");
                    System.out.println(stabelResepter);

                    for(Pasient p : pasienter){
                        for(Resept r : resepter){
                            if(p.hentId() == r.hentPasientId()){
                                stabelResepter.leggTil(r);
                            }
                        }
                    }

                    System.out.println("\nhvilken resept vil du bruke? (velg ved aa taste inn id)");

                    info = new Scanner(System.in);
                    int valgtReseptId = info.nextInt();

                    for(Resept resept : stabelResepter){
                        System.out.println(resept.hentID() + ": " + resept.hentLegemiddel().hentNavn() + " (" + resept.hentReit() + " reit)");
                    }

                    Resept valgtRes = Legesystem.finnResept(stabelResepter, valgtReseptId);

                    if(valgtRes.bruk()){//dersom resepten kan brukes
                        System.out.println("\nbrukte resept paa " + valgtRes.hentLegemiddel().hentNavn() + ". antall gjenvaerende reit: " + valgtRes.hentReit());
                    } else {
                        System.out.println("\nKunne ikke bruke resept paa " + valgtRes.hentLegemiddel().hentNavn() + " (ingen gjenvaerende reit).");
                    }
                }

            } else if (kommando.equals("statistikk")) {

                int antVane = 0;
                int antNarko = 0;

                System.out.println("\nALLE RESEPTER:");
                System.out.println(resepter);

                System.out.println("\nLeger som har skrevet ut narkotisk resept og antall respter:");

                SortertLenkeliste<String> lNavn = new SortertLenkeliste<String>();

                for(Lege l : leger){
                    lNavn.leggTil(l.hentNavn());
                  }

                  for(String s : lNavn){
                    for(Lege l : leger){
                        int antUtskrevneNarko = 0;
                      if(l instanceof Spesialist){
                          if(s.equals(l.hentNavn())){
                              for(Resept r : l.hentUt()){
                                  if(r.hentLegemiddel() instanceof Narkotisk){
                                      antUtskrevneNarko++;
                                  }
                              }
                              if(antUtskrevneNarko > 0){
                                  System.out.println("\n " + s + " har skrevet ut " + antUtskrevneNarko);
                              }
                          }
                      }
                    }
                }
                
                for(Lege lege : leger){
                    for (Resept resept : lege.hentUt()){
                        if(resept.hentLegemiddel() instanceof Vanedannende){
                            antVane++;
                    }
                }
            }

                for(Lege leg : leger){
                    for (Resept resept : leg.hentUt()){
                        System.out.println(resept.hentLegemiddel());
                        if(resept.hentLegemiddel() instanceof Narkotisk){
                            antNarko++;
                        }
                    }
                }
    
                System.out.println("\n antall utskrevede narkotiske resepter: " + antNarko);
                System.out.println("\n antall utskrevede vanedannende resepter: " + antVane);
                System.out.println("\nnavnene paa alle pasienter som har minst en gyldig resept paa narkotiske legemidler, og for disse, antallet per pasient.");

                for(Pasient pasient : pasienter){
                    int narko = 0;
                    for (Resept resept : pasient.hentUt()){
                        if(resept.hentLegemiddel() instanceof Narkotisk){
                            narko++;
                        }
                    }

                    //PROBLEM: VARIABELEN NARKO SKRIVER ALLTID UT EN VERDI STOERRE ENN DEN EGENTLIG SKAL
                    if(narko > 0){
                        System.out.println("\npasienten: " + pasient.hentNavn() + ", antallet narkotiske legemidler for denne pasienten: " + narko);
                    }
                }
            } else if (kommando.equals("skriv fil")) {

                FileWriter writer = new FileWriter("nyfil.txt");

                writer.write("# Pasienter (navn, fnr)");

                for (Pasient p: pasienter) {
                    writer.write(p + System.lineSeparator());
                }

                writer.write("# Legemidler (navn,type,pris,virkestoff,[styrke])");

                for (Legemiddel p: legemidler) {
                    writer.write(p + System.lineSeparator());
                }

                writer.write("# Leger (navn,kontrollid / 0 hvis vanlig lege)");

                for (Lege p: leger) {
                    writer.write(p + System.lineSeparator());
                }

                writer.write("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");

                for (Resept p: resepter) {
                    writer.write(p + System.lineSeparator());
                }
                writer.close();
            }

            input = new Scanner(System.in);

            System.out.println("\nkommando for aa... (s = stopp)");
            System.out.println("skrive ut informasjon om: p = pasienter, l = leger, le = legemidler, r = respeter");
            System.out.println("legge til: legg til pasienter, legg til leger, legg til legmidler, legg til resepter.");
            System.out.println("bruke en resept: bruk resept");
            System.out.println("skrive ut statistikk: statistikk");
            System.out.println("skrive til fil: skriv fil");

            kommando = input.nextLine();

        }
    }

    public static Legemiddel finnLm(int legemiddelnr) {
        Legemiddel finnLm = null;
        for (Legemiddel lm : legemidler) {
            if (lm.hentID() == legemiddelnr) {
                finnLm = lm;
            }
        }
        return finnLm;
    }

    // Sjekker om lege ved gitt navn finnes
    public static boolean finnesLege(String legeNavn) {
        boolean finnes = false;

        for(Lege l : leger){
            if(l.hentNavn().compareTo(legeNavn) == 0){
                finnes = true;
              }
            }
            return finnes;
        }

    //finner en lege med kun navn paa legen
    private static Lege finnLege(String legeNavn){
        Lege lege = null;
        for(Lege l : leger){
            if(l.hentNavn().equals(legeNavn)){
                lege = l;
            }
        }
        return lege;
    }

    private static Pasient finnPasient(int id){
        Pasient pasient = null;

        for(Pasient pas : pasienter){
            if(pas.hentId() == id){
                pasient = pas;
            }
        }
        return pasient;
    }

    private static boolean finnesPasient(int id){
        boolean finnes = false;

        for(Pasient p : pasienter){
            if(p.hentId() == id){
                finnes = true;
            }
        }
        return finnes;
    }

    //finner resept ved aa kjoere gjennom stabelen
    private static Resept finnResept(Stabel<Resept> stabel, int id){
        Resept res = null;

        for(Resept resept : stabel){
            if(resept.hentID() == id){
                res = resept;
            }
        }
        return res;
    }

    public static Narkotisk narkotiskLm(Legemiddel lm){
        Narkotisk riktig = (Narkotisk) lm; //caster fra Legemiddel til Narkotisk
        Narkotisk legemiddel = new Narkotisk(riktig.hentNavn(), riktig.hentPris(), riktig.hentVirkestoff(), riktig.hentNarkotiskStyrke());

        return legemiddel;
    }

    public static Vanedannende vanedannendeLm(Legemiddel lm){
        Vanedannende riktig = (Vanedannende) lm;
        Vanedannende legemiddel = new Vanedannende(riktig.hentNavn(), riktig.hentPris(), riktig.hentVirkestoff(), riktig.hentVanedannendeStyrke());

        return legemiddel;
    }

    public static Vanlig vanligLm(Legemiddel lm){
        Vanlig riktig = (Vanlig) lm;
        Vanlig legemiddel = new Vanlig(riktig.hentNavn(), riktig.hentPris(), riktig.hentVirkestoff());
        
        return legemiddel;
    }

    public static void lesFraFil(String filnavn) throws Exception{

        Scanner fil = new Scanner(new File(filnavn));
        String linje = fil.nextLine();

        while(fil.hasNextLine()){
            //System.out.println(linje + linje.contains("# Legemidler"));

            if(linje.contains("# Pasienter")){

                linje = fil.nextLine();
                String[] biter = linje.split(",");

                while(!biter[0].contains("#")){

                    pasienter.leggTil(new Pasient(biter[0], biter[1]));
                    //System.out.println(linje);
                    linje = fil.nextLine();
                    biter = linje.split(",");
                }

            } else if(linje.contains("# Legemidler")){

                linje = fil.nextLine();

                String[] biter = linje.split(",");

                while(!biter[0].contains("#")){
                    String navn = null;
                    String type = null;
                    int pris = 0;
                    double virkestoff = 0;

                    try {
                        if (biter[2].contains(".")) {
                            Double biten = Double.parseDouble(biter[2]);
                            pris = (int) Math.round(biten);
                        }
                        navn = biter[0];
                        type = biter[1];
                        pris = Integer.parseInt(biter[2]);
                        virkestoff = Double.parseDouble(biter[3]);
                    } catch (NumberFormatException e) {
                        System.out.println("fant number format exception.");
                    }
                    if(type.equals("vanlig")){

                        Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
                        legemidler.leggTil(vanlig);
                        System.out.println("lagt til vanlig");

                    } else if(type.equals("vanedannende")){

                        int styrke = Integer.parseInt(biter[4]);
                        Vanedannende vane = new Vanedannende(navn, pris, virkestoff, styrke);
                        legemidler.leggTil(vane);
                        System.out.println("lagt til vane");

                    } else if(type.equals("narkotisk")){
                        int styrke = Integer.parseInt(biter[4]);
                        Narkotisk narko = new Narkotisk(navn, pris, virkestoff, styrke);
                        legemidler.leggTil(narko);
                        System.out.println("lagt til narko");
                    }
                    linje = fil.nextLine();
                    biter = linje.split(",");
                }

            } else if(linje.contains("# Leger")){

                linje = fil.nextLine();
                String[] biter = linje.split(",");

                while(!biter[0].contains("#")){

                    String navn = biter[0];
                    String kontrollId = biter[1];

                    if(kontrollId.equals("0")){

                        Lege lege = new Lege(navn);
                        leger.leggTil(lege);

                    } else {

                        Spesialist sp = new Spesialist(navn, kontrollId);
                        leger.leggTil(sp);
                    }

                    linje = fil.nextLine();
                    biter = linje.split(",");

                }

            } else if(linje.contains("# Resepter")){

                linje = fil.nextLine();
                String[] biter = linje.split(",");

                while(fil.hasNextLine()){

                    int legemiddelNr = Integer.parseInt(biter[0]);
                    String lNavn = biter[1];
                    int pasientId = Integer.parseInt(biter[2]);
                    String typeResept = biter[3];

                    //finner legemiddelet vha id
                    Legemiddel riktigLm = null;

                    for(Legemiddel lm : legemidler){
                        if(lm.hentID() == legemiddelNr){
                            riktigLm = lm;
                        }
                    }

                    if (riktigLm == null) {
                        linje = fil.nextLine();
                        biter = linje.split(",");
                        continue;
                    }


                    //finner lege
                    Lege riktigLege = null;
                    for(Lege l : leger){
                        if(l.hentNavn().equals(lNavn)){
                            riktigLege = l;
                        }
                    }

                    if (riktigLege == null) {
                        linje = fil.nextLine();
                        biter = linje.split(",");
                        continue;
                    }

                    //oppretter Lege objekt
                    Lege lege = new Lege(riktigLege.hentNavn());

                    //finner riktig pasient
                    Pasient riktigPasient = null;

                    for(Pasient p : pasienter){
                        if(p.hentId() == pasientId){
                            riktigPasient = p;
                        }
                    }

                    if (riktigPasient == null) {
                        linje = fil.nextLine();
                        biter = linje.split(",");
                        continue;
                    }

                    //oppretter Pasient
                    Pasient pasient = new Pasient(riktigPasient.hentNavn(), riktigPasient.hentFodselsnr());

                    try{
                        if(typeResept.equals("hvit")){
                            //oppretter et legemiddel avhengig av hvilken type det er, og setter alt inn i resept
                        if(riktigLm instanceof Narkotisk){

                            Narkotisk legemiddel = Legesystem.narkotiskLm(riktigLm);  
                            int reit = Integer.parseInt(biter[4]);
                            Resept nyResept = lege.skrivHvitResept(legemiddel, pasient, reit);
                            resepter.leggTil(nyResept);
                            pasient.leggTil(nyResept); 
                            System.out.println("lagt til hvit");               

                        } else if(riktigLm instanceof Vanedannende){

                            Vanedannende legemiddel = Legesystem.vanedannendeLm(riktigLm);
                            int reit = Integer.parseInt(biter[4]);
                            Resept nyResept = lege.skrivHvitResept(legemiddel, pasient, reit);
                            resepter.leggTil(nyResept);
                            pasient.leggTil(nyResept);   
                            System.out.println("lagt til hvit");               

                        } else if(riktigLm instanceof Vanlig){

                            Vanlig legemiddel = Legesystem.vanligLm(riktigLm);
                            int reit = Integer.parseInt(biter[4]);
                            Resept nyResept = lege.skrivHvitResept(legemiddel, pasient, reit);
                            resepter.leggTil(nyResept);
                            pasient.leggTil(nyResept); 
                            System.out.println("lagt til hvit");               

                        }
                        } else if(typeResept.equals("blaa")){

                            if(riktigLm instanceof Narkotisk){

                                Narkotisk legemiddel = Legesystem.narkotiskLm(riktigLm);  
                                int reit = Integer.parseInt(biter[4]);
                                Resept nyResept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);  
                                System.out.println("lagt til blaa");               

                            } else if(riktigLm instanceof Vanedannende){

                                Vanedannende legemiddel = Legesystem.vanedannendeLm(riktigLm);
                                int reit = Integer.parseInt(biter[4]);
                                Resept nyResept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);  
                                System.out.println("lagt til blaa");               

                            } else if(riktigLm instanceof Vanlig){

                                Vanlig legemiddel = Legesystem.vanligLm(riktigLm);
                                int reit = Integer.parseInt(biter[4]);
                                Resept nyResept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);
                                System.out.println("lagt til blaa");               

                            }
                        } else if(typeResept.contains("militaer")){

                            if(riktigLm instanceof Narkotisk){

                                Narkotisk legemiddel = Legesystem.narkotiskLm(riktigLm);  
                                int reit = Integer.parseInt(biter[4]);
                                Resept nyResept = lege.skrivMillitaerResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept); 
                                pasient.leggTil(nyResept);  
                                System.out.println("lagt til mili");                 

                            } else if(riktigLm instanceof Vanedannende){

                                Vanedannende legemiddel = Legesystem.vanedannendeLm(riktigLm);
                                int reit = Integer.parseInt(biter[4]);
                                Resept nyResept = lege.skrivMillitaerResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);  
                                System.out.println("lagt til mili");                 

                            } else if(riktigLm instanceof Vanlig){

                                Vanlig legemiddel = Legesystem.vanligLm(riktigLm);
                                int reit = Integer.parseInt(biter[4]);
                                Resept nyResept = lege.skrivMillitaerResept(legemiddel, pasient, reit);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);   
                                System.out.println("lagt til mili");                 

                            }

                        } else if(typeResept.contains("p")){

                            if(riktigLm instanceof Narkotisk){

                                Narkotisk legemiddel = Legesystem.narkotiskLm(riktigLm);  
                                //int reit = Integer.parseInt(biter[3]);
                                Resept nyResept = lege.skrivPResept(legemiddel, pasient);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);  
                                System.out.println("lagt til p");                 

                            } else if(riktigLm instanceof Vanedannende){

                                Vanedannende legemiddel = Legesystem.vanedannendeLm(riktigLm);
                                //int reit = Integer.parseInt(biter[3]);
                                
                                Resept nyResept = lege.skrivPResept(legemiddel, pasient);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept);  
                                System.out.println("lagt til p");                 

                            } else if(riktigLm instanceof Vanlig){

                                Vanlig legemiddel = Legesystem.vanligLm(riktigLm);
                                Resept nyResept = lege.skrivPResept(legemiddel, pasient);
                                resepter.leggTil(nyResept);
                                pasient.leggTil(nyResept); 
                                System.out.println("lagt til p");                 

                            }
                        }
                    } catch(UlovligUtskrift e){
                        System.out.println("vanlig lege som proever aa skrive ut ulovlig");
                    }
                    linje = fil.nextLine();
                    biter = linje.split(",");
                }
            }
        }
    }
}
