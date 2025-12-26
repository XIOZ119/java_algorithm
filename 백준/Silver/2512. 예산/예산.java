import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 지방의 수
        int[] request = new int[N]; // 각 지방의 예산 요청

        int max = Integer.MIN_VALUE;
        int result = Integer.MIN_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            request[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, request[i]);
        }

        int total = Integer.parseInt(br.readLine()); // 총 예산

        int min = 0;
        int mid = max / 2;

        while(min <= max) {
            int sum = 0;

            for(int i = 0; i < N; i++) {
                if(request[i] > mid) sum += mid;
                else sum += request[i];
            }

            if(sum == total) {
                result = mid;
                break;
            }
            else if(sum > total) {
                max = mid - 1;
                mid = (min + max) / 2;
            }
            else {
                result = Math.max(result, mid);
                min = mid + 1;
                mid = (min + max) / 2;
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}