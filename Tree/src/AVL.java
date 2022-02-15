import java.util.*;

class Vertex<Key extends Comparable<Key>,Value>{
    Key key;
    Value value;
    Vertex<Key,Value> left, right;
    int height;
    public Vertex(Key k, Value v){
        key = k;
        value = v;
        left = right = null;
        height = 1;
    }
    public Key getKey(){return key;}
    public Value getValue(){return value;}
    public Vertex<Key,Value> getLeft(){return left;}
    public Vertex<Key,Value> getRight(){return right;}
    public int getHeight(){return height;}
    public void setKey(Key k){key = k;}
    public void setValue(Value v){value = v;}
    public void setLeft(Vertex<Key,Value> lt){left = lt;}
    public void setRight(Vertex<Key,Value> rt){right = rt;}
    public void setHeight(int h){height = h;}
}
public class AVL<Key extends Comparable<Key>,Value>{
    Vertex<Key,Value> root;
    public AVL(){root = null;}
    public Vertex<Key,Value> getRoot(){return root;}
    //height()
    public int height(Vertex<Key,Value> n){
        if(n == null) return 0;
        return Math.max(height(n.getLeft()),height(n.getRight()))+1;
    }
    //tallerHeight() 
    public int tallerHeight(Vertex<Key,Value> l, Vertex<Key,Value> r){
        return Math.max(height(l), height(r));
    }
    //bf()
    public int bf(Vertex<Key,Value> n){
        return height(n.getLeft())-height(n.getRight());
    }
    //leftRotation()
    public Vertex<Key,Value> leftRotation(Vertex<Key,Value> n){
        Vertex<Key,Value> x = n.getRight();
        n.setRight(x.getLeft());
        x.setLeft(n);
        //노드 x와 n의 높이가 바뀔 수 있음으로 높이를 다시 계산.
        x.setHeight(tallerHeight(x.getLeft(), x.getRight())+1);
        n.setHeight(tallerHeight(n.getLeft(), n.getRight())+1);
        return x;
    }
    //rightRotation()
    public Vertex<Key,Value> rightRotation(Vertex<Key,Value> n){
        Vertex<Key,Value> x = n.getLeft();
        n.setLeft(x.getRight());
        x.setRight(n);
        x.setHeight(tallerHeight(x.getLeft(), x.getRight())+1);
        n.setHeight(tallerHeight(n.getLeft(), n.getRight())+1);
        return x;
    }
    //balance()
    public Vertex<Key,Value> balance(Vertex<Key,Value> n){
        if(bf(n) > 1){ //LL or LR
            if(bf(n.getLeft()) < 0){ //LR에서 R에 해당하는 부분 leftRotation
                n.setLeft(leftRotation(n.getLeft()));
            }
            n = rightRotation(n);
        }
        else if(bf(n) < -1){ //RR or RL
            if(bf(n) > 0){ //RL에서 L에 해당하는 부분 rightRotation
                n.setRight(rightRotation(n.getRight()));
            }
            n = leftRotation(n);
        }
        return n;
    }
    public void put(Key k, Value v){
        root = put(root,k,v);
    }
    public Vertex<Key,Value> put(Vertex<Key,Value> n, Key k, Value v){
        if(n == null) return new Vertex<Key,Value>(k,v);
        int t = n.getKey().compareTo(k);
        if(t > 0){n.setLeft(put(n.getLeft(),k,v));}
        else if(t < 0){n.setRight(put(n.getRight(),k,v));}
        else{
            n.setValue(v);
            return n; //트리가 바뀌지 않으니 AVL트리를 유지하고 있다. 굳이 balance를 확인할 필요가 없다.
        }
        //balance() 전에 n의 높이를 저장. 만일 트리가 balance()함수에 의해 변화되면 rotation()함수에서 또
        //높이를 재설정해준다. 즉, bf(n) 이 -1,0,1이면 아래 지정한 높이가 유지된다.
        n.setHeight(tallerHeight(n.getLeft(), n.getRight())+1); 
        return balance(n); //밑에서부터 root를 향해 수정해 나간다.
    }
    public void bfs(Vertex<Key,Value> n){
        Queue<Vertex<Key,Value>> q = new LinkedList<Vertex<Key,Value>>();
        q.add(n);
        Vertex<Key,Value> t;
        while(!q.isEmpty()){
            t = q.remove();
            System.out.println("<"+t.getKey()+","+t.getValue()+","+t.getHeight()+">");
            if(t.getLeft()!=null)q.add(t.getLeft());
            if(t.getRight()!=null)q.add(t.getRight());
        }
    }
    //min()
    public Key min(){
        if(root == null) return null;
        return min(root).getKey();
    }
    public Vertex<Key,Value> min(Vertex<Key,Value> n){
        if(n.getLeft() == null) return n;
        return min(n.getLeft());
    }
    //deleteMin()
    public void deleteMin(){
        if(root == null){System.out.println("root is null");}
        else root = deleteMin(root);
    }
    public Vertex<Key,Value> deleteMin(Vertex<Key,Value> n){
        if(n.getLeft() == null)return n.getRight();
        n.setLeft(deleteMin(n.getLeft()));
        return n;
    }
    //delete()
    public void delete(Key k){
        root = delete(root, k);
    }
    public Vertex<Key,Value> delete(Vertex<Key,Value> n, Key k){
        if(n == null) return null; //지우고자 하는 노드가 트리에 없음을 의미.
        int t = n.getKey().compareTo(k);
        if(t > 0){n.setLeft(delete(n.getLeft(),k));}
        else if(t < 0){n.setRight(delete(n.getRight(),k));}
        else{
            //n의 오른쪽 또는 왼쪽 서브트리는 이미 avl트리 조건을 만족하고 있을테니 굳이 
            //bf를 확인할 필요가 없을 것이고, n에서의 balance를 확인해서 수정해주면 된다.
            if(n.getLeft()==null) return n.getRight(); //case 0, 1
            if(n.getRight()==null) return n.getLeft(); //case 1
            Vertex<Key,Value> target = n;
            n = min(target.getRight()); //중위 후속자
            n.setRight(deleteMin(target.getRight()));
            n.setLeft(target.getLeft());
        }
        n.setHeight(tallerHeight(n.getLeft(), n.getRight())+1);
        return balance(n); //아래에서 root를 향해서...
    }

    public static void main(String []args){
        AVL<Integer,Character> avl = new AVL<Integer, Character>();
        char c = 'a';
        for(int i = 0; i<10;i++){
            avl.put(i,c++);
        }
        avl.delete(1);
        avl.delete(7);
       avl.bfs(avl.getRoot());
    }
}