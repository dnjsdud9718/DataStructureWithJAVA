import java.util.*;
/*
    강연결성분을 찾는 알고리즘
    강연결성분은 모든 정점으로부터 모든 정점으로 가는 경로가 존재하는 집합.
    아래는 집합에서 강연결성분인 부분집합을 추출하는 프로그램
    Tarjan과 Kosaraju 알고리즘이 있는데 아래는 Tarjan 알고리즘이다.
    이중연결성분을 찾는 알고리즘과 매우 유사하며 스택을 활용한다. 
    dfs 알고리즘으로 방문하는 순서에 따라 번호를 매긴다. 늦게 방문할수록 큰 값을 부여 받는다.
    강연결성분은 모든 정점에서 모든 정점으로 이동 가능해야 하기 때문에 부모로도 당연히 이동 가능해야 한다. 
    따라서 뒷간선을 활용해 부모로 가는 경로가 있는지 확인하며, 경로가 존재한다면 부모와 자식은 같은
    강연결성분에 포함되고 그렇지 않는다면 서로 분리된 강연결성분에 포함된다.
    중요: 자식에서 부모로 가는 경로가 존재한다면 같은 부모와 자식은 같은 연결성분에 포함된다. 
*/
public class Tarjan { 
    private int N;
    private int sequence;
    private int [] dfsNum;
    private int [] lowNum;
    List<Integer> [] graph;
    Stack<Integer> stack;
    public Tarjan(List<Integer>[] adjList){
        sequence =1;
        N = adjList.length;
        dfsNum = new int[N];
        lowNum = new int[N];
        graph = adjList;
        stack = new Stack<>();
        for(int i = 0; i<N;i++) dfsNum[i] = 0;
        scc(0); 
        while(!stack.isEmpty()) System.out.print(stack.pop()+ " ");
        System.out.println("");
    }
    private void scc(int v){
        dfsNum[v] = lowNum[v] = sequence++;
        stack.add(v);
        for(int w : graph[v]){
            if(dfsNum[w] == 0){
                scc(w);
                lowNum[v] = Math.min(lowNum[v], lowNum[w]); 
                if(lowNum[w] > dfsNum[v]){ //부모로 갈 수 있는지를 체크!
                    int x;
                    while((x = stack.pop())!=w) System.out.print(x+" ");
                    System.out.println(x+" ");
                }
            }
            else lowNum[v] = Math.min(lowNum[v], dfsNum[w]); // 뒷간선 w를 이미 방문한 상태.
        }
    }
    public static void main(String [] args){
        Scanner s = new Scanner(System.in);
        List<Integer> [] adjList = new List[9];
        for(int i = 0 ; i<9;i++)adjList[i] = new LinkedList<>();
        adjList[0].add(7);
        adjList[1].add(8);
        adjList[2].add(5);
        adjList[3].add(1);adjList[3].add(4);adjList[3].add(8);
        adjList[4].add(2);adjList[4].add(5);
        adjList[5].add(3);
        adjList[6].add(0);adjList[6].add(4);
        adjList[7].add(2);adjList[7].add(6);
        adjList[8].add(1);
        Tarjan t = new Tarjan(adjList);
        s.close();
    }
}
