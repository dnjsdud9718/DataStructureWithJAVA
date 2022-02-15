public class Travel {
    private Node start;
    public Travel(){start=null;}
    public class Node{
        private int number;
        private Node left, right;
        public Node(int number, Node lt, Node rt){
            this.number = number; this.left=lt; this.right=rt;
        }
    }
    public Node map(){
        Node [] n = new Node[12]; //0 ~ 11
        for(int i=1;i<n.length;i++) {n[i] = new Node(i, null, null);}
        n[1].left= n[2]; n[1].right=n[3];
        n[2].left= n[4]; n[2].right=n[5];
        n[3].left= n[6]; n[3].right=n[7];
        n[4].left= n[8]; n[5].left=n[9];
        n[7].right= n[10]; n[9].right=n[11];
        return n[1];
    }
    public void aCourse(Node n){
        //VLR
        if(n!=null){
            System.out.print(n.number+" ->");
            aCourse(n.left);
            aCourse(n.right);
        }
    }
    public void bCourse(Node n){
        //LVR;
        if(n!=null){
            bCourse(n.left);
            System.out.print(n.number+" ->");
            bCourse(n.right);
        }
    }
    public void cCourse(Node n){
        //LRV
        if(n!=null){
            cCourse(n.left);
            cCourse(n.right);
            System.out.print(n.number+" ->");
        }
    }
    public static void main(String [] args){
        Travel t = new Travel();
        t.start= t.map();
        System.out.println("A-Course");
        t.aCourse(t.start);
        System.out.println();
        System.out.println("B-Course");
        t.bCourse(t.start);
        System.out.println();
        System.out.println("C-Course");
        t.cCourse(t.start);
    }
}
