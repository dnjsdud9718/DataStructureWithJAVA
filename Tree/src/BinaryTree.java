import java.util.*;
import java.util.Stack;
public class BinaryTree<Key extends Comparable<Key>>{ //generic: type bounded
    private TNode<Key> root;
    public BinaryTree(){root = null;}
    public int getSize(TNode<Key> n){ //모든 노드를 방문 O(N)
        if(n == null)return 0;
        else return (1+getSize(n.getLeft())+getSize(n.getRight()));
    }
    public int getHeight(TNode<Key> n){ //모든 노드를 방문 O(N)
        if(n == null) return 0;
        else return (1+Math.max(getHeight(n.getLeft()), getHeight(n.getRight())));
    }
    public TNode<Key> getRoot(){return root;}
    public void setRoot(TNode<Key> newRoot){root=newRoot;}
    public boolean isEmpty(){return root == null;}
    //메서드의 재귀호출은 시스템 스택을 사용하므로 스택 자료구조를 사용한 것으로 간주한다.
    public void preorder(TNode<Key> n){ //VLR
        if(n != null){
            System.out.print(n.getItem()+" ");
            preorder(n.getLeft());
            preorder(n.getRight());
        }   
    }
    public void inorder(TNode<Key> n){ //LVR
        if(n != null){
            inorder(n.getLeft());
            System.out.print(n.getItem()+" ");
            inorder(n.getRight());
        }
    }
    public void iter_inorder(TNode<Key> n){ //스택활용
        Stack<TNode<Key>> s = new Stack<TNode<Key>>();
        while(true){
            while(n!=null){
                s.push(n);
                n = n.getLeft();
            }
            if(s.size() == 0) break;
            n=s.pop();
            if(n == null) break;
            System.out.print(n.getItem()+" ");
            n=n.getRight();
        }
        System.out.println(" ");
    }
    public void postorder(TNode<Key> n){ //LRV
        if(n != null){
            postorder(n.getLeft());
            postorder(n.getRight());
            System.out.print(n.getItem()+" ");
        }
    }
    public void levelorder(TNode<Key> root){ //BFS
        Queue<TNode<Key>> q = new LinkedList<TNode<Key>>();
        TNode<Key> t;
        q.add(root);
        while(!q.isEmpty()){
            t = q.remove();
            System.out.print(t.getItem()+" ");
            if(t.getLeft() != null) q.add(t.getLeft());
            if(t.getRight() != null) q.add(t.getRight());
        }
    }
    public static <Key extends Comparable<Key>> boolean isEqual(TNode<Key> n, TNode<Key> m){
        if(n==null || m ==null) return n == m;
        if(n.getItem().compareTo(m.getItem())!=0) return false;
        return(isEqual(n.getLeft(), m.getLeft())&&isEqual(n.getRight(), m.getRight()));
    }
    public static <Key extends Comparable<Key>> TNode<Key> copy(TNode<Key> original){
        TNode<Key> tmp;
        if(original != null){
            tmp = new TNode<Key>(null,null,null);
            tmp.setLeft(copy(original.getLeft()));
            tmp.setRight(copy(original.getRight()));
            tmp.setItem(original.getItem());
            return tmp;
        }
        return null;
    }
    public static void main(String [] args){
        TNode<Integer> n1 = new TNode<Integer>(100,null,null);
        TNode<Integer> n2 = new TNode<Integer>(200,null,null);
        TNode<Integer> n3 = new TNode<Integer>(300,null,null);
        TNode<Integer> n4 = new TNode<Integer>(400,null,null);
        TNode<Integer> n5 = new TNode<Integer>(500,null,null);
        TNode<Integer> n6 = new TNode<Integer>(600,null,null);
        TNode<Integer> n7 = new TNode<Integer>(700,null,null);
        TNode<Integer> n8 = new TNode<Integer>(800,null,null);
        n1.setLeft(n2); n1.setRight(n3);
        n2.setLeft(n4); n2.setRight(n5);
        n3.setLeft(n6); n3.setRight(n7);
        n4.setLeft(n8);
        BinaryTree<Integer> bt = new BinaryTree<>();
        bt.setRoot(n1);
        System.out.print("중위순회: ");
        bt.inorder(bt.getRoot());
        System.out.print("\n중위순회2: ");
        bt.iter_inorder(bt.getRoot());
        System.out.print("\n전위순회");
        bt.preorder(bt.getRoot());
        System.out.print("\n후위순회");
        bt.postorder(bt.getRoot());
        System.out.print("\n레벨순회");
        bt.levelorder(bt.getRoot());
        BinaryTree<Integer> bt2 = new BinaryTree<>();
        //boolean t = BinaryTree.isEqual(bt.getRoot(), bt2.getRoot());
        TNode<Integer> m1 = new TNode<Integer>(100, null, null);
        TNode<Integer> m2 = new TNode<Integer>(200, null, null);
        TNode<Integer> m3 = new TNode<Integer>(300, null, null);
        TNode<Integer> m4 = new TNode<Integer>(400, null, null);
        m1.setLeft(m2); m2.setRight(m3);
        m2.setLeft(m4);
        bt2.setRoot(m1);
        System.out.println("\n"+bt.getRoot().getItem()+" "+bt2.getRoot().getItem());
        //boolean t = BinaryTree.isEqual(bt.getRoot(), bt2.getRoot());
        //System.out.println(t);
        TNode<Integer> c = BinaryTree.copy(bt.getRoot());
        BinaryTree<Integer> bt3 = new BinaryTree<>();
        bt3.setRoot(c);
        bt3.inorder(bt3.getRoot());
        System.out.println("\n"+BinaryTree.isEqual(bt.getRoot(), bt3.getRoot()));
        System.out.println(bt.getRoot() == c);
        System.out.println(bt.getRoot().getItem().equals(c.getItem()));
    }

}
