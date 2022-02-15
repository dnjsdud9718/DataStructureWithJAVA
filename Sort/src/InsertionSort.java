import java.util.*;
public class InsertionSort {
    public static void sort(Comparable [] a){
        int N = a.length;
        System.out.println("N: "+N);
        int i , j;
        for(i = 1; i<N;i++){
            Comparable next = a[i];
            for(j=i-1;j>=0 && a[j].compareTo(next) > 0;j--) a[j+1] = a[j];
            a[j+1] = next;
        }
    }
    public static void main(String [] args){
        Comparable [] a = new Comparable[15];
        Random rand = new Random();
        rand.setSeed(10);
        
        for(int i = 0,j=15;i<15;i++,j--)a[i] = rand.nextInt(1000);
        for(int i = 0;i<15;i++)System.out.print(a[i]+" ");
        sort(a);
        System.out.println(" ");
        for(int i = 0;i<15;i++)System.out.print(a[i]+" ");
    }
}
