
// 이차 조사
public class QuadProbing <K,V>{
    private final int M = 13;
    private int N = 0;
    private K [] a = (K[]) new Object[M];
    private V [] d = (V[]) new Object[M];
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff)%M;
    }
    /* 이차 조사 유의: empty원소가 있음에도 못찾아내고 건너띄어 저장에 실패할 수도 있다. */
    public void put(K key, V data){
        int initialpos = hash(key);
        int i = initialpos, j = 1;
        do{
            //System.out.println(i+","+N+","+j*j);
            if(d[i] == null){
                a[i] = key;
                d[i] = data;
                N++;
                return;
            }
            if(a[i].equals(key)){
                d[i] = data;
                return;
            }
            i = (initialpos+j*j++)%M;
            if(i < 0) return; //i가 int로 담을 수 있는 크기를 벗어났다. 2^31-1
        }while(N<M);
    }
    public V get(K key){
        int initialpos = hash(key);
        int  i = initialpos, j = 1;
        while(a[i] != null){
            if(a[i].equals(key)) return d[i];
            i = (initialpos + j*j++)%M;
            if(i < 0) return null;
        }
        return null;
    }
    public void delete(K key){
        int initialpos = hash(key);
        int i = initialpos, j = 1;
        while(a[i] != null){
            if(a[i].equals(key)){
                a[i] = (K)new Object();
                d[i] = null;
                N--;
                return;
            }
            i = (initialpos+j*j++)%M;
            if(i < 0) return;
        }
    }
    private void print(){
        for(int i = 0; i < M;i++) System.out.print(a[i]+" ");
        System.out.println("");
        for(int i = 0; i < M;i++) System.out.print(d[i]+" ");
    }
    public static void main(String [] args){
        QuadProbing<Integer, Character> q = new QuadProbing<>();
        char c = 'a';
        for(int i = 0; i<8;i++) q.put(i*13, c++);
        q.delete(13);
        q.delete(65);
        q.print();
    }
}
