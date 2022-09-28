public class Lege{
    protected String navn;
    protected static int generator = 1;
    protected int id;

    public Lege(String navn){
        this.navn = navn;
        id = generator++;
    }

    public String toString(){
        return "Lege: " + navn + " har id: " + id;
    }
}
