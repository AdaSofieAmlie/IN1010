public class Node{
    private int minne, antPros;

    public Node(int min, int antP){
        this.minne = min;
        this.antPros = antP;
    }

    public int antProssesorer(){
        return this.antPros;
    }

    public boolean nokMinne(int paakrevdMinne){
        if (this.minne >= paakrevdMinne){
            return true;
        }
        return false;
    }
}
