public class list {
    public int num;
    public DNode header;
    public DNode trailer;

    public list(){
        num=0;
        header=new DNode(null,null,null);
        trailer=new DNode(header,null,null);
        header.setnext(trailer);
    }
    public int size(){
        return num;
    }
    public boolean isempty(){
        if (header.getnext().equals(trailer)) return true; else return false;
    }
    public DNode first(){
        if (header.getnext()==trailer)
            return header;
        else
            return header.getnext();
    }
    public DNode prev(DNode v){
        DNode prev=v.getprev();
        return prev;
    }
    public DNode insertBefore(DNode p,order v){
        num++;
        DNode newNode=new DNode(p.getprev(),p,v);
        p.getprev().setnext(newNode);
        p.setprev(newNode);
        return newNode;
    }
    public DNode insertFirst(order v){
        num++;
        DNode newNode=new DNode(header,header.getnext(),v);
        header.getnext().setprev(newNode);
        header.setnext(newNode);
        return newNode;
    }
    public void removenode(DNode v){
        num--;
        DNode vprev=v.getprev();
        DNode vnext=v.getnext();
        vprev.setnext(vnext);
        vnext.setprev(vprev);

    }

}

