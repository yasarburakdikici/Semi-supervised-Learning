package com.preprocessing;



public class Filter{

    public  static String filter(String line,String TAG)  {
        String line00=" ";

        if(line.startsWith(TAG)){
            line00=line.replace(TAG,""); //Removed Tags..
        }
        String line01=line00.replaceAll("[^A-Za-z0-9]"," ");
        String line0=line01.toLowerCase();
        String line1=line0.replaceAll("[0,1,2,3,4,5,6,7,8,9]" ,""); //Removed Numbers..
        String line2=line1.trim().replaceAll(" +"," "); // Removed Multiple Spaces..
          return line2;
    }


}
