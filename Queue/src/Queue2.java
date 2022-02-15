import java.util.NoSuchElementException;
//링크드리스트로 큐를 구현
//rear에서 삽입, front에서 삭제
//(front) n1 -> n2 -> n3 -> n4(rear) n1이 가장 먼저 들어왔고, n4가 가장 늦게 들어왔다.
class qNode<E>{
    E item;
    qNode<E> next;
    public qNode(E newItem, qNode<E> nextNode){
        this.item=newItem; this.next= nextNode;
    }
    public E getItem(){return item;}
    public qNode<E> getNext(){return next;}
    public void setItem(E newItem){this.item=newItem;}
    public void setNext(qNode<E> newNode){this.next=newNode;}
}
public class Queue2<E> {   
    protected qNode<E> front, rear;
    protected int size;
    public Queue2(){
        size = 0;
        front = rear = null;
    }
    public boolean isEmpty(){if(size == 0) return true; else return false;}
    public E peek(){
        if(isEmpty())throw new NoSuchElementException();
        return front.getItem(); //제일 먼저 들어온 놈을 보여준다.
    }
    public void addQueue2(E newItem){
        qNode<E> newNode = new qNode<E>(newItem, null);
        if(isEmpty()) front = newNode;
        else rear.setNext(newNode);
        rear = newNode;
        size++;
    }
    public E delQueue2(){
        if(isEmpty()) throw new NoSuchElementException();
        qNode<E> del = front;
        if(front  == rear) front = rear =null; //size == 1
        else front = front.getNext();
        size --;
        del.setNext(null);
        return del.getItem();
    }
    public int getSize(){return size;}
    public static void main(String [] args){
        Queue2<Integer> q = new Queue2<Integer>();
        q.addQueue2(10);
        q.addQueue2(11);
        System.out.println(q.delQueue2());
        System.out.println(q.delQueue2());

    }
}
