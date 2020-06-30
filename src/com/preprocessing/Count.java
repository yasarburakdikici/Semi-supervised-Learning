package com.preprocessing;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Count {


    public static HashMap<String, Integer> coincidences;





    public static HashMap<String, Integer> processList(List<String> list) throws IOException {


             coincidences=new HashMap<String,Integer>();

            for (String string : list) {

                String[] sp = string.split(" ");
                for (String string2 : sp) {


                    if (coincidences.get(string2) == null) {
                        coincidences.put(string2, 1);
                    } else {

                        coincidences.replace(string2, coincidences.get(string2) + 1);

                    }

                }


            }


          return coincidences;



    }








}

