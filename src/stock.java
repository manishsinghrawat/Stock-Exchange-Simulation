import java.io.*;
class stock
{
    FileInputStream fstream;
    DataInputStream in;
    BufferedReader br;
    String CurrentLine;
     public stock(String inputfile){
         try {
            fstream = new FileInputStream(inputfile);
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
    public order readorder() throws Exception{
        return new order(CurrentLine);
    }

    public boolean nextorder()
    {
            String strLine;
            try{
                if ((strLine = br.readLine())!=null){
                    CurrentLine=strLine;
                    return true;
                } else
                    return false;
            }catch(Exception ex){}
            return false;
    }

}