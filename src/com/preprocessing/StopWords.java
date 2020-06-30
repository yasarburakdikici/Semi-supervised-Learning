package com.preprocessing;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StopWords {

    private static HashSet stopWords=new HashSet();

    public StopWords(String fileName) throws IOException {


        BufferedReader reader =  new BufferedReader(new FileReader(fileName));

        try{

            while (reader.ready()) {

                stopWords.add(reader.readLine());
            }

        }catch (FileNotFoundException ex){
            System.out.println("this file is not exist..");
        }catch (IOException ex){
            System.out.println("input output error..");
        }finally {
            if(reader!=null){
                reader.close();
            }
        }
    }

    public void add(String word){
        stopWords.add(word);
    }

    public  boolean  isStopWords(String words){

       return stopWords.contains(words);


    }


}
