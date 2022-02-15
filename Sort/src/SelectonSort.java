import java.lang.Comparable;
import java.util.*;

public class SelectonSort {
    public static void sort(Comparable [] a){
        int N = a.length;
        System.out.println("N: "+N);
        for(int i=0;i<N;i++){
            int min = i;
            for(int j = i+1;j<N;j++) if(isLess(a[j], a[min])) min  = j;
            Comparable t = a[i];
            a[i] = a[min];
            a[min] = t;
        }
    }
    private static boolean isLess(Comparable i, Comparable j){
        return (i.compareTo(j)<0);
    }
    public static void main(String [] args){
        Comparable a [] = new Comparable[15];
        Random rand = new Random();
        rand.setSeed(10);
        for(int i = 0 ; i<15;i++)a[i] = rand.nextInt(1000);
        for(int i = 0 ; i<15;i++)System.out.print(a[i]+" ");
        System.out.println(" ");
        sort(a);        
        for(int i = 0 ; i<15;i++)System.out.print(a[i]+" ");
    }
}
