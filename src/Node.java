public class Node {

    private order element;
    private Node next;

    public Node(){
       element=null;
       next=null;
    }
    public Node(order e,Node n){
        element=e;
        next=n;
    }
    public order getelement(){
        return element;
    }
    public void setelement(order nele){
        element=nele;
    }
    public Node getnext() {
        return next;
    }
    public void setnext(Node nnext){
        next=nnext;
    }
}
