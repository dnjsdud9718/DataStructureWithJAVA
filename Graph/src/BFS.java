import java.util.*;

public class BFS{
    static class Edge { //E(u,v)
        int v;
        public Edge(int v){
            this.v = v;
        }
    }
    
    private int N;
    private boolean [] visited;
    private int [] prevVertex;
    private List<Edge>[] graph;
    public BFS(List<Edge>[] adjList){
        N = adjList.length;
        visited = new boolean[N];
        prevVertex = new int[N];
        graph = adjList;
        for(int i = 0; i<N;i++){
            visited[i] = false;
            prevVertex[i] = -1;
        }
        for(int i = 0;i<N;i++){
            if(!visited[i]){
                bfs(i);
            }
        }
    }
    private void bfs(int i){
        Queue<Integer> q = new LinkedList<>();
        visited[i] = true;
        q.add(i);
        while(!q.isEmpty()){
            int t = q.remove();
            System.out.print(t+" ");
            for(Edge x : graph[t]){
                if(!visited[x.v]){
                    q.add(x.v);
                    visited[x.v] = true;
                    prevVertex[x.v] = t;
                }
            }
        }
        System.out.println("");
    }
    public void printPath(int s, int v){
        if(s==v)System.out.print(s+ " ");
        else if(prevVertex[v] == -1) System.out.println("no path from "+s+" to "+v);
        else{
            printPath(s, prevVertex[v]);
            System.out.print(v+" ");
        }
    }
    public static void main(String [] args){
        List<Edge>[] adjList = new List[6]; //링크드리스트 배열을 만든다.l;''
        for(int i = 0; i<6;i++){
            adjList[i] = new LinkedList<>();
        }
        adjList[0].add(new Edge(1));adjList[0].add(new Edge(2));adjList[0].add(new Edge(3));
        adjList[1].add(new Edge(0));adjList[1].add(new Edge(4));adjList[1].add(new Edge(5));
        adjList[2].add(new Edge(0));adjList[2].add(new Edge(4));adjList[2].add(new Edge(5));
        adjList[3].add(new Edge(0));adjList[3].add(new Edge(5));
        adjList[4].add(new Edge(1));adjList[4].add(new Edge(2));
        adjList[5].add(new Edge(1));adjList[5].add(new Edge(2));adjList[5].add(new Edge(3));
        BFS b = new BFS(adjList);
        b.printPath(0, 5);
    }
}