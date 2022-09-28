public class TestLegemidel{

    public static void main(String[] args){
        Narkotisk nark = new Narkotisk("nark", 10, 15.5, 4);
        Vanlig vanlig = new Vanlig("vanlig", 20, 50.2);
        Vanedannende vaned = new Vanedannende("vaned", 30, 30.5, 3);

        System.out.println(nark.hentID());    //1
        System.out.println(nark.hentNavn());    //nark
        System.out.println(nark.hentPris());    //10
        System.out.println(nark.hentVirkestoff());    //15.5
        nark.settNyPris(100);
        System.out.println(nark.hentPris());    //100
        System.out.println(nark.hentNarkotiskStyrke());    //4
        System.out.println(nark.toString());
        System.out.println("");

        System.out.println(vanlig.hentID());    //2
        System.out.println(vanlig.hentNavn());    //vanlig
        System.out.println(vanlig.hentPris());    //20
        System.out.println(vanlig.hentVirkestoff());    //50.2
        vanlig.settNyPris(50);
        System.out.println(vanlig.hentPris());    //50
        System.out.println(vanlig.toString());
        System.out.println("");

        System.out.println(vaned.hentID());    //3
        System.out.println(vaned.hentNavn());    //vaned
        System.out.println(vaned.hentPris());    //30
        System.out.println(vaned.hentVirkestoff());    //30.5
        vaned.settNyPris(40);
        System.out.println(vaned.hentPris());    //40
        System.out.println(vaned.hentVanedannendeStyrke());    //3
        System.out.println(vaned.toString());
        System.out.println("");
    }

}
