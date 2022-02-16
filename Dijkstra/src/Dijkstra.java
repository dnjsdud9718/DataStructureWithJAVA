import java.util.*;

public class Dijkstra {
    static class Edge{
        int adjVertex, wt;
        public Edge(int v, int wt) {
            this.adjVertex = v; this.wt = wt; 
        }
    }
	private int distance[];
	private boolean founded[];
	private int path[];
	private int N;
	private List<Edge> [] graph;
	public Dijkstra(List<Edge> []adjList) {
		N = adjList.length;
		distance = new int[N];
		founded = new boolean[N];
		path = new int[N];
		graph = adjList;
	}
	private int choose() {
		int min = Integer.MAX_VALUE, vertex = -1;
		for(int i = 0 ; i<N;i++) {
			if(!founded[i] && min > distance[i]) {
				min = distance[i];
				vertex = i;
			}
		}
		return vertex;
	}
	private void print() {
		System.out.println("==========================================");
		System.out.print("Tree Vertex: {");
		for(int i = 0; i<N;i++) {
			if(founded[i]) {
				System.out.printf("%2d,", i);
			}
		}
		System.out.println("}");
		System.out.printf("Distance:  ");
		for(int i = 0;i<N;i++) {
			if(distance[i] == Integer.MAX_VALUE)System.out.printf("%3d", -1);
			else System.out.printf("%3d", distance[i]);
		}
		System.out.println();
		System.out.printf("Path:      ");
		for(int i = 0; i<N;i++)System.out.printf("%3d", path[i]);
		System.out.println();
	}
	public int[] spPath(int s) {
		for(int i = 0 ; i<N;i++) {
			founded[i] = false;
			distance[i] = Integer.MAX_VALUE;
			path[i] = s;
		}
		for(Edge e: graph[s]) {
			distance[e.adjVertex] = e.wt;
		}
		distance[s] = 0;
		founded[s] = true;
		int cnt = 0;
		while(cnt<N-1){
			print();
			int u =  choose();//아직 방문하지 않은 vertex 중에서 가장 가까운 거리에 있는 vertex를 가져온다.
			founded[u] = true;
			for(Edge e: graph[u]) {//거리 업데이트 기존 거리 vs. u를 거쳐가는 경로의 거리
				if(!founded[e.adjVertex] && distance[e.adjVertex] > distance[u]+e.wt) {
					distance[e.adjVertex] = distance[u]+e.wt;
					path[e.adjVertex] = u;
				}
			}
			cnt++;
		}
		return distance;
	}
	public void getPath(int s, int e) {
		if(s==e)System.out.printf("%3d",s);
		else {
			getPath(s,path[e]);
			System.out.printf("%3d", e);
		}
	}
	public static void main(String [] args) {
		List<Edge> [] adjList = new List[8];
        for(int i = 0; i<8;i++)adjList[i] = new LinkedList<>();
        adjList[0].add(new Edge(1,1));
        adjList[0].add(new Edge(3,2));
        adjList[1].add(new Edge(0,1));
        adjList[1].add(new Edge(3,3));
        adjList[1].add(new Edge(4,1));
        adjList[1].add(new Edge(2,4));
        adjList[1].add(new Edge(5,6));
        adjList[2].add(new Edge(1,4));
        adjList[2].add(new Edge(5,1));
        adjList[2].add(new Edge(7,2));
        adjList[2].add(new Edge(6,1));
        adjList[3].add(new Edge(0,2));
        adjList[3].add(new Edge(1,3));
        adjList[3].add(new Edge(4,5));
        adjList[4].add(new Edge(1,1));
        adjList[4].add(new Edge(3,5));
        adjList[4].add(new Edge(6,2));
        adjList[5].add(new Edge(1,6));
        adjList[5].add(new Edge(2,1));
        adjList[5].add(new Edge(7,9));
        adjList[6].add(new Edge(2,1));
        adjList[6].add(new Edge(4,2));
        adjList[6].add(new Edge(7,1));
        adjList[7].add(new Edge(2,2));
        adjList[7].add(new Edge(6,1));
        adjList[7].add(new Edge(5,9));
        Dijkstra d = new Dijkstra(adjList);
        int r [] = d.spPath(4);
        for(int i = 0 ; i<r.length;i++){
            System.out.print(r[i]+ " ");
        }
        System.out.println();
        System.out.printf("%d에서 %d까지의 경로: ",4,5);
        d.getPath(4, 5);
	}
}
