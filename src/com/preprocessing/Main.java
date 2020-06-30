package com.preprocessing;



public class Main {

    public static void main(String[] args)  {


        try{
            unique unique=new unique();
            TFCalculator tfCalculator=new TFCalculator();
            Txt2Arff txt2Arff=new Txt2Arff();
            CreateModel createModel=new CreateModel();
            coTraining coTraining=new coTraining();





            unique.writeunique("co-training-train","<P>");//For TRAIN SET unique words on related documents for <P> tags...
            unique.writeunique("co-training-train","<ANCH>");//For TRAIN SET unique words on related documents for <ANCH> tags...


            // P tag's arff...
            tfCalculator.createVector("co-training-train","<P>");
            txt2Arff.txt2Arff("co-training-train","<P>");
            tfCalculator.createVector("co-training-test","<P>");
            txt2Arff.txt2Arff("co-training-test","<P>");

            // ANCH's arff..
            tfCalculator.createVector("co-training-train","<ANCH>");
            txt2Arff.txt2Arff("co-training-train","<ANCH>");
            tfCalculator.createVector("co-training-test","<ANCH>");
            txt2Arff.txt2Arff("co-training-test","<ANCH>");





            //Train.P arff convert to model..
            createModel.toModel("co-training-train-<P>.arff","<P>");

            //Train.ANCH arff convert to model..
            createModel.toModel("co-training-train-<ANCH>.arff","<ANCH>");


                //Look probability of each class from test arffs on models
               coTraining.classifier("<P>.model",
                       "co-training-test-<P>.arff");


                System.out.println("\n");

               coTraining.classifier("<ANCH>.model",
                    "co-training-test-<ANCH>.arff");




             //check co-training train accuracy on Program's test datasets.

            /*unique.writeunique("co-training-train","<P>");//For TRAIN SET unique words on related documents for <P> tags...
            unique.writeunique("co-training-train","<ANCH>");//For TRAIN SET unique words on related documents for <ANCH> tags...


            // P tag's arff...
            tfCalculator.createVector("co-training-train","<P>");
            txt2Arff.txt2Arff("co-training-train","<P>");
            tfCalculator.createVector("/dataset/test","<P>");
            txt2Arff.txt2Arff("/dataset/test","<P>");

            // ANCH's arff..
            tfCalculator.createVector("co-training-train","<ANCH>");
            txt2Arff.txt2Arff("co-training-train","<ANCH>");
            tfCalculator.createVector("/dataset/test","<ANCH>");
            txt2Arff.txt2Arff("/dataset/test","<ANCH>");
             */





        }catch (Exception e){
            System.out.println("Input output mismatch...");
        }


    }
}
