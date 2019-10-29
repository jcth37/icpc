import java.util.ArrayList;
public class Node {//for squawk problem
    public ArrayList<Node> connects;
    public int inSquawks;
    public int outSquawks;
    public boolean wasSqakdAtPrev = false;
    public boolean wasSqakdAtNow = false;
    public Node(){
        connects = new ArrayList<>();
        inSquawks = 0;
        outSquawks = 0;
    }

    public void receive(int i){
        inSquawks += i;
    }

    public void addConnect(Node n){
        connects.add(n);
    }

    public void oneSecondPasses(){
        wasSqakdAtPrev = wasSqakdAtNow;
        wasSqakdAtNow = false;
        outSquawks = inSquawks;
        inSquawks = 0;
    }

}
