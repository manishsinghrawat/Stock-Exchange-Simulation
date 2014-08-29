import java.io.*;

public class Ordergen implements Runnable{
    Queqe queqe;
    long starttime;
    stock stk;
    File file;
    BufferedWriter bufferedWriter;

    Ordergen(stock stk,long starttime,Queqe queqe){
        this.stk=stk;
        this.starttime=starttime;
        this.queqe=queqe;
        file=new File("c:\\order.out");

        try{
            if (!file.exists())
                file.createNewFile();

            bufferedWriter = new BufferedWriter(new FileWriter(file));
        }catch(Exception ex){
            System.out.println(ex);
        }

    }

    public void write(String str){
            try {
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
    }

    public boolean validate(long T0){
        if ((T0*1000)<(System.currentTimeMillis()-starttime))
                return true;
        else
                return false;
    }
    public void addtoqueqe(order ord){
        queqe.enqueqe(ord);
    }
    public void run() {
        order cur;
        while(stk.nextorder()==true){
            try{
                cur=stk.readorder();
                while(!validate(cur.T0())){}
                
                
                if (!validate(cur.T0()+cur.Texp())){
                    addtoqueqe(cur);
                    write(Long.toString((System.currentTimeMillis()-starttime)/1000)+"\t"+cur.getstring());
                }
                else {
                    write("Exception : "+""+Long.toString((System.currentTimeMillis()-starttime)/1000)+"\t"+cur.getstring());
                }

            }catch (Exception ex){
               write("EXCEPTION : "+ex.toString()+" "+stk.CurrentLine);
            }

        }

        try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            } catch (Exception exs){}
    }
}
