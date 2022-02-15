import java.util.*;
public class QuickSort {
    private static void swap(Comparable []a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j]=t;
    }
    public static void sort(Comparable [] a, int lt, int rt){
        if(lt<rt){
            int i = lt;
            int j = rt+1;
            Comparable pivot = a[lt]; //좋은 성능을 기대하기 위해선 피봇값을 적절히 지정해야 한다.
            //여기서는 부분 배열에 첫 번째 원소를 피봇으로 지정했다. 
            do{
                do{ //pivot보다 큰 놈을 찾는다.
                    i++; 
                }while(i<=rt && pivot.compareTo(a[i]) > 0);
                do{ //pivot보다 작은 놈을 찾는다.
                    j--;
                }while(j>lt && pivot.compareTo(a[j]) < 0);
                if(i<j) swap(a,i,j);
            }while(i<j);
            if(j!=lt) swap(a,j,lt);//a[j]는 pivot보다 작고 a[lt]는 pivot을 가지고 있다. 
            //a[j]에 pivot이 들어가고 pivot의 왼쪽에는 pivot보다 작은 데이터가 오른쪽에는 
            //큰 데이터가 들어가 있다. 하지만 정렬된 상태는 아니다. 
            sort(a,lt,j-1);
            sort(a,j+1,rt);
        }
    }
    public static void main(String [] args){
        Random rand = new Random();
        rand.setSeed(10);
        Comparable [] a = new Comparable[10];
        for(int i = 0;i<10;i++){a[i]=rand.nextInt(1000);System.out.print(a[i]+" ");}
        System.out.println(" ");
        sort(a,0,9);
        for(int i = 0; i<10;i++)System.out.print(a[i]+" ");
        System.out.println(" ");
        Comparable b[] = new Comparable[3];
        b[0] = 8; b[1] = 6; b[2] = 7;
        sort(b,0,2);
        System.out.println(b[0]+" "+ b[1]+" "+b[2]);
    }
}
