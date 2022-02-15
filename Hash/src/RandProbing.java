import java.rmi.server.ObjID;
import java.util.*;
public class RandProbing <K,V>{
    private final int M = 13;
    private int N = 0;
    private K [] a = (K []) new Object[M];
    private V [] d = (V []) new Object[M];
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff)%M;
    }
    public void put(K key, V data){
        int initialpos = hash(key);
        int i = initialpos;
        Random rand = new Random();
        rand.setSeed(10);
        do{
            if(d[i] == null){
                a[i] = key;
                d[i] = data;
                N++;
                return;
            }
            if(a[i].equals(key)){
                d[i] = data;
                return ;
            }
            i = (initialpos+rand.nextInt(1000))%M;
        }while(N<M);
    }
    public V get(K key){
        int initialpos = hash(key);
        int i = initialpos;
        Random rand = new Random();
        rand.setSeed(10);
        while(a[i]!=null){
            if(a[i].equals(key)){
               return (V)d[i];
            }
            i = (initialpos+rand.nextInt(1000))%M;
        }
        return null;
    }
    public void delete(K key){ //a가 full이고 없는 키를 넣으면 무한 루프로 돌 수 있다.
        int initialpos = hash(key);
        int i = initialpos;
        Random rand = new Random();
        rand.setSeed(10);
        while(a[i] != null){
            System.out.println(initialpos+"<"+i);
            if(a[i].equals(key)){
                a[i] = (K)new Object();
                d[i] = null;
                N--;
                return;
            }
            i = (initialpos+rand.nextInt(1000))%M;
        }
    }
    private void print(){
        for(int i = 0; i < M;i++) System.out.print(a[i]+" ");
        System.out.println("");
        for(int i = 0; i < M;i++) System.out.print(d[i]+" ");
        System.out.println(" ");
    }
    public static void main(String [] args){
        RandProbing<Integer, Character> r = new RandProbing<>();
        char ch = 'a';
        for(int i = 0;i<13;i++){
            r.put(i*13, ch++);
        }
        //r.delete(100);
        r.print();
        r.delete(13);
        r.print();
    }
}
