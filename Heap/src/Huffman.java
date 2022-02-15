public class Huffman {
    private HEntry [] a;
    private int N;
    public Huffman(HEntry[] harray, int initSize){
        a = harray; N = initSize;
    }
    public int size(){return N;}
    public void createHeap(){
        for(int i = N/2;i>0;i--) downHeap(i);
    }
    private void downHeap(int i){
        while(i*2<=N){
            int j = i*2;
            if(j < N && a[j].getKey() > a[j+1].getKey())j++;
            if(a[j].getKey() >= a[i].getKey()) break;
            HEntry t = a[i];
            a[i] = a[j];
            a[j] = t;
            i = j;
        }
    }
    public HEntry deleteMin(){
        HEntry t = a[1];
        a[1] = a[N--];
        a[N+1] = null;
        downHeap(1);
        return t;
    }
    public void insert(HEntry h){
        a[++N] = h;
        upHeap(N);
    }
    private void upHeap(int i){
        while(i>1){
            int j = i/2;
            if(a[i].getKey() > a[j].getKey()) break;
            HEntry t = a[i];
            a[i] = a[j];
            a[j] = t;
            i= j;
        }
    }
    public void print(){
        for(int i = 1; i<=N; i++)System.out.print("<"+a[i].getKey()+","+a[i].getValue()+"> ");
        System.out.println("");
    }
    public HEntry createTree(){
        while(size()>1){
            HEntry e1 = deleteMin();
            HEntry e2 = deleteMin();
            HEntry temp = new HEntry(e1.getKey()+e2.getKey(), e1.getValue()+e2.getValue(), e1, e2, "");
            insert(temp);
        }
        return deleteMin();
    }
    public void HuffmanCode(HEntry e){
        if(e.getLeft() != null){
            e.getLeft().setCode(e.getCode()+"0");
            HuffmanCode(e.getLeft());
        }
        if(e.getRight() != null){
            e.getRight().setCode(e.getCode()+"1");
            HuffmanCode(e.getRight());
        }
        if(e.getLeft() == null && e.getRight()==null)System.out.println(e.getValue()+": "+e.getCode());
    }
    public static void main(String [] args){
        HEntry [] entry = new HEntry[10];
        entry[1] = new HEntry(60, "a", null, null, null);
        entry[2] = new HEntry(20, "b", null, null, null);
        entry[3] = new HEntry(30, "c", null, null, null);
        entry[4] = new HEntry(35, "d", null, null, null);
        entry[5] = new HEntry(40, "e", null, null, null);
        entry[6] = new HEntry(90, "f", null, null, null);
        Huffman huf = new Huffman(entry, 6);
        huf.print();
        huf.createHeap();
        huf.print();

        HEntry t = huf.createTree();
        System.out.println(t.getKey()+", "+t.getValue()+" "+t.getCode());
        huf.HuffmanCode(t);
    }
}
