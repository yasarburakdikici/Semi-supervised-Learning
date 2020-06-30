package com.preprocessing;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;

public class CreateModel {
    public static void toModel(String doc,String TAG) {

        try{
            String fileName="/home/ybd/Desktop/Programs/"+doc;
            Classifier cls = new NaiveBayesMultinomial();
            Instances inst = new Instances(
                    new BufferedReader(
                            new FileReader(fileName)));
            inst.setClassIndex(inst.numAttributes()-1);
            cls.buildClassifier(inst);


            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("/home/ybd/Desktop/Programs/"+TAG+"."+"model"));
            oos.writeObject(cls);
            oos.flush();
            oos.close();
        }catch (Exception exception){
            exception.printStackTrace();

        }

    }
}
