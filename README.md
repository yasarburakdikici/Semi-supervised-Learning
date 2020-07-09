# Co-training in SSL.

 You can use your dataset in this program or send e-mail for Program's dataset. 
 If your dataset train and test content not equal and they have two class non-related and related or something like this and then 
 this project prediction test dataset's class a document in which part of related or non-related.
 How do this it looks probability of each document on test datasets and which document probability of higher than others we can pick it and put on train dataset
 so we can do this for many iterations.
 Generally,purpose of co-training is extend of train dataset.
 For each iteration,pick up 3 non-related document and 1 related document which probability of higher exactly close to 1.00.
 Firstly, you must select 12 non related and 4 related document on train dataset and other document on train dataset we should think that they are not classify
 split it for (co-training-train),(co-training-test) and start iterations so extend simple train dataset for fake test dataset(fake dataset=co-training-test).
 Finally, you can check on weka with NaiveBayesMultinominal train model and first test dataset,analyze of accuracy learning.
 
 # Usage
 
   You must add weka.jar for your project and you can also take only src.com.preprocessing classes.
   Preprocessing have a lot of steps so one of them you must use Stopwords in English and keep it any txt file in root directory.
   
 # Information
   
   This project dataset name is Programs.Project learning on ANCH and P tags.All of tags scan for this two tags and predict which document has which one of class (related or non-related).
   
   If you build this project completely you can send e-mail so use Programs dataset on this project.

