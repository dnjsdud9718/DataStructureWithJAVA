import java.util.*;
public class Kruskal{
    private int N,M; //N->노드의 수, ->간선의 수
    private List<Edge> [] graph;
    private Edge [] tree;
    private UnionFind uf;
    static class WeightComparison implements Comparator<Edge>{
        public int compare(Edge i, Edge j){
            if(i.wt > j.wt) return 1;
            else if (i.wt < j.wt) return -1;
            else return 0;
        }
    }
    public Kruskal(List<Edge> [] adjList, int numOfEdges){ 
        N = adjList.length;
        M = numOfEdges;
        graph = adjList;
        uf = new UnionFind(N);
        tree = new Edge[N-1];
    }
    public Edge[] mst(){
        WeightComparison BY_WEIGHT = new WeightComparison();
        PriorityQueue<Edge> pq = new PriorityQueue<>(M, BY_WEIGHT);
        for(int i = 0; i<N;i++){
            for(Edge e: graph[i]) pq.add(e);
        }
        int count = 0;
        while(!pq.isEmpty() && count <= N-1){
            Edge e = pq.poll();
            int u = e.u;
            int v = e.v;
            if(!uf.isConnected(u, v)){
                uf.union(u,v);
                tree[count++] = e;
            }
        }
        return tree;
    }
    public static void main(String [] args){
        List<Edge> [] adjList = new List[5];
        for(int i = 0 ; i<5;i++){
            adjList[i] = new LinkedList<>();
        }
        adjList[0].add(new Edge(0,1,160));
        adjList[0].add(new Edge(0,2,230));
        adjList[0].add(new Edge(0,3,320));
        
        adjList[1].add(new Edge(1,2,100));
        adjList[1].add(new Edge(1,3,150));
        
        adjList[2].add(new Edge(2,3,140));
        adjList[2].add(new Edge(2,4,190));
        
        adjList[3].add(new Edge(3,4,60));
        
        Kruskal k = new Kruskal(adjList, 8);
        Edge[] r = k.mst();
        for(int i =0 ;i<r.length;i++) {
        	System.out.println(r[i].u+","+r[i].v+","+r[i].wt);
        }
        System.out.println("Hello");               
    }
}