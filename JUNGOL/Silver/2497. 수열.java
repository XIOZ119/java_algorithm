import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = Integer.MIN_VALUE;

        int left = 0;
        int right = 0;
        int sum = 0;
        while(right < N) {
            if(right - left + 1 < K) {
                sum += arr[right];
                right++;
                continue;
            }

            sum += arr[right];

            answer = Math.max(sum, answer);
            
            sum -= arr[left];
            left++;
            right++;
        }

        System.out.println(answer);
    }
}
