import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split(" ");
        
        int test = Integer.parseInt(br.readLine());
        String[] testArr = br.readLine().split(" "); 

        Map<String, Integer> cardCount = new HashMap<>();
    
        for(int i=0; i<size; i++) {
            String card = arr[i];
            cardCount.put(card, cardCount.getOrDefault(card, 0) + 1); // 기존 값에 1을 더해주거나 없으면 1로 초기화
        } 

        for (int i=0; i<test; i++) {
            String card = testArr[i];
            bw.write(cardCount.getOrDefault(card, 0) + " ");
        }

        bw.flush();
        bw.close();
    }
}