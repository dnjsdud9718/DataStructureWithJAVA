import java.util.EmptyStackException;
import java.util.Scanner;

//링크드 리스트로 스택 구현하기
//괄호 짝 맞추기
class SNode<E> {
    private SNode<E> next;
    private E item;
    public SNode(E newItem, SNode<E> next){
        this.next = next; this.item = newItem;
    }
    public E getItem(){return item;}
    public SNode<E> getNext(){return next;}
    public void setItem(E newItem){this.item = newItem;}
    public void setNext(SNode<E> newNext){this.next = newNext;}
}
public class Stack2<E> {
    protected SNode<E> top;
    protected int size;
    public Stack2(){
        top = null;
        size = 0;
    }
    public int size(){return size;}
    public boolean isEmpty(){
        if(size == 0) return true;
        else return false;
    }
    public void push(E newItem){
        SNode<E> newNode = new SNode<E>(newItem, top);
        top = newNode;
        size++;
    }
    public E pop(){
        if(isEmpty())throw new EmptyStackException();
        SNode<E> t = top;
        top = top.getNext();
        t.setNext(null);
        size--;
        return t.getItem();
    }
    public E peek(){
        if(isEmpty()) throw new EmptyStackException();
        return top.getItem();
    }
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Stack2<Character> st3 = new Stack2<Character>();
        System.out.print("입력: ");
        String input = scanner.nextLine();
        char [] c = input.toCharArray();
        int flag = 1;
        for(int i = 0;i<c.length;i++){
            if(c[i] == '(' || c[i] == '{') st3.push(c[i]);
            else{
                char lParen = st3.pop();
                System.out.println(lParen+" "+c[i]);
                switch(lParen){
                    case '(':   if(c[i] == '}') {flag = 0;}
                                break;
                    case '{':   if(c[i] == ')') {flag = 0;}
                                break;
                }
                if(flag == 0 )break;
            }
        }
        if(st3.isEmpty() && flag == 1) System.out.println("GOOD");
        else System.out.println("NO!");
        scanner.close();
    }
}
