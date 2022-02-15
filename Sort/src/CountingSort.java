public class CountingSort {
    public static Comparable [] sort(Comparable []a , int  n){
        int [] b = new int[n];
        int [] index = new int[n];
        System.out.println(a.length);
        Comparable [] t = new Comparable[a.length];
        for(int i = 0;i<a.length;i++){
            b[(int)a[i]]++;
        }
        System.out.println(b[0]+" "+b[1]+" "+b[2]);
        for(int i =1;i<index.length;i++){
            index[i] = index[i-1]+b[i-1];
        }
        System.out.println(index[0]+" "+index[1]+" "+index[2]);
        for(int i =0;i<a.length;i++){
            t[index[(int)a[i]]++] = a[i];
        }
        return t;
    }
    public static void main(String [] args){
        Comparable[] a = new Comparable[10];
        a[0]=0;a[1]=1;a[2]=2;a[3]=0;a[4]=1;a[5]=2;a[6]=0;a[7]=1;a[8]=2;a[9]=0;
        a = sort(a,3);
        for(int i =0;i<10;i++)System.out.print(a[i]+" ");
    }

}
