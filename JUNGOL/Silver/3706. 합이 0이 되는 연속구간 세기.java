import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int sum = 0;
        long answer = 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            sum += num; 

            if(map.containsKey(sum)) map.put(sum, map.get(sum) + 1);
            else map.put(sum, 1);
        }

        for(int v: map.values()) {
            answer += ((long) v * (v-1)) / 2;
        }

        System.out.println(answer);
    }
}
