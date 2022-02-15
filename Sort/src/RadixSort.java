import java.util.*;
public class RadixSort { //LSB -> MSB O(d(N+R)) d는 키의 자리수, R은 기수
    public static void sort(int [] a){
        int R = 10;
        int N  = a.length;
        int [] t = new int[N];
        for(int k = R;k<=1000;k*=R){
            int b [] = new int[R+1];
            for(int i = 0;i<R+1;i++) b[i] =0; 
            for(int i = 0;i<N;i++) b[(a[i]%k)/(k/10)+1]++; //빈도수 계산
            //System.out.println(" ");
            //for(int i = 0; i<=R;i++)System.out.print(b[i]+" ");
            for(int i =0;i<R;i++) b[i+1]+=b[i];//부분합
            //System.out.println(" ");
            //for(int i = 0; i<=R;i++)System.out.print(b[i]+" ");
            for(int i =0;i<N;i++) {t[b[(a[i]%k)/(k/10)]++] = a[i];}
            for(int i =0;i<N;i++) a[i] = t[i];
            System.out.println(" ");
            System.out.println(k/10+"자리 정렬 결과");
            for(int i = 0; i<N;i++)System.out.print(String.format("%03d", a[i])+" ");
            System.out.println(" ");
        }
    }
    public static void main(String [] args){
        Random rand = new Random();
        rand.setSeed(10);        
        int a[] = new int[10];
        for(int i = 0;i<10;i++) {a[i] = rand.nextInt(1000);System.out.print(a[i]+" ");}
        sort(a);
    }
}
