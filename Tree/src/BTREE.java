import java.util.*;

public class BTREE<Key extends Comparable<Key>,Value>{
    private static final int T = 3; //T>=2
    private static final class Node{
        private Entry [] entry;
        private int n; //노드에 포함된 키의 수 최소 T-1 최대 2*T-1
        private boolean leaf;//리프 노드 T/F
        private Node parent; //부모 노드
        private int p; //노드가 부모에 몇번째 오프셋인지 저장
        public Node(){
            entry = new Entry[2*T];
            for(int i = 0; i<2*T;i++) entry[i] = new Entry();
            n = 0;
            leaf = true;
            p=-1; //부모가 null일 때, 즉, root node인 경우
            parent = null;
        }
    }
    private static class Entry{
        private Comparable key;
        private Object value;
        private Node child;
    }
    private Node root;
    public BTREE(){root = new Node();}
    //get()
    public Value get(Key k){
        if(root == null) return null;
        return search(root, k);
    }
    private Value search(Node x, Key k){
        int  i=0;
        while(i < x.n && k.compareTo((Key)x.entry[i].key) > 0) i++;
        if(i < x.n && k.compareTo((Key)x.entry[i].key) == 0) return (Value)x.entry[i].value;
        if(x.leaf) return null;
        else return search(x.entry[i].child, k);
    }
    //split()
    private Node split(Node x, int index){//x는 리프가 아니다.full도 아니다.
        Node y = x.entry[index].child; //왼쪽자식
        Node z = new Node(); //오른쪽자식
        z.leaf = y.leaf;
        z.parent = y.parent;
        int i;
        for(i = 0; i < T-1; i++){
            z.entry[i].key = y.entry[i+T].key;
            z.entry[i].value = y.entry[i+T].value;
            if(!z.leaf) {
            z.entry[i].child = y.entry[i+T].child;
            z.entry[i].child.parent = z;
            z.entry[i].child.p = i;
            }
        }
        if(!z.leaf) {
        	z.entry[i].child = y.entry[i+T].child;
	        z.entry[i].child.parent = z;
	        z.entry[i].child.p = i;
        }
        z.n = y.n = T-1;
        for(i = x.n; i > index; i--){
            x.entry[i+1].child = x.entry[i].child;
            x.entry[i+1].child.p = i+1;
            x.entry[i].key = x.entry[i-1].key;
            x.entry[i].value = x.entry[i-1].value;
        }
        x.entry[index+1].child = z;
        x.entry[index+1].child.p = index+1;
        x.entry[index].key = y.entry[T-1].key;
        x.entry[index].value = y.entry[T-1].value;
        x.n++;
        return x;
    }
    //put()
    public void put(Key k,Value v){
        Node r = root;
        if(r.n == 2*T-1){
            Node s = new Node();
            root =s;
            s.leaf = false;
            s.parent = null;
            s.entry[0].child = r;
            r.p = 0;
            r.parent =s;
            s = split(s,0);
            root = put(s,k,v);
        }else{
            root = put(r,k,v);
        }
    }
    private Node put(Node x, Key k, Value v){
        int i = x.n-1;
        if(x.leaf){
            while(i>=0 && k.compareTo((Key)x.entry[i].key) < 0){
                x.entry[i+1].key = x.entry[i].key;
                x.entry[i+1].value = x.entry[i].value;
                i--;
            }
            i++;  
            x.entry[i].key = k;
            x.entry[i].value = v;
            x.n++;
        }else{
            while(i>=0 && k.compareTo((Key)x.entry[i].key) < 0) i--;
            i++;
            if(x.entry[i].child.n == 2*T-1){
                x = split(x,i);
                if(k.compareTo((Key)x.entry[i].key) > 0) i++;
            }
            x.entry[i].child = put(x.entry[i].child, k, v);
        }
        return x;
    }
    //delete() -> min() deleteMin()이 필요하네
    public Entry min() { //key, value만 사용할 것.
    	if(root == null) return null;
    	return min(root);
    }
    private Entry min(Node x) {
    	if(x.leaf) return x.entry[0];
    	return min(x.entry[0].child);
    }
    public void deleteMin() { //leaf노드에 있는 entry를 지운다!!!
    	if(root == null) return;
    	else root = deleteMin(root);
    }
    private Node deleteMin(Node x) {
    	int i;
    	if(x.leaf) {
    		for(i=0; i<x.n-1;i++) {
    			x.entry[i].key = x.entry[i+1].key;
    			x.entry[i].value = x.entry[i+1].value;
    		}
    		x.n--;
    	}else x.entry[0].child = deleteMin(x.entry[0].child);
    	if(x.n < T-1 && x.parent != null) {
    		//이동 연산
    		if(x.parent.entry[x.p+1].child.n > T-1) {x.parent = movingLeft(x.parent, x.p);}
    		//통합 연산
    		else {x.parent = mergingRight(x.parent, x.p);}
    	}
    	return x;
    }
    private Node movingLeft(Node x, int index) { //x는 leaf가 아니다.
    	Node y = x.entry[index].child;  
    	Node z = x.entry[index+1].child;
    	y.entry[y.n].key = x.entry[index].key;
    	y.entry[y.n].value = x.entry[index].value;
    	y.n++;
    	y.entry[y.n].child = z.entry[0].child;
    	y.entry[y.n].child.parent = y;
    	y.entry[y.n].child.p = y.n;
    	
    	x.entry[index].key = z.entry[0].key;
    	x.entry[index].value = z.entry[0].value;
    	int i;
    	for(i = 0; i< z.n-1; i++) {
    		z.entry[i].key = z.entry[i+1].key;
    		z.entry[i].value = z.entry[i+1].value;
    		z.entry[i].child = z.entry[i+1].child;
    		z.entry[i].child.p = i;
    	}
    	z.entry[i].child = z.entry[i+1].child;
		z.entry[i].child.p = i;
    	z.n--;   
    	return x;
    }
    private Node movingRight(Node x, int index) { //x는 leaf가 아니다.
    	Node z = x.entry[index].child;  
    	Node y = x.entry[index-1].child;
    	int i;
    	for(i=z.n;i>0;i--) {
    		z.entry[i].key = z.entry[i-1].key;
    		z.entry[i].value = z.entry[i-1].value;
    		if(!z.leaf) {
    			z.entry[i+1].child = z.entry[i].child;
        		z.entry[i+1].child.p = i+1;
    		}
    	}
    	if(!z.leaf) {
			z.entry[i+1].child = z.entry[i].child;
    		z.entry[i+1].child.p = i+1;
		}
    	z.entry[0].key = z.parent.entry[index-1].key;
    	z.entry[0].value = z.parent.entry[index-1].value;
    	if(!z.leaf) {
    		z.entry[0].child = y.entry[y.n].child;
        	z.entry[0].child.p = 0;
        	z.entry[0].child.parent = z;
    	}
    	z.n++;
    	x.entry[index-1].key = y.entry[y.n-1].key;
    	x.entry[index-1].value = y.entry[y.n-1].value;
    	y.n--;
    	return x;
    }
    private Node mergingRight(Node x, int index) {
    	Node y = x.entry[index].child;
    	Node z = x.entry[index+1].child;
    	int i;
    	for(i=0;i<z.n;i++) {
    		y.entry[y.n+1+i].key = z.entry[i].key;
    		y.entry[y.n+1+i].value = z.entry[i].value;
    		if(!y.leaf) {
	    		y.entry[y.n+1+i].child = z.entry[i].child;
	    		y.entry[y.n+1+i].child.p = y.n+1+i;
    		}
    	}
    	if(!y.leaf) {
    		y.entry[y.n+1+i].child = z.entry[i].child;
    		y.entry[y.n+1+i].child.p = y.n+1+i;
		}
    	y.entry[y.n].key = x.entry[index].key;
    	y.entry[y.n].value = x.entry[index].value;
    	y.n = y.n+z.n+1;
    	for(i=index;i<x.n-1;i++) {
    		x.entry[i].key = x.entry[i+1].key;
    		x.entry[i].value = x.entry[i+1].value;
    		x.entry[i+1].child = x.entry[i+2].child;
    		x.entry[i+1].child.p = i+1;
    	}
    	x.n--;
    	return x; 
    }
    private Node mergingLeft(Node x, int index) {
    	Node z = x.entry[index].child;
    	Node y = x.entry[index-1].child;
    	int i;
    	for(i=0;i<z.n;i++) {
    		y.entry[y.n+1+i].key = z.entry[i].key;
    		y.entry[y.n+1+i].value = z.entry[i].value;
    		if(!y.leaf) {
	    		y.entry[y.n+1+i].child = z.entry[i].child;
	    		y.entry[y.n+1+i].child.p = y.n+1+i;
	    		y.entry[y.n+1+i].child.parent = y;
    		}   
    	}
    	if(!y.leaf) {
    		y.entry[y.n+1+i].child = z.entry[i].child;
    		y.entry[y.n+1+i].child.p = y.n+1+i;
    		y.entry[y.n+1+i].child.parent = y;
		}
    	y.entry[y.n].key = x.entry[index-1].key;
    	y.entry[y.n].value = x.entry[index-1].value;
    	y.n = y.n+z.n+1;
    	for(i=index-1;i<x.n-1;i++) {
    		x.entry[i].key = x.entry[i+1].key;
    		x.entry[i].value = x.entry[i+1].value;
    		x.entry[i+1].child = x.entry[i+2].child;
    		x.entry[i+1].child.p = i+1;
    	}
    	x.n--;
    	return x; 
    }
    public void delete(Key k) {
    	if(root == null) return;
    	root = delete(root,k);
    }
    private Node delete(Node x, Key k) {
    	int i = 0;
    	while(i<x.n && k.compareTo((Key)x.entry[i].key) > 0) i++;
    	if(!x.leaf) {
    		if(i < x.n && k.compareTo((Key)x.entry[i].key)==0) {
    			//deleteMin()
    			x.entry[i].key = min(x.entry[i+1].child).key;
    			x.entry[i].value = min(x.entry[i+1].child).value;
    			x.entry[i+1].child = deleteMin(x.entry[i+1].child);
    		}else {
    			//delete()를 재귀 호출한다.
    			x.entry[i].child = delete(x.entry[i].child,k);
    		}
    	}
    	else if(i<x.n && k.compareTo((Key)x.entry[i].key)==0){ //x.leaf -> true
    		//지운다.
    		while(i<x.n-1) {
    			x.entry[i].key = x.entry[i+1].key;
    			x.entry[i].value = x.entry[i+1].value;
    			i++;
    		}
    		x.n--;
    		if(x.n < T-1 && x.parent != null) {
    			if(x.p == 2*T-1) {
    				if(x.parent.entry[x.p-1].child.n > T-1) x.parent = movingRight(x.parent, x.p);
    				else x.parent = mergingLeft(x.parent, x.p);
    			}else if(x.p == 0){
    				if(x.parent.entry[x.p+1].child.n > T-1) x.parent = movingLeft(x.parent, x.p);
    				else x.parent = mergingRight(x.parent, x.p);
    			}else {
    				if(x.parent.entry[x.p+1].child.n > T-1) x.parent = movingLeft(x.parent, x.p);
        			else if(x.parent.entry[x.p-1].child.n > T-1) x.parent = movingRight(x.parent, x.p);
        			else x.parent = mergingRight(x.parent, x.p);
    			}	
    		}
    	}
    	return x;
    }
    private Key getKey(Node x, int i){
        return (Key)x.entry[i].key;
    }
    private Value getValue(Node x, int i){
        return (Value)x.entry[i].value;
    }
    public void levelOrder(Node x){
        Queue<Node> q = new LinkedList<Node>();
        Node t;
        q.add(x);
        while(!q.isEmpty()){
            t = q.remove();
            int i;
            for(i = 0; i<t.n;i++){
                System.out.println("<"+getKey(t, i)+","+getValue(t, i)+","+t.p+">");
                if(!t.leaf) q.add(t.entry[i].child);
            }
            if(!t.leaf) q.add(t.entry[i].child);
            System.out.println("=============");
        }
    }
    public Node getRoot(){return root;}
    public static void main(String [] args){
        BTREE<Integer, Character> b = new BTREE<>();
        char ch = 'a';
        for(int i = 0; i<24;i++) b.put(i,ch++);
        /*b.put(23,ch++);
		b.put(462,ch++);
		b.put(68,ch++);
		b.put(86,ch++);
		b.put(3,ch++);
		b.put(463,ch++);
		b.put(5567,ch++);
		b.put(64,ch++);
		b.put(22,ch++);
		b.put(1,ch++);
		b.put(24,ch++);*/
        b.delete(8);
        b.delete(9);
        b.delete(15);
        b.deleteMin();
        //System.out.println(b.min().key);
        b.levelOrder(b.getRoot());
    }
}


/*import java.util.*;

public class BTREE<Key extends Comparable<Key>,Value>{
    private static final int T = 3;
    private static final class Node{
        private Entry [] entry;
        private int n;
        private boolean leaf;
        public Node(){
            entry = new Entry[2*T];
            for(int i = 0; i<2*T;i++) entry[i] = new Entry();
            n = 0;
            leaf = true;
        }
    }
    private static class Entry{
        private Comparable key;
        private Object value;
        private Node child;
    }
    private Node root;
    public BTREE(){root = new Node();}
    //get()
    public Value get(Key k){
        if(root == null) return null;
        return (Value)search(root, k);
    }
    private Value search(Node x, Key k){
        int  i=0;
        while(i < x.n && k.compareTo((Key)x.entry[i].key) > 0) i++;
        if(i < x.n && k.compareTo((Key)x.entry[i].key) == 0) return (Value)x.entry[i].value;
        if(x.leaf) return null;
        else return search(x.entry[i].child, k);
    }
    //split()
    private Node split(Node x, int index){
        Node z = new Node();
        Node y = x.entry[index].child;
        z.leaf = y.leaf;
        int i;
        for(i = 0; i < T-1; i++){
            z.entry[i].key = y.entry[i+T].key;
            z.entry[i].value = y.entry[i+T].value;
            //y.leaf가 true면 무의미한 연산이다.
            z.entry[i].child = y.entry[i+T].child;
        }
        z.entry[i].child = y.entry[i+T].child;
        z.n = y.n = T-1;
        for(i = x.n; i > index; i--){
            x.entry[i+1].child = x.entry[i].child;
            x.entry[i].key = x.entry[i-1].key;
            x.entry[i].value = x.entry[i-1].value;
        }
        x.entry[index+1].child = z;
        x.entry[index].key = y.entry[T-1].key;
        x.entry[index].value = y.entry[T-1].value;
        x.n++;
        return x;
    }
    //put()
    public void put(Key k,Value v){
        Node r = root;
        if(r.n == 2*T-1){
            Node s = new Node();
            root =s;
            s.leaf = false;
            s.entry[0].child = r;
            s = split(s,0);
            root = put(s,k,v);
        }else{
            root = put(r,k,v);
        }
    }
    private Node put(Node x, Key k, Value v){
        int i = x.n-1;
        if(x.leaf){
            while(i>=0 && k.compareTo((Key)x.entry[i].key) < 0){
                x.entry[i+1].key = x.entry[i].key;
                x.entry[i+1].value = x.entry[i].value;
                i--;
            }
            i++;  
            x.entry[i].key = k;
            x.entry[i].value = v;
            x.n++;
        }else{
            while(i>=0 && k.compareTo((Key)x.entry[i].key) < 0) i--;
            i++;
            if(x.entry[i].child.n == 2*T-1){
                x = split(x,i);
                if(k.compareTo((Key)x.entry[i].key) > 0) i++;
            }
            x.entry[i].child = put(x.entry[i].child, k, v);
        }
        return x;
    }
    private Key getKey(Node x, int i){
        return (Key)x.entry[i].key;
    }
    private Value getValue(Node x, int i){
        return (Value)x.entry[i].value;
    }
    public void levelOrder(Node x){
        Queue<Node> q = new LinkedList<Node>();
        Node t;
        q.add(x);
        while(!q.isEmpty()){
            t = q.remove();
            int i;
            for(i = 0; i<t.n;i++){
                System.out.println("<"+getKey(t, i)+","+getValue(t, i)+">");
                if(!t.leaf) q.add(t.entry[i].child);
            }
            if(!t.leaf) q.add(t.entry[i].child);
            System.out.println("=============");
        }
    }
    //delete()
    public Node getRoot(){return root;}
    public static void main(String [] args){
        BTREE<Integer, Character> b = new BTREE<>();
        char ch = 'a';
        for(int i = 0; i<100;i++) b.put(i,ch++);
        b.put(23,ch++);
		b.put(462,ch++);
		b.put(68,ch++);
		b.put(86,ch++);
		b.put(3,ch++);
		b.put(463,ch++);
		b.put(5567,ch++);
		b.put(64,ch++);
		b.put(22,ch++);
		b.put(1,ch++);
		b.put(24,ch++);
        b.put(17,ch++);
        System.out.println(b.get(24));
        b.levelOrder(b.getRoot());   
    }
}*/