import java.util.NoSuchElementException;

class CDNode<E>{
    private E item;
    private CDNode<E> left;
    private CDNode<E> right;
    public CDNode(E newItem, CDNode<E> left, CDNode<E> right){
        this.item = newItem;
        this.left= left;
        this.right=right;
    }
    public E getItem(){return item;}
    public CDNode<E> getLeft(){return left;}
    public CDNode<E> getRight(){return right;}
    public void setItem(E newItem){this.item=newItem;}
    public void setLeft(CDNode<E> left){this.left=left;}
    public void setRight(CDNode<E> right){this.right=right;}
}
public class CDList<E>{
    protected int size;
    protected CDNode<E> head;
    public CDList(){
        head = new CDNode<E> (null, null, null);
        head.setLeft(head);
        head.setRight(head);
        size = 0;
    }
    public void insert(E newItem, String mode){ // left / right
        CDNode<E> newNode;
        if("right".equals(mode)){ //head 오른쪽에 연결
            newNode = new CDNode<E>(newItem,head.getLeft(),head);
            head.getLeft().setRight(newNode);
            head.setLeft(newNode);
        }else{ //head 왼쪽에 연결
            newNode = new CDNode<E>(newItem,head,head.getRight());
            head.getRight().setLeft(newNode);
            head.setRight(newNode);
        }
        size++;
    }
    //원하는 노드 우측에 연결
    public void insertAt(E newItem, CDNode<E> horse){
        CDNode<E> newNode = new CDNode<E>(newItem, horse, horse.getRight());
        horse.getRight().setLeft(newNode);
        horse.setRight(newNode);
        size++;
    }
    public CDNode<E> search(E target){
        CDNode<E> tmp=head;
        do{
            tmp =tmp.getRight();
            if(target.equals(tmp.getItem())) return tmp;
        }while(!tmp.equals(head));
        return null;
    }

    public void delete(CDNode<E> del){
        if(head.equals(del))System.out.println("No permission!");
        else{
            del.getRight().setLeft(del.getLeft());
            del.getLeft().setRight(del.getRight());
            del.setRight(null);
            del.setLeft(null);
            size--;
        }
    }
    public void printALL(){
        CDNode<E> tmp = head.getRight();
        while(!tmp.equals(head)){
            System.out.print(tmp.getItem()+" ");
            tmp=tmp.getRight();
        }
        System.out.println("");
    }
    public static void main(String [] args){
        CDList<Integer> cdList = new CDList<Integer>();
        for(int i = 0 ; i<10; i++)cdList.insert(i, "right");
        cdList.printALL();
        CDNode<Integer> at = cdList.search(6);
        cdList.insertAt(77, at);
        cdList.insertAt(88, cdList.search(3));
        cdList.printALL();
        cdList.delete(cdList.search(3));
        cdList.delete(cdList.search(0));
        cdList.delete(cdList.search(9));
        cdList.printALL();
    }
}
