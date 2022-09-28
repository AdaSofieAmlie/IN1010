

public class Integrasjonstest {
    
    public static void main(String[] args) {

        Vanlig ibux = new Vanlig("Ibux", 41, 2.0); //@param navn, pris, mg virkestoff
        Vanedannende nesespray = new Vanedannende("Nesespray", 150, 30.5, 2); //@param navn, pris, mg virkestoff, styrke
        Narkotisk piller = new Narkotisk("Piller", 200, 20.5, 4);

        Lege Lege1 = new Lege("Mai Berg"); 
        Lege Lege2 = new Lege("Ida");
        Spesialist Lege3 = new Spesialist("Siri", 10); //kontrollID: 10

        BlaaResept ibuxResept = new BlaaResept(ibux, Lege1, 123, 4); //@param legemiddel, lege, paient id, reit
        MResept nesesprayResept = new MResept(nesespray, Lege2, 456, 2);
        PResept pilleResept = new PResept(piller, Lege3, 789); //@param legemiddel, lege, paient id

        //skriver ut etter opprettelse (datastruktur herifra)
        System.out.println(ibuxResept.toString()); //pris er da bli 41/4 = 10,25 i int = 10kr
        System.out.println(nesesprayResept.toString()); //pris er da bli 0 kr
        System.out.println(pilleResept.toString()); //pris er da bli 100 - 108 = 92 kr

        //ibux blir tatt ut 5 ganger. Reit går fra 4-0. 5 gangen noen prøver å ta ut er den ugyldig.
        for (int i = 0; i < 5; i++) {
            System.out.println(TaUtLegemiddel(ibuxResept));
        }
        System.out.println(ibuxResept);

        //kontrollerer at siri/lege3 som har skrevet ut pilleResept, med narkotiske piller har ID til det.
        System.out.println(KontrollerID(Lege3));

        //konotroller at ida/Lege2 ikek kan skrive ut narkotiske legemidler
        System.out.println(KontrollerID(Lege2));

    }

    public static String KontrollerID(Lege lege) {
        if (lege.getClass() == Spesialist.class) {
            return lege.hentNavn() + " kan skrive ut narkotiske legemidler.";
        } else {
            return lege.hentNavn() + " kan ikke skrive ut narkotiske legemidler.";
        }
    }

    public static String TaUtLegemiddel(Resept resept) {
        if (resept.hentReit() > 0) {
            resept.bruk();
            return "Resept tatt ut.";
        }
       return "Resept ugyldig.";
    }
}

