import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String,Integer> words = new HashMap<>();
        HashMap<String, String> wordList = new HashMap<String, String>();
        int N = Integer.parseInt(br.readLine());
        String[] wordArray = new String[N];

        int index = Integer.MAX_VALUE;
        int answer = 0;
        String word1 = "";
        String word2 = "";
        for(int i=0; i<N; i++){
            String string = br.readLine();
            wordArray[i] = string;
            if(words.containsKey(string)) continue;

            words.put(string, i);
            String w = "";
            for(int j=0; j<string.length(); j++){
                w += string.charAt(j);

                if(wordList.isEmpty() || !wordList.containsKey(w)) {
                    wordList.put(w, string);
                    continue;
                }

                if(answer < w.length()) {
                    answer = w.length();
                    word1 = wordList.get(w);
                    word2 = string;
                    index = words.get(word1);
                }

                else if(answer == w.length()) {
                    if(index > words.get(wordList.get(w))) {
                        word1 = wordList.get(w);
                        word2 = string;
                        index = words.get(word1);
                    }
                }

            }
        }
        if(answer == 0) {
            word1 = wordArray[0];
            word2 = wordArray[1];
        }

        System.out.println(word1);
        System.out.println(word2);
    }
}