import java.util.ArrayList;

public class Rack{
    private int antP, antN;
    private ArrayList<Node> listeAvNoder;

    public Rack(){
        listeAvNoder = new ArrayList<Node>();
    }

    public void settInn(Node node){
        listeAvNoder.add(node);
    }

    public int getAntNoder(){
        return listeAvNoder.size();
    }

    public int antProssesorer(){
        antP = 0;
        for (int i = 0; i < listeAvNoder.size(); i++){
            antP += listeAvNoder.get(i).antProssesorer();
        }
        return antP;
    }

    public int noderMedNokMinne(int paakrevdMinne){
        antN = 0;
        for (Node n : listeAvNoder){
            if (n.nokMinne(paakrevdMinne)){
                antN += 1;
            }
        }
        //System.out.print(antN);
        return antN;
    }

}
