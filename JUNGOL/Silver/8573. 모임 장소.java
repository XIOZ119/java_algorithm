import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long[] prefix = new long[N+1];
        for(int i=0; i<N; i++) {
            prefix[i+1] = prefix[i] + arr[i];
        }

        long min = Long.MAX_VALUE;
        Set<Integer> set = new LinkedHashSet<>();

        for(int i=0; i<N; i++) {
            long left = (long) arr[i] * i - prefix[i];
            long right = (prefix[N] - prefix[i+1]) - (long) arr[i] * (N-i-1);

            long sum = left + right;

            if(sum < min) {
                min = sum; 
                set = new LinkedHashSet<>();
                set.add(arr[i]);
            }
            else if(sum == min) {
                set.add(arr[i]);
            }
        }

        for(int s: set) {
            System.out.print(s + " ");

        }
    }
}
