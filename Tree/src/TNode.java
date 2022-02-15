//BinaryTree에 쓰이는 노드
public class TNode<Key extends Comparable<Key>>{
    private Key item;
    private TNode<Key> left, right;
    public TNode(Key newItem, TNode<Key> lt, TNode<Key> rt){
        item = newItem; left = lt; right = rt;
    }
    public Key getItem(){return item;}
    public TNode<Key> getLeft(){return left;}
    public TNode<Key> getRight(){return right;}
    public void setItem(Key newItem){item = newItem;}
    public void setLeft(TNode<Key> lt){left = lt;}
    public void setRight(TNode<Key> rt){right = rt;}
}