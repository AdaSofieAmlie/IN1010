public class IntegrasjonsTest{

    public static void main(String[] args){
        Narkotisk nark = new Narkotisk("nark", 10, 15.5, 4);
        Vanlig vanlig = new Vanlig("vanlig", 20, 50.2);
        Vanedannende vaned = new Vanedannende("vaned", 110, 30.5, 3);

        System.out.println("");
        System.out.println(nark.toString());
        System.out.println(vanlig.toString());
        System.out.println(vaned.toString());
        System.out.println("");

        Lege lege = new Lege("Lege-navn");
        Spesialist spesL = new Spesialist("Spes-Lege-navn", "kontrollID-12");

        System.out.println(lege.toString());
        System.out.println(spesL.toString());
        System.out.println("");

        Militaerresepter mil = new Militaerresepter(nark, spesL, 23, 5);
        PResepter pr = new PResepter(vaned, lege, 15, 6);
        BlaaResepter blaa = new BlaaResepter(vanlig, lege, 19, 8);

        System.out.println(mil.toString());
        System.out.println("");

        System.out.println(pr.toString());
        System.out.println("");

        System.out.println(blaa.toString());
        System.out.println("");


    }
}
