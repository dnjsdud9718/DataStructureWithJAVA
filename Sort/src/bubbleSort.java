import java.util.*;
public class bubbleSort {
    private static void swap(Comparable []a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public static void sort(Comparable [] a){
        int N = a.length;
        boolean flag = true;
        for(int i = N-1;flag;i--){
            flag = false;
            for(int j = 0; j<i;j++){
                if(a[j].compareTo(a[j+1]) > 0 ) {swap(a,j,j+1);flag = true;}
            }
        }
    }
    public static void main(String []args){
        Random rand = new Random();
        rand.setSeed(10);
        Comparable [] array = new Comparable[10];
        for(int i = 0;i<10;i++) {array[i] = rand.nextInt(1000);System.out.print(array[i]+" ");}
        System.out.println(" ");
        sort(array);
        for(int i = 0;i<10;i++)System.out.print(array[i] + " ");
        
    }
}
 