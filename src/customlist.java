public class customlist extends list{

    private DNode ctrex;
    private DNode ctrcl;
    private DNode Lastcl;
    
    public customlist(){
        ctrcl=trailer;
        ctrex=header;
        Lastcl=header;
    }

    public void additem(order nn){
        
        insertBefore(trailer,nn);
    }

    public synchronized order getnextcl(){


        if (ctrcl==trailer){
            ctrcl=first();}
        
        Lastcl=ctrcl;
        ctrcl=ctrcl.getnext();
        return Lastcl.getelement();
    }

    public void resetex(){
        ctrex=first();
    }

    public order getnextex(){
        DNode temp;
        if (ctrex==trailer){
            return null;
        }else{
            temp=ctrex;
            ctrex=ctrex.getnext();
            return temp.getelement();
        }
    }

    public void remove(){
        
        if (ctrex.equals(Lastcl)) ctrex=ctrex.getnext();
        removenode(Lastcl);
        }
}
