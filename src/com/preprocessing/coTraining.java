package com.preprocessing;



import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class coTraining {
    public static HashMap<Integer,String> classifier(String modelType,String testFileArff) throws Exception{

            HashMap<Integer,String> fileNo=new HashMap<>();

        String modelFileSerialized="/home/ybd/Desktop/Programs/"+modelType;
        String testFileDoc="/home/ybd/Desktop/Programs/"+testFileArff;


        try{

            Classifier classifier =
                    (Classifier) weka.core.SerializationHelper.read(
                            modelFileSerialized);

            Instances testInstances = ConverterUtils.DataSource.read(testFileDoc);
            testInstances.setClassIndex(testInstances.numAttributes()-1);

            int numTestInstances = testInstances.numInstances();
            System.out.printf("There are %d test instances\n", numTestInstances);




            for (int i = 0; i < numTestInstances; i++) {

                // Get the true class label from the instance's own classIndex.
                String trueClassLabel =
                        testInstances.instance(i).toString(testInstances.classIndex());

                // Make the prediction here.
                double predictionIndex =
                        classifier.classifyInstance(testInstances.instance(i));




                // Get the predicted class label from the predictionIndex.
                String predictedClassLabel =
                        testInstances.classAttribute().value((int) predictionIndex);



                // Get the prediction probability distribution.
                  double[] predictionDistribution =
                        classifier.distributionForInstance(testInstances.instance(i));



                // Print out the true label, predicted label, and the distribution.
                System.out.printf("%5d: true=%-10s, predicted=%-10s, distribution=",
                        i, trueClassLabel, predictedClassLabel);

                for (int predictionDistributionIndex = 0;
                     predictionDistributionIndex < predictionDistribution.length;
                     predictionDistributionIndex++) {
                    // Get this distribution index's class label.
                    String predictionDistributionIndexAsClassLabel =
                            testInstances.classAttribute().value(
                                    predictionDistributionIndex);

                    // Get the probability.
                    double predictionProbability =
                            predictionDistribution[predictionDistributionIndex];


                    System.out.printf("[%10s : %6.3f]",
                            predictionDistributionIndexAsClassLabel,
                            predictionProbability);


                        if(trueClassLabel.equals(predictedClassLabel) && predictionProbability>=0.9 ){

                             fileNo.put(i,trueClassLabel+"//"+predictedClassLabel);
                        }



                }
                System.out.println("\n");


            }



            }catch (FileNotFoundException ex){
            System.out.println("file not found");
        }catch (IOException ex){
            System.out.println("input output mismatch...");
        }
        return fileNo;

    }
}
