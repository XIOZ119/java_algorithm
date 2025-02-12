import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int low = 1;
        int high = k;

        // 이진 탐색
        while (low < high) {
            int mid = (low + high) / 2;  
            int count = 0;

            // mid보다 작거나 같은 값들의 개수를 세기
            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);  // i * j <= mid인 j의 개수 계산
            }

            
            if (count >= k) {
                high = mid;
            } else {
                low = mid + 1;  
            }
        }

        bw.write(low + "\n");  
        bw.flush();
    }
}