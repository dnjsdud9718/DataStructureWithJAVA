import java.util.*;

public class LLRB<Key extends Comparable<Key>,Value>{
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        Key key;
        Value value;
        boolean color;
        Node left, right;
        public Node(Key k, Value v, boolean col){
            key = k;
            value = v;
            color = col;
            left = right = null;
        }
    }
    Node root;
    public LLRB(){root = null;}
    public Node getRoot(){return root;}
    //get()
    public Value get(Key k){
        return get(root,k);
    }
    private Value get(Node n, Key k){
        if(n == null) return null;
        int t = k.compareTo(n.key);
        if(t < 0) return get(n.left, k);
        else if(t > 0) return get(n.right, k);
        else return (Value)n.value;
    }
    //put()에 필요한 함수 -> rotateLeft(), rotateRight(), isRed(), flipColors()
    private boolean isRed(Node n){
        if(n == null) return false;
        return (n.color == RED);
    }
    private Node rotateLeft(Node n){
        Node x = n.right;
        n.right = x.left;
        x.left = n;
        x.color = n.color;
        n.color = RED;
        return x;
    }
    private Node rotateRight(Node n){
        Node x = n.left;
        n.left = x.right;
        x.right = n;
        x.color = n.color;
        n.color = RED;
        return x;
    }
    private void flipColors(Node n){
        n.color = !n.color;
        n.left.color = !n.left.color;
        n.right.color = !n.right.color;
    }
    //put()
    public void put(Key k, Value v){
        root = put(root,k,v);
        root.color = BLACK;
    }
    private Node put(Node n, Key k, Value v){
        if(n == null) return new Node(k,v,RED);
        int t = k.compareTo(n.key);
        if(t < 0) n.left = put(n.left, k, v);
        else if(t > 0) n.right = put(n.right, k, v);
        else n.value = v;
        if(!isRed(n.left) && isRed(n.right)) n = rotateLeft(n);
        if(isRed(n.left) && isRed(n.left.left)) n = rotateRight(n);
        if(isRed(n.left) && isRed(n.right)) flipColors(n);
        return n;
    }
    //delete()에 필요한 연산 -> min(), deleteMin(), fixUp(), moveRedLeft(), moveRedRight()
    //min()
    public Key min(){
        if(root == null) return null;
        return min(root).key;
    }
    private Node min(Node n){
        if(n.left == null) return n;
        return min(n.left);
    }
    //fixUp()
    private Node fixUp(Node n){
        if(isRed(n.right)) n = rotateLeft(n);
        if(isRed(n.left) && isRed(n.left.left)) n = rotateRight(n);
        if(isRed(n.left) && isRed(n.right)) flipColors(n);
        return n;
    }
    //moveRedLeft()
    private Node moveRedLeft(Node n){
        flipColors(n);
        if(isRed(n.right.left)){
            n.right = rotateRight(n.right);
            n = rotateLeft(n);
            flipColors(n);
        }
        return n;
    }
    //moveRedRight()
    private Node moveRedRight(Node n){
        flipColors(n);
        if(isRed(n.left.left)){
            n = rotateRight(n);
            flipColors(n);
        }
        return n;
    }
    //deleteMin()
    public void deleteMin(){
        if(root == null) return;
        root = deleteMin(root);
        root.color = BLACK;
    }
    private Node deleteMin(Node n){
        if(n.left == null) return null; //n.right는 무조건 null
        if(!isRed(n.left) && !isRed(n.left.left)) n = moveRedLeft(n);
        n.left = deleteMin(n.left);
        return fixUp(n);
    }
    //delete()
    public void delete(Key k){
        root = delete(root, k);
    }
    private Node delete(Node n, Key k){
        if(k.compareTo(n.key) < 0){
            if(!isRed(n.left) && !isRed(n.left.left)) n = moveRedLeft(n);
            n.left = delete(n.left, k);
        }else{
            if(isRed(n.left)) n = rotateRight(n);
            if(k.compareTo(n.key) == 0 && n.right == null) return null;
            if(!isRed(n.right) && !isRed(n.right.left)) n = moveRedRight(n);
            if(k.compareTo(n.key) == 0){
                n.key = min(n.right).key;
                n.value = get(n.right, n.key);
                n.right = deleteMin(n.right);
            }else{
                n.right = delete(n.right, k);
            }
        }
        return fixUp(n);
    }
    public void levelOrder(Node n){
        Queue<Node> q = new LinkedList<Node>();
        q.add(n);
        Node t;
        while(!q.isEmpty()){
            t = q.remove();
            System.out.print("<"+t.key+","+t.value+","+t.color+">");
            if(t.left != null) q.add(t.left);
            if(t.right != null) q.add(t.right);

        }
        System.out.println("");
    }
    public static void main(String [] args){
        LLRB<Integer, Character> rb = new LLRB<Integer, Character>();
        char ch = 'a';
        for(int i = 0; i<=18;i++){
            rb.put(i,ch++);
        }
        //rb.delete(3);
        rb.levelOrder(rb.getRoot());
    }
}