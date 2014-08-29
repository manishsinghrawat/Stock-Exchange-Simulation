public class DNode {
    private order element;
    private DNode next;
    private DNode prev;
    public DNode(){
       element=null;
       next=null;
       prev=null;
    }
    public DNode(DNode n1,DNode n,order e){
        element=e;
        next=n;
        prev=n1;
    }
    public order getelement(){
        return element;
    }
    public DNode getnext() {
        return next;
    }
    public DNode getprev(){
        return prev;
    }
    public void setnext(DNode nnext){
        next=nnext;
    }
    public void setprev(DNode pre){
        prev=pre;
    }
    public void setelement(order nele){
        element=nele;
    }
}
