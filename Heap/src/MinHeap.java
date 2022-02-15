import java.util.*;

public class MinHeap<Key extends Comparable<Key>,Value>{
    private Entry [] a;
    private int N;
    public MinHeap(Entry [] array, int initSize){ //initSize는 배열의 크기가 아니라 배열에 속한 원소의 개수
        a = array; N = initSize;
    }
    public int size(){return N;}
    private void downHeap(int i){
        while(i*2<=N){
            int j = i*2;
            if(j<N && a[j+1].getKey().compareTo(a[j].getKey()) < 0)j++;
            if(a[i].getKey().compareTo(a[j].getKey()) < 0) break;
            Entry t = a[i];
            a[i] = a[j];
            a[j] = t;
            i=j;
        }
    }
    public void createHeap(){
        for(int i = N/2; i>0;i--) downHeap(i);
    }
    public void print(){
        for(int i = 1; i<=N;i++){
            System.out.print("<"+a[i].getKey()+","+a[i].getValue()+"> ");
        }
        System.out.println("");
    }
    public Value deleteMin(){
        Value v = (Value)a[1].getValue();
        a[1] = a[N--];
        a[N+1] = null;
        downHeap(1);
        return v;
    }
    private void upHeap(int i){
        while(i > 1){
            int j = i/2; //parent
            if(a[j].getKey().compareTo(a[i].getKey()) < 0) break;
            Entry t = a[i];
            a[i] = a[j];
            a[j] = t;
            i=j;
        }
    }
    public void insert(Key k,Value v){
        Entry t = new Entry(k,v);
        a[++N] = t;
        upHeap(N);
    }
    public void decreaseKey(Key k, int i){
        if(a[i].getKey().compareTo(k) < 0) return;
        a[i].setKey(k);
        upHeap(i);
    }
    public static void main(String [] args){
        Entry [] array = new Entry[11];
        char ch = 'a';
        for(int i = 1, j = 10; i<11;i++,j--){
            array[i] = new Entry<>(j, ch++);
        }
        MinHeap<Integer,Character> m = new MinHeap<>(array, 10);
        m.print();
        System.out.println(m.size());
        m.createHeap();
        m.print();
        m.deleteMin();
        m.print();
        m.insert(150, ch++);
        m.print();
        m.decreaseKey(1, 10);
        m.print();
    }
}