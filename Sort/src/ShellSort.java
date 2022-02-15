import java.util.*;
public class ShellSort {
    public static void sort(Comparable [] a){
        int size = a.length;
        for(int i = size/2;i>0;i/=2){
            for(int j = 0;j<i;j++){
                for(int k = i+j;k<size;k+=i){
                    Comparable l = a[k];
                    int m = k;
                    while(m-i>=0 && a[m-i].compareTo(l) > 0) {
                        a[m] = a[m-i];
                        m-=i;
                    }
                    a[m]=l;
                }
            }
        }
    }
    public static void main(String[] args){
        Random rand = new Random();
        rand.setSeed(10);
        Comparable [] array = new Comparable[10];
        for(int i = 0; i<10;i++){array[i] = rand.nextInt(1000); System.out.print(array[i]+" ");}
        System.out.println("");
        sort(array);
        for(int i = 0; i<array.length;i++) System.out.print(array[i]+" ");
    }    
}
