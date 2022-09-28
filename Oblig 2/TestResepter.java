public class TestResepter{

    public static void main(String[] args){
        Narkotisk nark = new Narkotisk("nark", 10, 15.5, 4);
        Vanlig vanlig = new Vanlig("vanlig", 20, 50.2);
        Vanedannende vaned = new Vanedannende("vaned", 110, 30.5, 3);

        Lege lege = new Lege("Lege-navn");
        //Spesialist spesL = new Spesialist("Spes-Lege-navn", "kontrollID-12");

        Militaerresepter mil = new Militaerresepter(vanlig, lege, 23, 5);
        System.out.println(mil.toString());
        System.out.println("farge: " + mil.farge());        //HVIT
        System.out.println("pris: " + mil.prisAaBetale());       //O
        System.out.println("");

        PResepter pr = new PResepter(vaned, lege, 15, 6);
        System.out.println(pr.toString());
        System.out.println("farge: " + pr.farge()); //Hvit
        System.out.println("pris: " + pr.prisAaBetale());    //2
        System.out.println("");

        BlaaResepter blaa = new BlaaResepter(vanlig, lege, 19, 8);
        System.out.println(blaa.toString());
        System.out.println("farge: " + blaa.farge());   //Blaa
        System.out.println("pris: " + blaa.prisAaBetale());  //5
        System.out.println("");
    }
}
