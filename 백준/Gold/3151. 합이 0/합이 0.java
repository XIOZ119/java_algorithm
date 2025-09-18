import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        long answer = 0L;

        for (int i = 0; i < N - 2; i++) {
            // 최적화: i가 양수면 이후는 전부 양수라 합이 0 불가
            if (arr[i] > 0) break;

            int left = i + 1, right = N - 1;

            while (left < right) {
                long sum = (long)arr[i] + arr[left] + arr[right];

                if (sum == 0) {
                    if (arr[left] == arr[right]) {
                        long m = right - left + 1L;   // 남은 동일 값 개수
                        answer += m * (m - 1) / 2;    // mC2
                        break; // 이 구간은 모두 처리됨
                    } else {
                        int lv = arr[left];
                        int rv = arr[right];

                        long lcnt = 0;
                        while (left < right && arr[left] == lv) { lcnt++; left++; }

                        long rcnt = 0;
                        while (left <= right && arr[right] == rv) { rcnt++; right--; }

                        answer += lcnt * rcnt;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(answer);
    }
}
