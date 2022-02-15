import java.util.*;
public class MergeSort {
    public static void sort(Comparable []a, int s, int e){
        if(s<e){
            int mid = (s+e)/2;
            sort(a,s,mid);
            sort(a,mid+1,e);
            merge(a,s,mid,e);
        }
    }
    private static void merge(Comparable [] a, int s, int m, int e){
        int n1 = m-s+1;
        int n2 = e-m;
        Comparable [] l = new Comparable[n1+1];
        Comparable [] r = new Comparable[n2+1];
        for(int i = 0; i<n1;i++) l[i] = a[s+i];
        for(int i = 0; i<n2;i++) r[i] = a[m+i+1];
        l[n1] = r[n2] = 9999;
        for(int k=s,i=0,j=0;k<e+1;k++){
            if(l[i].compareTo(r[j])<=0)a[k]=l[i++];
            else a[k] = r[j++];
        }
    }
    public static void main(String [] args){
        Random rand = new Random();
        rand.setSeed(30);
        Comparable a[] = new Comparable[20];
        for(int i = 0;i<20;i++){a[i]=rand.nextInt(1000);System.out.print(a[i]+" ");}
        sort(a,0,19);
        System.out.println("");
        for(int i = 0;i<20;i++){System.out.print(a[i]+" ");}

    }
}
