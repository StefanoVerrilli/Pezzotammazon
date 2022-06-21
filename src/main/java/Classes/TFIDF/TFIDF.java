package Classes.TFIDF;

import Classes.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class TFIDF {
    public void TF(){
    ArrayList<String> UserPurchase = new ArrayList<>();
    UserPurchase.add("Minecraft sandbox game");
    UserPurchase.add("Terraria sandbox game");
    UserPurchase.add("Pallone supersantos");
    HashMap<String, HashMap<String, Float>> TermFrequencyMap = new HashMap<String,HashMap<String,Float>>();
    //***********INIZIO TF*********
    /*Questa funzione si occupa di calcolare le occorrenze di ogni termine appartenente all' i-esimo
    acquisto e a posizionarlo in un map apposito
     */
    for(String Purchase : UserPurchase) {
        TermFrequencyMap.put(Purchase, GetOccurrence(Purchase));
    }
    /*Dalle occorrenze di ogni termina precedentemente calcolate ci occupiamo di ottenere la frequenza
    * andndo a dividere il numero di occorrenze per il numero totale di termini contenuti nell' intestazione
    * dell' acquisto*/
    for(String Purchase : UserPurchase){
        TermFrequencyMap.put(Purchase,getFrequency(TermFrequencyMap.get(Purchase),Purchase));
    }
    // ********FINE TF**********

    // *********INIZIO IDF*********
        /*Per effettuare l' idf necessitiamo di calcolare prima di tutto il numero di acquisti
        * che contengono al suo interno l' i-esimo termine per ogni termine contenuto in ogni acquisto*/
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
    /*Dopo quest' ultima operazione possiamo calcolarci l' idf andando a calcolare il logaritmo in base
    * 2 del rapporto tra il numero totale di acquisti a disposizione per il numero di acquisti che contiene
    * l' i-esimo termine che prendiamo in esame*/
    for(String WordInText : SentenceWithWord.keySet()){
        SentenceWithWord.put(WordInText,(Math.log((UserPurchase.size())/(SentenceWithWord.get(WordInText)))));
        //System.out.println(WordInText);
        //System.out.println(SentenceWithWord.get(WordInText).floatValue());
    }
    //******FINE IDF********

    /*Fatto ciò siamo arrivati alla fine dell' algoritmo, e possiamo andare a calcolare il TF-IDF come
    * il prodotto tra il risulto del TF per i' idf.*/
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
