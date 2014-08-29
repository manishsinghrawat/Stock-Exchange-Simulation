public class Queqe {
    int size;
    Node head;
    Node tail;
    public Queqe(){
        size=0;
        head=null;
        tail=null;
    }
    public synchronized void enqueqe(order obj){
        Node node=new Node();
        node.setelement(obj);
        node.setnext(null);
        if (size==0)
           head=node;
        else
           tail.setnext(node);
        tail=node;
        size++;
    }
    public synchronized order dequeqe(){
        if (size==0)
            return null;
        order obj=head.getelement();
        head=head.getnext();
        size--;
        if(size==0)
            tail=null;
        return obj;
    }
    public int getsize(){
        return size;
    }
}
