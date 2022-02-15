import java.util.*;
public class HeapSort {
    //maxheap을 만들고 루트에 있는 값을 맨 뒤로 옮기며 힙사이즈를 1 감소, 그리고 다시 맥스힙을 만든다.
    //이를 힙사이즈가 1이될 때까지 반복
    private static void swap(Comparable [] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public static void sort(Comparable []a){
        int n = a.length-1;
        for(int i = n/2;i>0;i--)downHeap(a,i, n);
        while(n>1){
            swap(a,1,n--);
            downHeap(a, 1, n);
        }
    }
    private static void downHeap(Comparable [] a,int i, int size){
        while(i*2<=size){
            int child = i*2;
            if(child<size && a[child+1].compareTo(a[child]) > 0) child++;
            if(a[i].compareTo(a[child]) > 0) break;
            Comparable t = a[i];
            a[i] = a[child];
            a[child]=t;
            i = child;
        }
    }
    public static void print(Comparable [] a){
        int size = a.length-1;
        for(int i=1;i<=size;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println("");
    }
    public static void main(String [] args){
        Random rand = new Random();
        rand.setSeed(10);
        Comparable [] a = new Comparable[11];
        for(int i = 1;i<a.length;i++) a[i] = rand.nextInt(1000);
        print(a);
        sort(a);
        print(a);
    }
}
