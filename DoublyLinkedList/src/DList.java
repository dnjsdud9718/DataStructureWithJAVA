import java.util.NoSuchElementException;
/*
더블 링크드 리스트
노드에 연결된 링크가 두개 left & right
head와 tail의 item에는 null 값이 들어가 있다. 
head와 tail이 null이라는 의미는 아니다.
*/
class DNode<E>{
    private E item;
    private DNode<E> left;
    private DNode<E> right;
    public DNode(E newItem, DNode<E> left, DNode<E> right){
        this.item=newItem;
        this.left=left;
        this.right=right;
    }
    //get & set method
    public E getItem(){return item;}
    public DNode<E> getLeft(){return left;}
    public DNode<E> getRight(){return right;}

    public void setItem(E newItem){this.item = newItem;}
    public void setLeft(DNode<E> lt){this.left=lt;}
    public void setRight(DNode<E> rt){this.right=rt;}
}
public class DList<E>{
    protected DNode<E> head, tail;
    protected int size;
    public DList(){
        head = new DNode<E>(null,null, null);
        tail = new DNode<E>(null,head,null);
        head.setRight(tail);
        size=0;
    }
    //p의 left에 삽입.
    public void insertBefore(E newItem, DNode<E> p){
        DNode<E> q = p.getLeft();
        DNode<E> newNode = new DNode<E>(newItem,q,p);
        p.setLeft(newNode);
        q.setRight(newNode);
        size++;
    }
    //p의 right에 삽입
    public void insertAfter(E newItem, DNode<E> p){
        DNode<E> q = p.getRight();
        DNode<E> newNode = new DNode<E>(newItem, p,q);
        q.setLeft(newNode);
        p.setRight(newNode);
        size++;
    }
    public E delete(DNode<E> x){
        if(x == null) throw new NoSuchElementException();
        E r = x.getItem();
        x.getLeft().setRight(x.getRight());
        x.getRight().setLeft(x.getLeft());
        x.setLeft(null);
        x.setRight(null);
        size--;
        return r;
    }   
    public DNode<E> search(E target){
        DNode<E> tmp = head.getRight();
        for(int k = 0; k<size;k++){
            if(tmp.getItem().equals(target)) return tmp;
             tmp = tmp.getRight();
        }
        return null;
    }
    public void printAll(){
        DNode<E> tmp = head.getRight();
        while(!tmp.equals(tail)){
            System.out.print(tmp.getItem()+" ");
            tmp = tmp.getRight();
        }
    }
    public void printAllRevere(){
        DNode<E> tmp = tail.getLeft();
        while(!tmp.equals(head)){
            System.out.print(tmp.getItem()+" ");
            tmp = tmp.getLeft();
        }
    }

    public static void main(String [] args){
        DList<Integer> dList = new DList<Integer>();
        dList.insertBefore(100, dList.tail);
        dList.printAll();
        System.out.println(" ");
        for(int i = 0;i<30;i++)dList.insertAfter(i, dList.head);
        dList.printAll();
        System.out.println(" ");
        dList.insertBefore(777, dList.search(15)); 
        dList.printAll();
        System.out.println(" ");
        dList.printAllRevere();
        System.out.println(" ");
        dList.delete(dList.search(3));
        dList.delete(dList.search(23));
        dList.delete(dList.search(13));
        dList.printAll();
        System.out.println(" ");
        dList.printAllRevere();
    }
}