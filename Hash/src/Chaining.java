public class Chaining <Key, Value>{
    private final int M = 13;
    private static class Node{
        Object key;
        Object value;
        Node next;
        public Node(Object k, Object v, Node n){
            key = k;
            value = v;
            next = n;
        }
        public Node getNext(){return next;}
        public Object getKey(){return key;}
        public Object getValue(){return value;}
        public void setNext(Node n){next = n;}
        public void setKey(Object k){key = k;}
        public void setValue(Object v){value = v;}
    }
    private int hash(Key k){
        return (k.hashCode() & 0x7ffffff)%M;
    }
    private Node [] a = new Node[M];
    public void put(Key key, Value value){
        int i = hash(key);
        for(Node x = a[i];x!=null;x = x.getNext()){
            if(key.equals(a[i].key)){
                a[i].setValue(value);
                return;
            }
        }
        a[i] = new Node(key,value,a[i]);
    }
    public Value get(Key k){
        int i = hash(k);
        Node x = a[i];
        while(x!=null){
            if(x.key.equals(k)) return (Value)x.getValue();
            x = x.getNext();
        }
        return null;
    }
    public void delete(Key k){
        int i = hash(k);
        a[i] = delete(a[i], k);
    }
    public Node delete(Node x, Key k){
        if(x == null) return null;
        if(k.equals(x.key)) return x.next;
        x.next = delete(x.next, k);
        return x;
    }
    public void print(){
        for(int i = 0; i<M;i++){
            Node x = a[i];
            System.out.print("offset "+i+" : ");   
            while(x != null){
                System.out.print("<"+x.getKey()+","+x.getValue()+">");
                x=x.getNext();
            }
            System.out.println(" ");
        }
    }
    public static void main(String [] args){
        Chaining<Integer, Character> c = new Chaining<>();
        char ch = 'a';
        int i;
        for(i=0;i<10;i++)c.put(i,ch++);
        for(i=1;i<7;i++)c.put(i*13,ch++);
        for(i=1;i<7;i++)c.put(i*13+1,ch++);
        c.delete(66);
        c.delete(1);
        c.delete(14);
        c.delete(79);
        c.delete(2);
        c.delete(78);
        c.delete(52);
        c.print();        

    }
}
