import java.util.NoSuchElementException;
//single cicular linked-list
class CNode<E>{
    private E item;
    private CNode<E> next;
    public CNode(E newItem, CNode<E> next){
        this.item = newItem;
        this.next = next;
    }
    //set & get method
    public E getItem(){return item;}
    public CNode<E> getNext(){return next;}
    
    public void setItem(E newItem){this.item = newItem;}
    public void setNext(CNode<E> next){this.next=next;}
}
public class CList<E>{
    protected CNode<E> last;
    protected int size;
    public CList(){
        last = null;
        size = 0;
    }
    public void insert(E newItem, String mode){ //mode null or "rear"
        CNode<E> newNode = new CNode<E>(newItem,null);
        if(last == null){
            newNode.setNext(newNode);
            last = newNode;
        }
        else{
            newNode.setNext(last.getNext());
            last.setNext(newNode);
            if("rear".equals(mode)){ //last가 가리키는 노드가 바뀐다.
                last = newNode;
            }
        }
        size++;
    }
    //cNode 뒤에 삽입이고 cNode는 이미 serch()를 통해 찾았다.
    public void insertAt(E newItem, CNode<E> cNode){
        CNode<E> newNode = new CNode<E>(newItem,null);
        if(last == null){
            newNode.setNext(newNode);
            last = newNode;
        }
        else{
            newNode.setNext(cNode.getNext());
            cNode.setNext(newNode);
        }
        size++;
    }
    //마지막 노드를 리턴하며 삭제
    public CNode<E> delete(){
        if(last == null) throw new NoSuchElementException();
        CNode<E> del = last.getNext();
        if(last == del) last = null;
        else{
            last.setNext(del.getNext());
            del.setNext(null);
        }
        size--;
        return del;
    }
    public void printALL(){
        CNode<E> tmp = last;
        do{
            tmp=tmp.getNext();
            System.out.print(tmp.getItem()+" ");
        }while(!tmp.equals(last));
    }
    public CNode<E> search(E target){
        CNode<E> tmp = last;
        do{
            tmp = tmp.getNext();
            if(tmp.getItem().equals(target)) return tmp;
        }while(!tmp.equals(last));
        return null;
    }
    public static void main(String [] args){
        CList<Integer> cList = new CList<>();
        for(int i = 0;i<10;i++){
            cList.insert(i+1, null);
        }
        cList.printALL();
        System.out.println("");
    
        cList.insertAt(77,cList.search(4));
        cList.printALL();
        System.out.println("");

        CNode<Integer> d = cList.delete();
        System.out.println(d.getItem());
    }
}