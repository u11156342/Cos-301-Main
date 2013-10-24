package Connections;

import java.util.ArrayList;
import java.util.StringTokenizer;


//Converts datastructures into the formats are needed for url transfering

public class Converter {

    public ArrayList<String[]> ArrFromUrl(String link) {
        ArrayList<String[]> toreturn = new ArrayList();

        StringTokenizer token1;
        token1 = new StringTokenizer(link, "" + '\n');

        while (token1.hasMoreTokens()) {
            String unit = token1.nextToken();

            StringTokenizer token2 = new StringTokenizer(unit, "@");

            String[] temp = new String[token2.countTokens()];
            int pos = 0;

            while (token2.hasMoreTokens()) {
                String subunit = token2.nextToken();
                temp[pos] = subunit;
                pos++;
            }
            pos = 0;
            toreturn.add(temp);
        }

        return toreturn;
    }

    public ArrayList<String> FromUrl(String link) {
        StringTokenizer token = new StringTokenizer(link, "" + '\n');

        ArrayList<String> toreturn = new ArrayList();

        while (token.hasMoreTokens()) {
            toreturn.add(token.nextToken());
        }
        return toreturn;
    }

    public String ArrToUrl(ArrayList<String[]> lis) {
        String temp = "";

        for (int a = 0; a < lis.size(); a++) {
            String[] unit = lis.get(a);
            
            for (int b = 0; b < unit.length; b++) {
                if (b == 0) {
                    temp = temp + unit[b];
                } else {
                    temp = temp + "@" + unit[b];
                }
            }
            
            temp=temp+"\n";
        }
        
        return temp;
    }
    
    public String ToUrl(ArrayList<String> list)
    {
        String temp="";
        
        for(int a=0;a<list.size();a++)
        {
            if(a==0)
            {
             temp=temp+list.get(a);   
            }
            else
            temp=temp+"\n"+list.get(a);
        }
        
        return temp;
    }

}
