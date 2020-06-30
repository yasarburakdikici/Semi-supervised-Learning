package com.preprocessing;


import java.io.*;
import java.util.*;



class DocumentProperties{

    private HashMap<String,Integer> DocWordCounts;

       public HashMap<String,Integer> getWordCountMap() {

              return DocWordCounts;
        }

        public void setWordCountMap(HashMap<String,Integer> inMap) {
              DocWordCounts =new HashMap<String, Integer>(inMap);
        }

}

public class TFCalculator {


    ArrayList<String> wordList=new ArrayList<String>();

    //Writes the contents of hashmap to txt file
    public  void outputAsTxt(HashMap<String,Integer> treeMap,String fileName,String OutputPath) throws IOException {

          StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Integer> keymap : treeMap.entrySet()) {
            builder.append(keymap.getValue()+",");
        }
        builder.append(fileName.charAt(0));

        String content = builder.toString().trim();
        BufferedWriter writer = new BufferedWriter(new FileWriter(OutputPath,true));


        writer.write(content+"\n");
        writer.close();

    }

    //cleaning up the input by stemmer algorithm.,:"
    public  String cleanseInput(String input) throws IOException
    {

        Stemmer stemmer=new Stemmer();


        stemmer.add(input.toCharArray(), input.length());
        stemmer.stem();
        String stemmerWord = stemmer.toString();

        return stemmerWord;

    }




    // Converts the input text file to hashmap and even dumps the final output as txt files
    public  HashMap<String, Integer> getTermsFromFile(String Filename,int count,File folder) {
        HashMap<String,Integer> WordCount = new HashMap<String,Integer>();
        BufferedReader reader = null;
        HashMap<String, Integer> finalMap = new HashMap<>();
        Filter ft=new Filter();
        String[] TAGS={"<URL>","<TITLE>","<META>","<H1>","<ANCH>","<EM>","<STRONG>","<LI>","<B>","<I>","<P>","<TEXT"};
        try
        {
            reader = new BufferedReader(new FileReader(Filename));
            String line = reader.readLine();
            String stopWords_dir="/home/ybd/Desktop/Programs/stopWords.txt";
            StopWords stopWords=new StopWords(stopWords_dir);


            while(line!=null) {

                    for(String TAG:TAGS) {


                        if (line.startsWith(TAG)) {

                            String newLine = ft.filter(line, TAG);

                            if (newLine.length() > 0) {

                                String[] words = newLine.split("\\s");


                                for (String term : words) {

                                    term = cleanseInput(term);

                                    if (!stopWords.isStopWords(term)) {


                                        if (WordCount.containsKey(term)) {
                                            WordCount.put(term, WordCount.get(term) + 1);
                                        } else {
                                            WordCount.put(term, 1);
                                        }


                                    }//Stopwords Control..


                                }//ForEach for words..

                            }

                        }//If control for a TAG..
                    }//Foreach for TAGS..



                line = reader.readLine();

                // sorting the hashmap
                Map<String, Integer> treeMap = new TreeMap<>(WordCount);
                finalMap = new HashMap<String, Integer>(treeMap);

            }//While loop
        }//finish try..
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return finalMap;
    }

    public HashMap<String,Integer> eachDocumentVector(HashMap<String,Integer> tree){
        HashMap<String,Integer> InverseDocFreqMap = new HashMap<>();
        int wordCount ;



        for(String word:wordList){
            wordCount=0;

            if(tree.containsKey(word)){
                wordCount=tree.get(word);
            }else{
                wordCount=0;
            }
            InverseDocFreqMap.put(word,wordCount);
        }



        return InverseDocFreqMap;
    }



    public static File[] sortByNumber(File[] listofFiles) {
        Arrays.sort(listofFiles, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int n1 = extractNumber(o1.getName());
                int n2 = extractNumber(o2.getName());
                return n1 - n2;
            }

            private int extractNumber(String name) {

                int i = 0;



                try {

                            int s=name.indexOf('n')+1;

                    int e = name.lastIndexOf('.');
                        String number = name.substring(s, e);
                        i = Integer.parseInt(number);

                } catch(Exception e) {
                    i = 0; // if filename does not match the format
                    // then default to 0
                }
                return i;
            }
        });

        return listofFiles;
    }

    public static File[] sortByName(File[] listOfFiles){
        Arrays.sort(listOfFiles, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int n1 = extractName(o1.getName());
                int n2 = extractName(o2.getName());
                return n1 - n2;
            }
            private int extractName(String name) {

                int i = 0;

                try {

                    int s=name.indexOf('r')+1;

                    int e = name.lastIndexOf('.');
                    String number = name.substring(s, e);
                    i = Integer.parseInt(number);

                } catch(Exception e) {
                    i = 0; // if filename does not match the format
                    // then default to 0
                }
                return i;
            }

        });

        return listOfFiles;
    }


    public  void readunique(String TAG){
         String dir="/home/ybd/Desktop/Programs/"+TAG+"-unique.txt";
         BufferedReader reader=null;

         try{
             String line;
             reader = new BufferedReader(new FileReader(dir));
             while ((line = reader.readLine()) != null){
                 String[] lines=line.split(" ");
                 wordList.add(lines[0]);
             }
         }catch (FileNotFoundException e){
             System.out.println("unique file does not found...");
         } catch (IOException e){
             System.out.println("input mismatch...");
         }
    }



    public static void createVector(String SET,String TAG) throws IOException {


        Scanner scan = new Scanner("/home/ybd/Desktop/Programs/"+SET);
        TFCalculator Tf = new TFCalculator();
        File folder = new File(scan.nextLine());
        File[] listOfFiles = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return !file.isHidden();
            }
        });




          File[] files= Tf.sortByNumber(listOfFiles);
          File[] lastFiles=Tf.sortByName(files);
          int noOfDocs = lastFiles.length;

          Tf.readunique(TAG);//unique class.txt read...




        DocumentProperties [] docProperties = new DocumentProperties[noOfDocs];
        int count=0;

        for (File file:lastFiles) {
            if (file.isFile()) {

                docProperties[count] = new DocumentProperties();
                HashMap<String,Integer> wordCount = Tf.getTermsFromFile(file.getAbsolutePath(),count, folder);
                docProperties[count].setWordCountMap(wordCount);//Set words for each document...
                count++;
            }

        }
         count=0;


        //getWordCountmap in every document and write all of them seperately in outputAstxt...
          for(File file:lastFiles){
              if(file.isFile()){
                  String fileName=file.getName();
                  HashMap<String, Integer> eachDocumentVector = Tf.eachDocumentVector(docProperties[count].getWordCountMap());
                  String OutPutPath = "/home/ybd/Desktop/Programs/"+SET+"-"+TAG+".txt";
                  Tf.outputAsTxt(eachDocumentVector,fileName,OutPutPath);
                  count++;
              }


          }

    }


 }










