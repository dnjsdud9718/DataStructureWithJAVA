public class Node {
    private int parent, rank;
    public Node(int p, int r){
        parent = p; rank = r;
    }
    public void setParent(int p){parent = p;}
    public void setRank(int r){rank = r;}
    public int getParent(){return parent;}
    public int getRank(){return rank;}
}
