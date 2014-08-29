import java.io.*;

public class cleanthread implements Runnable {
    customlist buy;
    customlist sell;
    long starttime;
    Thread t1,t2;
    File file;
    BufferedWriter bufferedWriter;

    cleanthread(long starttime,customlist buy,customlist sell,Thread t1,Thread t2){
        this.starttime=starttime;
        this.buy=buy;
        this.sell=sell;
        this.t1=t1;
        this.t2=t2;
        file=new File("..\\cleanup.out");

        try{
            if (!file.exists())
                file.createNewFile();

            bufferedWriter = new BufferedWriter(new FileWriter(file));
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public boolean validate(long T0){
        if ((T0*1000)<(System.currentTimeMillis()-starttime))
                return true;
        else
                return false;
    }

    public void write(String str){
            try {
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
    }

    public void run() {
        order temp1=null;
        order temp2=null;
        while(temp1!=null || temp2!=null || t1.isAlive()||t2.isAlive()){

            if (temp1!=null){

                if( (temp1.Qty()==0) || validate(temp1.T0()+temp1.Texp())){
                    
                    buy.remove();
                    write(Long.toString((System.currentTimeMillis()-starttime)/1000)+"\t"+temp1.getstring());
                    temp1=null;
                }
            }
            
            if (temp2!=null){
           
                if( (temp2.Qty()==0)||validate(temp2.T0()+temp2.Texp())){

                    //System.out.println(temp2.getname());
                    // System.out.println(temp2.Qty());
                    sell.remove();
                    write(Long.toString((System.currentTimeMillis()-starttime)/1000)+"\t"+temp2.getstring());
                    temp2=null;
                }
            }
            temp1=buy.getnextcl();
            temp2=sell.getnextcl();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
        try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            } catch (Exception exs){}
    }
}
