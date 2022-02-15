public class UnionFind {
    private Node [] a;
    public UnionFind(int N){
        a = new Node[N];
        for(int i = 0; i<N;i++) a[i] =  new Node(i,1);
    }
    public int find(int i){
        if(a[i].getParent()!=i) a[i].setParent(find(a[i].getParent()));
        return a[i].getParent();
    }
    public void union(int i, int j){
        int iroot = find(i);
        int jroot = find(j);
        if(iroot == jroot) return;
        if(a[iroot].getRank() > a[jroot].getRank()) a[jroot].setParent(iroot);
        else if(a[iroot].getRank() < a[jroot].getRank()) a[iroot].setParent(jroot);
        else{
            a[jroot].setParent(iroot);
            a[iroot].setRank(a[i].getRank()+1);
        }
    }
    public boolean isConnected(int i, int j){
        return find(i) == find(j);
    }
}
