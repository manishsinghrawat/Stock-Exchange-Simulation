import java.io.*;
public class Exthread implements Runnable{
    Queqe queqe;
    customlist buy;
    customlist sell;
    long starttime;
    order currentorder;
    Thread ordgen;
    File file;
    BufferedWriter bufferedWriter;
     int profit;

    public Exthread(long starttime,Queqe queqe,customlist buy,customlist sell,Thread ord){
        this.starttime=starttime;
        this.queqe=queqe;
        this.buy=buy;
        this.sell=sell;
        profit=0;
        currentorder=null;
        ordgen=ord;
        file=new File("..\\exchange.out");

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
    private int min(order a,order b){
        if (b==null) return a.Qty();
        if(a==null) return b.Qty();
        if(a.Qty()<b.Qty())
            return a.Qty();
        else
            return b.Qty();
    }
    public boolean validate(long T0){
        if ((T0*1000)<(System.currentTimeMillis()-starttime))
                return true;
        else
                return false;
    }
    public void run() {
        order cord=null;
        order bestone=null;
        int unitbest=0,unitcord=0;
        currentorder=null;
        profit=0;
        while((ordgen.isAlive()) || (currentorder!=null)){
            while(ordgen.isAlive() && currentorder==null){
                currentorder = queqe.dequeqe();
            }
            
            if (currentorder!=null){
                cord=null;
                bestone=null;
                if(currentorder.gettype().toLowerCase().equals("buy")){
                
                    do{
                        bestone=null;
                        sell.resetex();
                        while((cord=sell.getnextex())!=null){
                            if (currentorder.getstock().equals(cord.getstock())){
                                if (currentorder.Price()>cord.Price()){
                                    
                                    unitbest=min(currentorder,bestone);
                                    unitcord=min(currentorder,cord);
                                    
                                    if(!validate(cord.T0()+cord.Texp()))
                                    if(currentorder.ispartial() || unitcord==currentorder.Qty()){
                                        if (cord.ispartial() || unitcord==cord.Qty()){
                                            if(bestone==null)
                                                bestone=cord;
                                            else if ((currentorder.Price()-bestone.Price())*unitbest<(currentorder.Price()-cord.Price())*unitcord)
                                                bestone=cord;
                                        }
                                    }

                                }
                            }

                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {}

                        }

                        if (bestone!=null && unitbest!=0){
                          //  System.out.println((currentorder.Price()-bestone.Price())*unitbest);
                                 profit+=unitbest*(currentorder.Price()-bestone.Price());
                                currentorder.setqty(currentorder.Qty()-unitbest);
                                bestone.setqty(bestone.Qty()-unitbest);

                                write("T\t"+Long.toString((System.currentTimeMillis()-starttime)/1000)+"\t"+Integer.toString(unitbest)+"\t"+currentorder.getstring());
                                write("T\t"+Long.toString((System.currentTimeMillis()-starttime)/1000)+"\t"+Integer.toString(unitbest)+"\t"+bestone.getstring());
                        }
                        

                    }while(bestone!=null && unitbest!=0);

                    if (currentorder.Qty()!=0){
                        //System.out.println("y");
                        buy.additem(currentorder);
                        write("P\t"+Long.toString((System.currentTimeMillis()-starttime)/1000)+"\t"+Integer.toString(0)+"\t"+currentorder.getstring());
                    }

                }else if (currentorder.gettype().toLowerCase().equals("sell")){
                    do{
                        bestone=null;
                        buy.resetex();
                        while((cord=buy.getnextex())!=null){
                            if (currentorder.getstock().equals(cord.getstock())){
                                if (currentorder.Price()<cord.Price()){
                                    
                                    unitbest=min(currentorder,bestone);
                                    unitcord=min(currentorder,cord);
                                    if(!validate(cord.T0()+cord.Texp()))
                                    if(currentorder.ispartial() || unitcord==currentorder.Qty()){
                                        if (cord.ispartial() || unitcord==cord.Qty()){
                                            if(bestone==null)
                                                bestone=cord;
                                            else if((bestone.Price() - currentorder.Price()) * unitbest < (cord.Price() - currentorder.Price()) * unitcord)
                                                bestone=cord;
                                        }
                                    }

                                }
                            }

                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {}
                        }

                        if (bestone!=null && unitbest!=0){
                            profit+=unitbest*(bestone.Price()-currentorder.Price());
                            currentorder.setqty(currentorder.Qty()-unitbest);
                            bestone.setqty(bestone.Qty()-unitbest);
                            write("T\t"+Long.toString((System.currentTimeMillis()-starttime)/1000)+"\t"+Integer.toString(unitbest)+"\t"+currentorder.getstring());
                            write("T\t"+Long.toString((System.currentTimeMillis()-starttime)/1000)+"\t"+Integer.toString(unitbest)+"\t"+bestone.getstring());
                        }

                    }while(bestone!=null && unitbest!=0);

                    if (currentorder.Qty()!=0){
                        sell.additem(currentorder);
                        write("S\t"+Long.toString((System.currentTimeMillis()-starttime)/1000)+"\t"+Integer.toString(0)+"\t"+currentorder.getstring());
                    }

                }else{
                    write("Exception\t"+Long.toString((System.currentTimeMillis()-starttime)/1000)+"\t"+Integer.toString(0)+"\t"+currentorder.getstring());
                }
                currentorder = queqe.dequeqe();

            }
        }



        write("Profit : " +Integer.toString(profit));
        try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
            }
        } catch (Exception exs){}

    }
}
