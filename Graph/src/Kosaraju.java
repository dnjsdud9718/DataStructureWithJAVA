import java.util.*;
/* 
    강연결성분을 찾는 알고리즘
    입력그래프의 각각의 강연결성분은 역방향 그래프에서도
    각각 동일한 강연결성분이다. 입력그래프의 위상정렬
    순서로 역방향그래프에서 DFS를 수행하면 하나의 강연결성분에서
    다른 강연결성분으로 진행할 수 없다.
    1. DFS를 이용하여 위상정렬순서 S를 찾는다. 단, 그래프의 싸이클은
    무시하며 S를 찾는다.
    2. 각 간선이 역방향으로 된 역방향그래프 Gr을 만든다.
    3. S를 이용하여 Gr에서 DFS를 수행하면서 강연결성분들을 추출한다.
*/
public class Kosaraju {
    private int N;
    private boolean [] visited;
    private List<Integer> []graph;
    private List<Integer> []reverse;
    private Stack<Integer> stack;
    public Kosaraju(List<Integer> []adjList){
        stack = new Stack<>();
        N = adjList.length;
        visited = new boolean[N];
        for(int i = 0; i<N;i++)visited[i] = false;
        graph = adjList;
        for(int i = 1;i<N;i++)if(!visited[i])dfs(i);
        //visited는 모두 true로 바뀌었을 것이다.
        reverse = reverseGraph(adjList);
        while(!stack.isEmpty()){
            int k=stack.pop();
            if(visited[k]) {scc(k);System.out.println();}
        }
    }
    private void scc(int v){
        visited[v] = false;
        System.out.print(v+" ");
        for(int w: reverse[v]){
            if(visited[w]){scc(w);}
        }
    }
    private void dfs(int v){
        visited[v] = true;
        for(int w : graph[v]){
            if(!visited[w]) dfs(w);
        }
        stack.add(v);
    }
    private List<Integer>[] reverseGraph(List<Integer>[] adjList){
        List<Integer>[] revList = new List[adjList.length];
        for(int i =0;i<adjList.length;i++) revList[i] = new LinkedList<>();
        for(int i = 0; i< adjList.length;i++){
            for(int j =0; j<adjList[i].size();j++) revList[adjList[i].get(j)].add(i);
        }
        return revList;
    }
    public void printGraph(List<Integer>[] adjList){
        for(int i = 0; i< adjList.length;i++){
            System.out.print(i+": ");
            for(int j =0; j<adjList[i].size();j++)System.out.print(adjList[i].get(j)+" ");
            System.out.println();
        }
    }
    public static void main(String args[]){
        List<Integer> [] adjList = new List[8];
        for(int i = 0 ; i<8;i++)adjList[i] = new LinkedList<>();
        adjList[1].add(4);
        adjList[4].add(5);
        adjList[5].add(1);
        adjList[1].add(6);
        adjList[6].add(7);
        adjList[2].add(7);
        adjList[7].add(3);
        adjList[3].add(7);
        adjList[7].add(2);
        /*
        adjList[0].add(7);
        adjList[1].add(8);
        adjList[2].add(5);
        adjList[3].add(1);adjList[3].add(4);adjList[3].add(8);
        adjList[4].add(2);adjList[4].add(5);
        adjList[5].add(3);
        adjList[6].add(0);adjList[6].add(4);
        adjList[7].add(2);adjList[7].add(6);
        adjList[8].add(1);
        */
        Kosaraju k = new Kosaraju(adjList);
        
    }
}
