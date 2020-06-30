package com.preprocessing;


import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Txt2Arff {

    public static void txt2Arff(String SET,String TAG) throws IOException{
        String uniqueDir="/home/ybd/Desktop/Programs/"+TAG+"-unique.txt";
        String TAGdir="/home/ybd/Desktop/Programs/"+SET+"-"+TAG+".txt";
        String outputDir="//home/ybd/Desktop/Programs/"+SET+"-"+TAG+".arff";
        BufferedReader BRunique = null;
        BufferedReader BRtag=new BufferedReader(new FileReader(TAGdir));




        ArrayList<String> features=new ArrayList<String>();

        try{
            String line;
            BRunique = new BufferedReader(new FileReader(uniqueDir));
            while ((line = BRunique.readLine()) != null){
                String[] lines=line.split(" ");
                features.add(lines[0]);
            }
        }catch (FileNotFoundException e){
            System.out.println("unique file does not found...");
        } catch (IOException e){
            System.out.println("input mismatch...");
        }finally {
            BRunique.close();
        }

        StringBuilder attributes=new StringBuilder();
        StringBuilder datas=new StringBuilder();
        int count=0;

        String arffHeader = "@relation Programs\n\n";

        for(String feature:features){
            count++;
            attributes.append("@attribute"+" "+count+" "+"Real"+"\n");
        }

        attributes.append("@attribute class"+" "+"{r,n}"+"\n");
        attributes.append("\n");
        datas.append("@data"+"\n");

        try{
            String TAGLine;

            while((TAGLine = BRtag.readLine()) != null){
               datas.append(TAGLine+"\n");
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found...");
        }catch (IOException e){
            System.out.println("Input mismatch...");
        }finally {
            BRtag.close();
        }


        FileWriter fileWriter=new FileWriter(outputDir);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);


        String firstPart=attributes.toString();
        String secondPart=datas.toString();


         bufferedWriter.write(arffHeader+firstPart+secondPart);
         bufferedWriter.close();



    }




}