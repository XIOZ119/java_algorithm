import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 기준 길이

        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<N; i++){
            String str = br.readLine();
            if(str.length()<M) continue;

            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        List<String> words = new ArrayList<>(map.keySet());

        // 정렬 기준: 빈도수 내림차순 -> 길이 내림차순 -> 사전순 오름차순
        Collections.sort(words, (a,b) -> {
            if(map.get(a) != map.get(b)) {
                return map.get(b) - map.get(a);
            }
            if(a.length() != b.length()) {
                return b.length() - a.length();
            }
            return a.compareTo(b);
        });

        for(String word: words){
            bw.write(word + "\n");
        }
        bw.flush();
        bw.close();
    }
}