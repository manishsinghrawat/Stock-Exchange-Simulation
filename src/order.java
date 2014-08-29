import java.util.StringTokenizer;

public class order {
    private int T0;
    private String name;
    private int Texp;
    private String Type;
    private int Qty;
    private String Stock;
    private int Price;
    private boolean Partial;
    private String str;
    public order(String str) throws Exception{
        this.str=str;
        StringTokenizer st = new StringTokenizer(str,"\t");
        int t=0;

        while (st.hasMoreTokens()) {
             t++;
            switch(t){
                case 1:
                    T0=Integer.parseInt(st.nextToken());
                    break;
                case 2:
                    name=st.nextToken();
                    break;
                case 3:
                    Texp=Integer.parseInt(st.nextToken());
                    break;
                case 4:
                    Type=st.nextToken();
                    if (!(Type.toLowerCase().equals("buy")||(Type.toLowerCase().equals("sell"))))
                        throw new Exception("STock Type should be either buy or sell");
                    break;
                case 5:
                    Qty=Integer.parseInt(st.nextToken());
                    break;
                case 6:
                    Stock=st.nextToken();
                    break;
                case 7:
                    Price=Integer.parseInt(st.nextToken());
                    break;
                case 8:
                    String temp=st.nextToken();
                   if(temp.startsWith("1"))
                       Partial=true;
                   else if(temp.startsWith("0"))
                       Partial=false;
                   else
                       throw new Exception("Partial should be either 0 or 1");
                   break;
                case 9:
                    throw new Exception("Excessive Data");
            }
        }
        if (t<8)
            throw new Exception("Insufficient data");
    }

    public String getname(){
        return name;
    }
    public String gettype(){
        return Type;
    }
    public String getstock(){
        return Stock;
    }
    public int T0(){
        return T0;
    }
    public int Texp(){
        return Texp;
    }
    public int Qty(){
        return Qty;
    }
    public int Price(){
        return Price;
    }
    public boolean ispartial(){
        return Partial;
    }
    public String getstring(){
        return str;
    }

    void setqty(int i) {
        Qty=i;
    }
}