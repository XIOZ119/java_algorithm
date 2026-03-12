import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 길이
        int K = Integer.parseInt(st.nextToken()); // 중복 가능 개수

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if(N == 1) {
            bw.write(1 + "");
            bw.flush();
            bw.close();
            return;
        };

        int[] count = new int[100_001];
        int left = 0;
        int right = 1;
        count[arr[0]]++;
        int answer = right - left;

        while(left < right) {
            int next = arr[right];
            while(count[next] >= K) {
                count[arr[left]]--;
                left++;
            }
            answer = Math.max(answer, right - left + 1);
            count[next]++;
            right++;
            if(right == N) break;
        }

        bw.write(answer + " ");
        bw.flush();
        bw.close();
    }
}