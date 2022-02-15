import java.util.*;
class BNode<Key extends Comparable<Key>,Value>{
    Key key; //값
    Value value; //노드 식별자
    BNode<Key,Value> left, right;
    public BNode(Key newKey, Value newVal){
        key = newKey;
        value = newVal;
        left = right = null; //BST에서 추가되는 노드는 항상 리프노드로 
        //삽입되기 때문에 링크를 모두 null로 설정
    }
    public Key getKey(){return key;}
    public Value getValue(){return value;}
    public BNode<Key,Value> getLeft(){return left;}
    public BNode<Key,Value> getRight(){return right;}
    public void setKey(Key newKey){key = newKey;}
    public void setValue(Value newVal){value = newVal;}
    public void setLeft(BNode<Key,Value> lt){left = lt;}
    public void setRight(BNode<Key,Value>rt){right = rt;}
}
public class BST<Key extends Comparable<Key>,Value>{
    BNode<Key,Value> root;
    public BST(){root = null;} //Tree는  empty일 수 있다. 따라서 초기 tree는 empty tree로 만든다.
    public BNode<Key,Value> getRoot(){return root;}
    //BST methods: put() min() deleteMIn() delete() inorder() levelorder()
    public void put(Key k, Value v){
        root = put(root, k, v);
    }
    public BNode<Key,Value> put(BNode<Key,Value> n, Key k, Value v){
        if(n == null) return new BNode<Key,Value>(k,v); //새로운 노드를 추가
        int t = n.getKey().compareTo(k);
        if(t>0) n.setLeft(put(n.getLeft(),k,v)); //n.getLeft() null이면 새로운 노드가 연결되고 아니면 유지
        else if(t<0)n.setRight(put(n.getRight(),k,v)); //n.getRight() null이면 새로운 노드가 연결되고 아니면 유지
        else n.setValue(v); //이미 동일한 key값이 있으면 value만 업데이트
        return n; // n이 null이 아니면 자기 자신을 리턴해서 원 상태 유지
    }
    public Key min(){
        if(root == null) return null;
        return min(root).getKey();
    }
    public BNode<Key,Value> min(BNode<Key,Value> n){ //n이 null이 아닌 것만 온다고 확신!
        if(n.getLeft() == null) return n;
        return min(n.getLeft());
    }
    public void deleteMin(){ 
        if(root == null) System.out.println("root is null: Not allowed deleteMin()");
        root = deleteMin(root);
    }
    public BNode<Key,Value> deleteMin(BNode<Key,Value> n){ //delete() 중위 후속자 찾아 target 위치에 올릴 때 사용
        if(n.getLeft() == null) return n.getRight(); //n이 min값이기 때문에
        n.setLeft(deleteMin(n.getLeft()));
        return n;
    }
    public void delete(Key k){
        root = delete(root, k);
    }
    public BNode<Key,Value> delete(BNode<Key,Value> n, Key k){
        if(n == null) return null; //k값을 가진 노드가 트리에 없음을 의미
        int t = n.getKey().compareTo(k);
        if(t>0) n.setLeft(delete(n.getLeft(),k));
        else if(t<0) n.setRight(delete(n.getRight(),k));
        else{ //t == 0
            if(n.getLeft() == null) return n.getRight(); //case 0,1
            if(n.getRight() == null) return n.getLeft(); //case 1
            BNode<Key,Value> target = n;
            n = min(n.getRight()); //n = 중위후속자: target의 오른쪽 서브 트리의 min node
            n.setRight(deleteMin(target.getRight()));
            n.setLeft(target.getLeft());
        }
        return n;
    }
    public void inorder(BNode<Key,Value> n){
        if(n != null){
            inorder(n.getLeft());
            System.out.print("<"+n.getKey()+","+n.getValue()+">");
            inorder(n.getRight());
        }
    }
    public void levelorder(BNode<Key,Value> n){
        Queue<BNode<Key,Value>> q = new LinkedList<BNode<Key,Value>>();
        BNode<Key,Value> t;
        q.add(n);
        System.out.print("Levelorder: ");
        while(!q.isEmpty()){
            t = q.remove();
            System.out.print("<"+t.getKey()+","+t.getValue()+">");
            if(t.getLeft() != null) q.add(t.getLeft());
            if(t.getRight() != null) q.add(t.getRight());
        }

    }
    public static void main(String [] args){
        BST<Integer,Character> bst = new BST<>();
        bst.put(100, 'A'); bst.put(50,'B'); bst.put(150,'C'); bst.put(25,'D');
        bst.put(75,'E'); bst.put(85,'F'); bst.put(125,'G'); bst.put(110,'H'); 
        bst.put(135,'I'); bst.put(115,'J');
        bst.inorder(bst.getRoot());
        System.out.println("");
        bst.delete(100);
        System.out.println(bst.getRoot().getValue());
        bst.inorder(bst.getRoot());
        System.out.println("");
        bst.levelorder(bst.getRoot());
    }
}