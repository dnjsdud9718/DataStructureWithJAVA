import java.util.*;
public class BCC { //jBiconnected Component 이중연결성분
    class Edge{
        int v;
        int u;
        public Edge(int v,int u){
            this.v =v; this.u = u;
        }
    }
    private int N, sequence =1;
    private int dfsNum[];
    private int lowNum[];
    private List<Integer>[] graph;
    private Stack<Edge> stack = new Stack<>();
    public BCC(List<Integer>[]adjList){
        sequence = 1;
        N = adjList.length;
        graph = adjList;
        dfsNum = new int[N];
        lowNum = new int[N];
        for(int i = 0; i<N;i++) dfsNum[i] = 0;
        biconnected(0,-1);
    }
    private void biconnected(int v, int u){
        dfsNum[v] = lowNum[v] = sequence++;
        for(int w: graph[v]){
            if(u != w && dfsNum[w]<dfsNum[v]){
                stack.push(new Edge(v,w));
            }
            if(dfsNum[w] == 0){
                biconnected(w, v);
                lowNum[v] = Math.min(lowNum[v], lowNum[w]);
                if(lowNum[w]>=dfsNum[v]){
                    Edge x;
                    do{
                        x = stack.pop();
                        System.out.print("("+x.v+","+x.u+") ");
                    }while(x.v != v && x.u !=w);
                    System.out.println("");
                }
            }
            else if(u != w){
                lowNum[v]=Math.min(lowNum[v],dfsNum[w]);
            }
        }
    }
    public static void main(String [] args){
        List<Integer> []adjList = new List[7];
        for(int i = 0;i<7;i++) adjList[i] = new LinkedList<>();
        adjList[0].add(3);adjList[0].add(1);adjList[0].add(2);
        adjList[1].add(2);adjList[1].add(0);
        adjList[2].add(1);adjList[2].add(3);adjList[2].add(0);
        adjList[3].add(0);adjList[3].add(2);adjList[3].add(5);
        adjList[4].add(5);adjList[4].add(6);
        adjList[5].add(3);adjList[5].add(4);adjList[5].add(6);
        adjList[6].add(4);adjList[6].add(5);
        BCC bcc = new BCC(adjList);
    }
}
