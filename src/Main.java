public class Main {
            static boolean running;

    public static void main(String[] args){
      stock stk=new stock(args[0]);

      order n1;
      Queqe queqe=new Queqe();
      customlist buy=new customlist();
      customlist sell=new customlist();
      
      
      long starttime=System.currentTimeMillis();
      
      Ordergen ordg=new Ordergen(stk,starttime,queqe);
      Thread t1=new Thread(ordg);
      t1.start();

      Exthread ex=new Exthread(starttime,queqe,buy,sell,t1);
      Thread t2=new Thread(ex);
      t2.start();

      cleanthread cln=new cleanthread(starttime,buy,sell,t1,t2);
      Thread t3=new Thread(cln);
      t3.start();

    }
}
