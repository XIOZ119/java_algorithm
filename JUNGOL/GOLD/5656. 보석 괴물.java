import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        String str = br.readLine(); 
        for(int i=0; i<N; i++) {
            arr[i] = str.charAt(i) - '0';
        }

        int[] prefix = new int[N+1];
        
        for(int i=1; i<=N; i++) {
            prefix[i] = prefix[i-1] + arr[i-1];
        }

        int[] values = new int[N+1];

        for(int i=0; i<=N; i++) {
            values[i] = prefix[i] - i;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int value: values) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }

        long sum = 0;
        for(Integer value: map.values()) {
            sum += (long) value * (value-1) / 2;
        }

        System.out.println(sum);
    }
}
