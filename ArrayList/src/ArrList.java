import java.util.NoSuchElementException;

public class ArrList <E>{
    private int size;
    private E a[];
    public ArrList(){
        size = 0;
        a = (E[])new Object[1];
    }
    /* 배열을 동적으로 관리하기 위해 newSize만큼의 공간을 확보 */
    public void resize(int newSize){
        Object [] tmp = new Object[newSize];
        for(int i = 0; i < size; i++) tmp[i] = a[i];
        a = (E[])tmp;
    }
    /* index k에 위치한 데이터를 리턴 */
    public E peek(int k){
        if(size == 0) throw new NoSuchElementException(); //empty일 경우
        return a[k];
    }
    /* 배열의 끝에 아이템을 삽입 O(1) */
    public void insertLast(E newItem){
        if(size == a.length) resize(2*a.length); //배열일 full이면 size를 두배로 늘려준다.
        a[size++] = newItem;
    }
    /* 원하는 위치에 아이템을 삽입 O(n) */
    public void insertAt(int k, E newItem){
        if(size == a.length) resize(2*a.length);
        for(int i = size-1; i >= k; i--) a[i+1] = a[i];
        a[k] = newItem;
        size++;
    }
    /* 아이템 삭제 O(n) & 크기 줄이기 */
    public E deleteAt(int k){
        if(size == 0) new NoSuchElementException();
        E item = a[k];
        for(int i = k; i < size-1; i++)a[i] = a[i+1];
        size--;
        if(size > 0 && size == a.length/4) resize(a.length/2);
        return item;
    }
    public int getSize(){
        return size;
    }
    public static void main(String [] args){
        ArrList<Integer> arrList = new ArrList<Integer>();
        System.out.println("초기 size: "+arrList.getSize());
        for(int i = 0; i < 10; i++){
            arrList.insertLast(i);
            System.out.println("size: "+arrList.getSize());
        }
        arrList.insertAt(0, 100);
        System.out.println(arrList.peek(0));
        for(int i = arrList.getSize()-1; i>=0; i--){
            System.out.print(arrList.deleteAt(i)+" ");
        }
    }
}