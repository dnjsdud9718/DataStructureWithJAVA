import java.util.EmptyStackException;
import java.util.Scanner;
//배열로 구현
//회문 검사하기
public class Stack1<E> {
    private int top;
    private E [] s;
    public Stack1(){
        top = -1;
        s = (E[])new Object[1];
    }
    public void push(E newItem){
        if(size() == s.length) resize(2*s.length);
        s[++top] = newItem;
    }
    public E pop(){
        if(size() == 0) throw new EmptyStackException();
        E item = s[top];
        s[top--]=null;
        if(size()>0 && size()==s.length/4) resize(s.length/2);
        return item;
    }
    public int size(){return top+1;}
    public void resize(int newSize){
        Object [] t = new Object[newSize];
        for(int i=0;i<size();i++) t[i] = s[i];
        s = (E[])t;
    }
    public E peek(){
        if(size()==0)throw new EmptyStackException();
        return s[top];
    }
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Stack1<Character> st = new Stack1<Character>();
        String str = scanner.nextLine();
        char cArr [] = str.toCharArray();
        int middle = cArr.length/2;
        for(int i = 0; i<middle; i++) st.push(cArr[i]);
        if(cArr.length%2 != 0) middle++;
        int f =1;
        for(int i = middle; i<cArr.length;i++){
            if(st.peek() != cArr[i]){f=0; break;}
            System.out.println(st.pop()+" : "+cArr[i]);
        }
        if(f==1 && st.size()==0)System.out.println("GOOD!");
        else System.out.println("BAD!");
        scanner.close();
    }
}

