import java.util.*;
//Directed Acyclic Graph. 정점들을 선형 정렬 <u, v>에 대해 u가 v보다 반드시 앞선다.
public class TopologicalSort {
    private int N;
    private boolean visited[];
    private List<Integer> [] graph;
    private int [] order;
    int orderIndex;
    public TopologicalSort(List<Integer> adjList []){
        N = adjList.length;
        visited = new boolean[N];
        order = new int[N];
        orderIndex =N-1;
        graph = adjList;
        for(int i =0;i<N;i++) if(!visited[i]) dfs(i);
    }
    private void dfs(int i){
        visited[i] = true;
        System.out.print(i+" ");
        for(int x:graph[i]){
            if(!visited[x]) dfs(x);
        }
        order[orderIndex--] = i;
    }
    public void printSort(){
        System.out.println();
        for(int i = 0; i<N;i++) System.out.print(order[i]+" ");
    }
    public static void main(String [] args){
        List<Integer>[] l = new List[9];
        for(int i = 0; i<9;i++) l[i] = new LinkedList<>();
        l[2].add(1);l[2].add(0);
        l[1].add(4);l[1].add(3);
        l[0].add(1);
        l[4].add(5);
        l[3].add(6);
        l[5].add(3);l[3].add(7);
        l[6].add(7);
        l[7].add(8);
        System.out.println(l.length);
        //System.out.println(l[8].isEmpty());
        TopologicalSort t = new TopologicalSort(l);
        t.printSort();
    }
}
