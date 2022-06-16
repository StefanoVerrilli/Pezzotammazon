package Classes.TFIDF;

import Classes.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class TFIDF {
    //Ci occupiamo prima di tutto di calcolare la frequenza di ogni parola che compare nei nomi dei prodotti
    //acquistati dall' utente i-esimo
    public void TF(){
    ArrayList<String> UserPurchase = new ArrayList<>();
    UserPurchase.add("Sabbia per gatti");
    UserPurchase.add("Giochi per bambini");
    UserPurchase.add("Oggetti per la casa");
    UserPurchase.add("Cibo per cani");
    HashMap<String, HashMap<String, Float>> TermFrequencyMap = new HashMap<String,HashMap<String,Float>>();
    for(String Purchase : UserPurchase) {
        TermFrequencyMap.put(Purchase, GetOccurrence(Purchase));
    }
    for(String Purchase : UserPurchase){
        TermFrequencyMap.put(Purchase,getFrequency(TermFrequencyMap.get(Purchase),Purchase));
    }
        //PrintTF(UserPurchase,TermFrequencyMap);
    HashMap<String, Double> SentenceWithWord = new HashMap<>();
    for(String Purchase : UserPurchase){
        for(String word : TermFrequencyMap.get(Purchase).keySet()){
            if(Purchase.contains(word)){
                if(SentenceWithWord.get(word) == null)
                    SentenceWithWord.put(word, 1.0);
                else
                    SentenceWithWord.put(word, SentenceWithWord.get(word) + 1);
                }
        }
    }
    for(String WordInText : SentenceWithWord.keySet()){
        System.out.println("Num occurence : " + SentenceWithWord.get(WordInText));
        System.out.println("Size: " + UserPurchase.size());
        SentenceWithWord.put(WordInText,(Math.log((UserPurchase.size())/(SentenceWithWord.get(WordInText)))));
        System.out.println(WordInText);
        System.out.println(SentenceWithWord.get(WordInText).floatValue());
    }

    for(String Purchase : UserPurchase){
        System.out.println("Next: ----");
        for(String word : TermFrequencyMap.get(Purchase).keySet()){
            System.out.println(word);
            System.out.println(TermFrequencyMap.get(Purchase).get(word) * SentenceWithWord.get(word));
        }
    }

    }

    private void PrintTF(ArrayList<String> UserPurchase,HashMap<String, HashMap<String, Float>> occurrenceForSentence){
        for(String Purchase: UserPurchase){
            String[] words = Purchase.split(" ");
            for (String key : occurrenceForSentence.get(Purchase).keySet()){
                System.out.println(key);
                System.out.println(occurrenceForSentence.get(Purchase).get(key));

            }
        }
    }
    private HashMap<String,Float> getFrequency(HashMap<String,Float> occurrences,String sentence){
        String[] words = sentence.split(" ");
        for (String key: occurrences.keySet()){
            occurrences.put(key,(occurrences.get(key).floatValue()/ words.length));
        }
        return occurrences;
    }
    private HashMap<String,Float> GetOccurrence(String Purchase){
        String[] words = Purchase.split(" ");
        HashMap<String,Float> Occurrence = new HashMap<>();
        for(String word : words){
            if(Occurrence.get(word) == null)
                Occurrence.put(word, 1.0F);
            else
                Occurrence.put(word,Occurrence.get(word).floatValue()+1);
        }
        return Occurrence;
    }
    public static void main(String args[]){
        TFIDF myTFIDF = new TFIDF();
        myTFIDF.TF();
    }
}
