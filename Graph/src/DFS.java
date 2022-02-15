import java.util.LinkedList;
import java.util.List;
public class DFS {
    static class Edge { //E(u,v)
        int v;
        public Edge(int v){
            this.v = v;
        }
    }
    
    int N;
    List<Edge>[] graph;
    private boolean [] visited;
    public DFS(List<Edge>[] adjList){
        N = adjList.length;
        graph = adjList;
        visited = new boolean[N];
        for(int i = 0; i<N;i++) visited[i] = false;
        for(int i = 0; i<N;i++) if(!visited[i]) dfs(i);
    }
    private void dfs(int i){
        visited[i] = true;
        System.out.print(i+" ");
        for(Edge e: graph[i]){ //각 edge를 정확히 한 번만 방문한다. O(N+E)
            if(!visited[e.v]) dfs(e.v);
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
        DFS d = new DFS(adjList);
    }
}
