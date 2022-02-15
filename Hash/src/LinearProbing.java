//선형조사      
public class LinearProbing <K, V>{
    private final int M = 13; //테이블 크기
    private K [] a = (K[]) new Object[M]; //해시 테이블 
    private V [] d = (V[]) new Object[M]; //key 관련 데이터 저장
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff)%M; //0 ~ M-1
    }
    /* <Integer,Character>라 가정. <0,a>,<13,b>,<26,c>,<39,d>,<52,e>,<65,f>,<78,g>가 차례대로 
    삽입. 모든 hash(key)는 0이기 때문에 배열의 0,1,2,3,4,5,6 위치에 a,b,...,g가 차례로 들어간다.
    여기서 키가 39인 정보를 지운다고 가정해보자. 이때 a[3]을 null로 만들면 get()가 원할히 이루어지지
    않는다. 그렇다고 a[3]에 null이 아닌 값을 넣는다면 put()가 적절히 이루어지지 않는다.
    그래서 delete() 시 a[i]에는 null이 아닌 값을 d[i]는 null로 만들고 put()시 d[i]가 null인지 확인하고
    데이터를 삽입한다. get()도 잘 작동한다.
    */
    private void put(K key, V data){
        int initialpos = hash(key);
        System.out.println("initialpos: "+initialpos);
        int i = initialpos;
        do{
            if(d[i] == null){
                a[i] = key;
                d[i] = data;
                return;
            }
            if(a[i].equals(key)){
                d[i] = data;
                return;
            }
            i = (i+1)%M;            
        }while(i != initialpos);
    }
    public V get(K key){
        int initialpos = hash(key);
        int i = initialpos;
        while(a[i] != null){
            if(a[i].equals(key)) return d[i];
            i = (i+1)%M;
            if(i == initialpos) return null;
        }
        return null;
    }
    private void delete(K key){
        int  initialpos = hash(key);
        int i = initialpos;
        do{
            if(a[i] == null) return;
            if(a[i].equals(key)){
                a[i] = (K) new Object();
                d[i] = null;
            }
            i = (i+1)%M;
        }while(i != initialpos);
        return;
    }
    private void print(){
        for(int i = 0; i < M;i++) System.out.print(a[i]+" ");
        System.out.println("");
        for(int i = 0; i < M;i++) System.out.print(d[i]+" ");
    }
    public static void main(String [] args){
        LinearProbing<Integer, Character> l = new LinearProbing<>();
        char ch = 'a';
        for(int i = 0; i<13;i++)l.put(13*i,ch++);
        //for(int i = 0; i<19;i++)System.out.println(l.get(i));
        l.delete(52);
        l.delete(65);
        l.delete(91);
        l.print();
        System.out.println(" ");
        System.out.println(l.get(78));
    }
}
