import java.util.NoSuchElementException;
//큐를 배열로 구현
//rear에서 삽입 front에서 삭제
//메모리 활용 비효율적 -> 대안 circular queue
public class Queue1<E> {
    private E [] arr;
    private int rear, front,size;
    public Queue1(){
        rear = front = -1;
        size =1; //배열 크기
        arr = (E[])new Object[1];
    }
    public void resize(int newSize){
        Object [] t = new Object[newSize];
        for(int i = 0, j=front+1 ; i<(rear-front);i++,j++){t[i]=arr[j];}
        rear = rear -(front+1);
        front  = -1;
        arr = (E[])t;
        size = newSize;
    }
    public void addQueue(E newItem){
        if(rear+1 == size) resize(size*2);
        arr[++rear]=newItem;
    }
    public E delQueue(){
        if(front == rear) throw new NoSuchElementException();
        E delItem = arr[++front];
        return delItem;
    }
    public E peek(){return arr[front];}//제일 먼저 들어온 놈 꺼내지 않고 내용만 보여준다.
    public int getSize(){return size;}
    public int getRear(){return rear;}
    public int getFront(){return front;}
    public static void main(String [] args){
        Queue1<Integer> q = new Queue1<Integer>();
        for(int i = 0; i<30;i++){q.addQueue(i);}
        for(int i = 0; i<15;i++)System.out.print(q.delQueue()+" ");
        System.out.println("");
        System.out.println(q.getFront()+" "+q.getRear());
        for(int i = 0; i<20;i++)q.addQueue(i+100);
        System.out.println(q.getFront()+" "+q.getRear());
        for(int i = 0; i<35;i++)System.out.print(q.delQueue()+" ");

    }
}
