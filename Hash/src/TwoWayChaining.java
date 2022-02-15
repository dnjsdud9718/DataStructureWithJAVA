import java.util.*;
public class TwoWayChaining <Key, Value>{
	private final int M = 13;
	private static class Node{
		Object key;
		Object value;
		Node next;
		public Node(Object k, Object v, Node n) {
			key = k;
			value = v;
			next = n;
		}
		public Object getKey() {return key;}
		public Object getValue() {return value;}
		public Node getNext() {return next;}
	}
	private int hash(Key k) {
		return (k.hashCode() & 0x7fffffff)%M;
	}
	private Node [] a = new Node[M];
	private int [] link = new int[M];
	public void put(Key k, Value v) {
		int i = hash(k);
		int d = (i+7)%M;
		if(link[i] > link[d]) i = d;
		for(Node x = a[i];x!=null;x=x.getNext()) {
			if(k.equals(x.key)) {
				x.value=v;
				return;
			}
		}
		a[i] = new Node(k,v,a[i]);
		link[i]++;
	}
	public Value get(Key k) { //데이터가 어디 저장되었는지 알 수 없네
		int i = hash(k);
		int d = (i+7)%M;
		for(Node x = a[i];x!=null;x=x.getNext()) {
			if(k.equals(x.key)) return (Value)x.value;
		}
		for(Node x = a[d];x!=null;x=x.getNext()) {
			if(k.equals(x.key)) return (Value)x.value;
		}
		return null;
	}
	public void delete(Key k) {
		int i = hash(k);
		int l = link[i];
		a[i] = delete(a[i],k,i);
		if(link[i] < l) return;
		int d = (i+7)%M;
		a[d] = delete(a[d],k,d);
	}
	private Node delete(Node x, Key k, int i) {
		if(x==null) return null;
		if(k.equals(x.key)) {link[i]--; return x.getNext();}
		x.next = delete(x.getNext(),k,i);
		return x;
	}
	public void print() {
		for(int i = 0; i<M;i++) {
			System.out.print("link "+i+":"+link[i]+"개 ");
			for(Node x = a[i];x!=null;x=x.getNext()) {
				System.out.print("<"+x.getKey()+","+x.getValue()+"> ");
			}
			System.out.println(" ");
		}
	}
	public static void main(String[] args) {
		TwoWayChaining<Integer, Character> t = new TwoWayChaining<>();
		char ch = 'a';
		for(int i = 0; i<50;i++) t.put(i,ch++);
		t.delete(9);
		t.print();
		//System.out.println(t.get(0));
		//System.out.println(t.get(49));
		//System.out.println(t.get(10));
		
	}

}
