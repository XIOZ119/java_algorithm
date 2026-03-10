import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long answer = 1;

        int left = 0;
        int right = 1;

        int[] flag = new int[100_001];
        flag[arr[left]] = 1;

        while(left < right && right < N) {
            int num = arr[right];

            while(flag[num] == 1) {
                flag[arr[left]]--;
                left++;
            }

            answer += right - left + 1;
            flag[num]++;
            right++;
        }

        bw.write(answer + " ");
        bw.flush();
        bw.close();
    }
}