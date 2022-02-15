import java.util.NoSuchElementException;
/* 노드 클래스: C의 구조체와 유사
    제네릭을 잘 받아들일 필요가 있을 듯...*/
class Node <E>{
    private E item;
    private Node<E> next;
    public Node(E newItem, Node<E> node){ //생성자(새로운 아이템, 연결된 노드)
        this.item = newItem;
        this.next = node;
    }
    //get, set method
    public E getItem(){return item;}
    public Node<E> getNext(){return next;}
    public void setItem(E newItem){this.item = newItem;}
    public void setNext(Node<E> newNext){this.next = newNext;}
}
/* 단일 링크드 리스트, 노드를 연결시켜서 구현 */
public class SingleLinkedList<E>{
    private Node<E> head;
    private int size;
    public SingleLinkedList(){
        head = null;
        size = 0;
    }
    //탐색: 찾고자 하는 아이템의 위치(노드)를 찾는다. 노드를 리턴. insertAfter()와 deleteAfter()에서 노드 p를 찾기 위해 사용.
    public Node<E> search(E target){
        if(head == null) throw new NoSuchElementException();
        Node<E> p = head;
        for(int k = 0 ; k<size;k++){
            //System.out.println(p.getItem()+ " "+target);
            if(p.getItem().equals(target)) return p; //객체는 equals()!!!!!
            p = p.getNext();
        }
        return null; //찾고자 하는 아이템(target)이 리스트에 없을 경우 null을 리턴
    }
    /* 헤드부분에 아이템 삽입 */
    public void insertFront(E newItem){
        head = new Node<E>(newItem, head); //새로운 헤드 노드에 기존의 헤드 노드를 연결
        size++;
    }
    /* 노드 p 뒤에 새로운 노드를 삽입 */
    public void insertAfter(E newItem, Node<E> p){
        p.setNext(new Node<E>(newItem, p.getNext()));
        size++;
    }
    /* head노드를 삭제하고 해당 노드의 아이템을 리턴 */
    public E deleteFront(){
        if(head == null) throw new NoSuchElementException();
        Node<E> t = head;
        head = head.getNext();
        size--;
        return t.getItem();
    }
    /* 노드 p의 연결된 next노드를 삭제하고 해당 노드의 아이템을 리턴 */
    public E deleteAfter(Node<E> p){
        if(p == null) throw new NoSuchElementException();
        Node<E> t = p.getNext();
        p.setNext(t.getNext());
        t.setNext(null);
        size--;
        return t.getItem();
    }
    public void printALL(){
        Node<E> p = head;
        while(p!=null){
            System.out.print(p.getItem()+ " ");
            p=p.getNext();
        }
        System.out.println(" ");
    }
    public static void main(String [] args){
        SingleLinkedList<Integer> sList = new SingleLinkedList<Integer>();
        for(int i = 0; i<30;i++)sList.insertFront(i+10);
        sList.printALL();
        sList.insertAfter(777, sList.search(15));
        sList.printALL();
        sList.deleteAfter(sList.search(777));
        sList.printALL();
        sList.deleteFront();
        sList.printALL();
    }
}
