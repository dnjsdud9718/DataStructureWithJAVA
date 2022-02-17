import java.util.*;
public class BellmanFord {
    private static final int INF = 9999;
    private int [] distance;
    private int [] path;
    private int N;
    private int [][] graph;
    public BellmanFord(int [][] matrix){
        N = matrix.length;
        distance = new int[N];
        path = new int[N];
        graph = matrix;
    }
    public void spPath(int s){
        for(int i=0;i<N;i++){
            distance[i] = INF;
            path[i] =s;
        }
        distance[s] = 0;
        int k = 0;
        while(k++<N-1){
            for(int i = 0; i<N;i++){
                if(distance[i]!=INF){ //s에서 i까지 가는 경로가 있다.
                    for(int j = 0;j<N;j++){
                        if(distance[j]>distance[i]+graph[i][j]){
                            distance[j] = distance[i]+graph[i][j];
                            path[j] = i;
                        }
                    }
                }
            }
        }
        for(int i = 0; i<N;i++){
            System.out.printf("from %d to %d: ", s, i);
            printPath(s, i);
            System.out.println("");
        }
    }
    public void printPath(int s, int e){
        if(s==e) System.out.printf("%d->", s);
        else{
            printPath(s, path[e]);
            System.out.printf("%d->", e);
        }
    }
    public static void main(String [] args){
        int [][] adjMatrix = new int[8][8];
        for(int i = 0 ; i<8;i++){
            for(int j = 0 ; j<8;j++){
                adjMatrix[i][j] = INF;
            }
            adjMatrix[i][i] = 0;
        }
        
        adjMatrix[0][1] = 1;
        adjMatrix[0][3] = 2;
        adjMatrix[1][3] = -2;
        adjMatrix[1][4] = 1;
        adjMatrix[1][5] = 6;
        adjMatrix[1][2] = 4;
        adjMatrix[2][7] = 2;
        adjMatrix[3][4] = -1;
        adjMatrix[4][6] = 4;
        adjMatrix[5][2] = 1;
        adjMatrix[6][2] = -1;
        adjMatrix[6][7] = 1;
        adjMatrix[7][5] = 3;
        BellmanFord bf = new BellmanFord(adjMatrix);
        bf.spPath(0);
    }
}
