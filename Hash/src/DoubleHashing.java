public class DoubleHashing <K,V>{
    private final int M = 13;
    private int N = 0;
    private K [] a = (K [])new Object[M];
    private V [] dt = (V [])new Object[M];
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff)%M;
    }
    public void put(K key, V data){
        int initialpos = hash(key);
        int i = initialpos, j = 1;
        int d =(7-(int)key%7); //1~7 1/7확률 골고루 퍼지네
        do{
            if(dt[i] == null){
                a[i] = key;
                dt[i] = data;
                N++;
                return;
            }
            if(a[i].equals(key)){
                dt[i] = data;
                return;
            }
            i = (initialpos+j*d)%M;
            j++;
        }while(N<M);
    }
    private V get(K key){ //키가 있다고 가정
        int initialpos = hash(key);
        int i = initialpos, j =1;
        int d = (7-(int)key%7);
        while(a[i] != null){
            if(a[i].equals(key)) return dt[i];
            i = (initialpos+j*d)%M;
            j++;
        }
        return null;
    }
    private void delete(K key){ //key가 있다고 가정
        int  initialpos = hash(key);
        int i = initialpos,j=1;
        int d = (7-(int)key%7);
        while(a[i] != null){
            if(a[i].equals(key)){
                a[i] = (K) new Object();
                dt[i] = null;
                N--;
                return;
            }
            i = (initialpos+j*d)%M;
            j++;
        }
    }
    public static void main(String [] args){
      
    }
}
