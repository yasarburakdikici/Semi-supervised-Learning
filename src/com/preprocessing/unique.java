package com.preprocessing;

import java.io.*;
import java.util.*;




public class unique {


    public static void writeunique(String SET,String TAG) throws IOException {


        String target_dir = "/home/ybd/Desktop/Programs/"+SET;
        String stopWords_dir="/home/ybd/Desktop/Programs/stopWords.txt";
        String SET_dir="/home/ybd/Desktop/Programs/"+TAG+"-unique"+".txt";
        FileWriter writer=new FileWriter(SET_dir,true);
        BufferedWriter bufferedWriter=new BufferedWriter(writer);
        File dir = new File(target_dir);
        File[] files = dir.listFiles();
        Filter ft = new Filter();
        Stemmer stemmer = new Stemmer();
        String stemmerWord;
        StopWords stopWords=new StopWords(stopWords_dir);
        ArrayList<String> unique=new ArrayList<String>();
        HashMap<String, Integer> mapOfRepeatedWord = new HashMap<String, Integer>();
        Count c=new Count();




        for (File f : files) {
            if (f.isFile()) {

                if (f.getName().charAt(0) == 'r') {


                    BufferedReader inputStream = null;

                    try {

                        String line;
                        inputStream = new BufferedReader(new FileReader(f));
                        line = inputStream.readLine();

                        while (line != null) {



                                if (line.startsWith(TAG)) {


                                    String newLine = ft.filter(line, TAG);

                                    if (newLine.length() > 0) {


                                        String[] words = newLine.split("\\s");

                                        for (String word : words) {

                                            stemmer.add(word.toCharArray(), word.length());
                                            stemmer.stem();
                                            stemmerWord = stemmer.toString();


                                            if (!stopWords.isStopWords(stemmerWord)) {


                                                unique.add(stemmerWord);

                                            }//Stopwords Control..


                                        }//For each for words

                                    }//Lenght Control..

                                }//İf control for a TAG...




                            line = inputStream.readLine();


                        }//finish while

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }

                    }


                }//f is file control...


            }//Related file control..


        }//for each for files...


        try{
            mapOfRepeatedWord=c.processList(unique);


            for(Map.Entry<String,Integer> entry: mapOfRepeatedWord.entrySet()){

                bufferedWriter.write(entry.getKey() + " " + entry.getValue() + " \n");

            }

        }finally {
            bufferedWriter.close();
        }





    }

}
