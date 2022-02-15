/* disjoint: 교집합이 공집합인 두 집합 */
public class DisjointSet {
    private static class Node{
        private int parent; 
        private int rank; //component에 대표노드를 택하기 위해 사용
        public Node(int p, int r){parent=p;rank=r;} //초기: 부모는 자기자신, 랭크는 1
        public int getParent(){return parent;}
        public int getRank(){return rank;}
        public void setParent(int newParent){this.parent=newParent;}
        public void setRank(int newRank){this.rank=newRank;}
    }
    protected Node[] a;
    public DisjointSet(Node[] iarray){a = iarray;}
    protected int find(int i){ //경로 압축: 다음번에 find()를 빠르게 해준다. //logN
        if(i != a[i].getParent()) a[i].setParent(find(a[i].getParent())); //parent가 자기자신이 아니면
        return a[i].getParent();
    }
    public void union(int  i, int j){
        int iroot = find(i);
        int jroot = find(j);
        if(iroot == jroot) return;
        if(a[iroot].getRank() > a[jroot].getRank()) a[jroot].setParent(iroot);
        else if(a[iroot].getRank() < a[jroot].getRank()) a[iroot].setParent(jroot);
        else{
            a[jroot].setParent(iroot);
            int t = a[iroot].getRank()+1;
            a[iroot].setRank(t);
        }
    }
    public void printAll(){
        for(int i = 0; i<a.length;i++) System.out.print(a[i].getParent()+" ");
        System.out.println(" ");
    }
    public static void main(String [] args){
        Node [] iarray = new Node[10];
        for(int i = 0; i< iarray.length; i++){
            iarray[i] = new DisjointSet.Node(i,1); //초기에 부모는 자기자신, 랭크는 자기자신인 한 개
        }
        DisjointSet disSet = new DisjointSet(iarray);
        disSet.printAll();
        disSet.union(0, 4);
        disSet.union(1,4);
        disSet.printAll();

    }
}
