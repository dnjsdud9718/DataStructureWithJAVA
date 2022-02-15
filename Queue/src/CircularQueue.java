import java.util.NoSuchElementException;

//circular queue를 배열을 이용해서 구현
//n개 공간 중 n-1개 공간만 아이템을 삽입
//rear+1 == front -> full
//rear == front -> empty
public class CircularQueue<E> {
    private E [] arr;
    private int rear, front, size; //size는 item개수
    public CircularQueue(){
        rear = front = size = 0;
        arr = (E[])new Object[2]; //배열의 초기 크기가 2지만 실제 넣을 수 있는 아이템은 한 개
    }
    public E peek(){
        if(isEmpty()) throw new NoSuchElementException();
        return arr[front+1];
    }
    public void resize(int newSize){ 
        Object [] t = new Object[newSize];
        for(int i =1,j=front+1;i<size+1;i++,j++) t[i] = arr[j%arr.length]; //그림 떠올리면서 이해하기
        rear =size;
        front = 0;
        arr = (E[])t;
    }
    public void addQueue(E newItem){
        if(isFull()) resize(arr.length*2);
        rear=(rear+1)%arr.length;
        arr[rear]=newItem;
        size++;
    }
    public E delQueue(){
        if(isEmpty()) throw new NoSuchElementException();
        front = (front+1)%arr.length;
        size --;
        E del =arr[front];
        arr[front]=null;
        if(size>0 && size == arr.length/4)resize(arr.length/2);
        return del;
    }
    public boolean isEmpty(){
        if(rear == front) return true;
        else return false;
    }
    public boolean isFull(){
        if((rear+1)%arr.length == front) return true;
        else return false;
    }
    public int getSize(){return size;}
    public static void main(String [] args){
        CircularQueue<Integer> q = new CircularQueue<Integer>();
        for(int i = 0;i<100;i++)q.addQueue(i);
        for(int i = 0;i<100;i++)System.out.print(q.delQueue()+" ");
        System.out.println("");
        
        for(int i = 0; i<10; i++)q.addQueue(i*2);
        
        for(int i = 0; i<10; i++)System.out.print(q.delQueue()+" ");
        q.addQueue(777);
        System.out.println(q.getSize()+" "+q.delQueue());
    }
}
