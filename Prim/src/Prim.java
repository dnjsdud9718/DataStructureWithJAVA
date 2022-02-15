import java.util.*;
public class Prim {
    static class Edge{
        int adjVertex, wt;
        public Edge(int adj, int w){adjVertex  = adj; wt = w;}
    }
    private int N;
    private List<Edge> [] graph;
    private boolean treeVertex[];
    public Prim(List<Edge> [] adjList){
        N = adjList.length;
        graph = adjList;
        treeVertex = new boolean[N];
        for(int i = 0 ; i<N; i++){
            treeVertex[i] = false;
        }
    }
    public void mst(){
        treeVertex[0] = true;//시작할 때 임의의 버텍스 하나가 트리에 속해 있다고 가정한다.
        int min, minVertex;
        int cnt = 0;
        int prev; //트리에 속한 버텍스
        System.out.println("u,v,weight");
        while(cnt < N-1){
            min = Integer.MAX_VALUE; minVertex =-1;prev = -1;
            for(int i = 0; i<N;i++){
                if(treeVertex[i]){
                    for(Edge e: graph[i]){
                        if(!treeVertex[e.adjVertex] && min>e.wt){
                            min = e.wt;
                            minVertex = e.adjVertex;
                            prev = i;
                        }
                    }
                }
            }
            if(minVertex == -1) break;
            treeVertex[minVertex] = true;
            System.out.println("("+prev+","+minVertex+","+min+")");
            cnt++;
        }
    }
    public static void main(String [] args){
        List<Edge> [] adjList = new List[7];
        for(int i = 0 ; i<7;i++) adjList[i] = new LinkedList<>();
        adjList[0].add(new Edge(1,9));
        adjList[0].add(new Edge(2,10));
        adjList[1].add(new Edge(0,9));
        adjList[1].add(new Edge(4,5));
        adjList[1].add(new Edge(6,3));
        adjList[1].add(new Edge(3,10));
        adjList[2].add(new Edge(0,10));
        adjList[2].add(new Edge(4,7));
        adjList[2].add(new Edge(5,2));
        adjList[2].add(new Edge(3,9));
        adjList[3].add(new Edge(1,10));
        adjList[3].add(new Edge(5,4));
        adjList[3].add(new Edge(6,8));
        adjList[3].add(new Edge(2,9));
        adjList[4].add(new Edge(6,1));
        adjList[4].add(new Edge(1,5));
        adjList[4].add(new Edge(2,7));
        adjList[5].add(new Edge(6,6));
        adjList[5].add(new Edge(3,4));
        adjList[5].add(new Edge(2,2));
        adjList[6].add(new Edge(1,3));
        adjList[6].add(new Edge(4,1));
        adjList[6].add(new Edge(3,8));
        adjList[6].add(new Edge(5,6));
        Prim p = new Prim(adjList);
        p.mst();

    }
}
