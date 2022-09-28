
public class Tuppel{
    public int x, y;

    public Tuppel(int xInn, int yInn){
        x = xInn;
        y = yInn;
    }

    public String toString(){
        String ut = "";
        ut += "Rad: " + x + ".Kolonne: " + y;
        ut += "\n";
        return ut;
    }
}
