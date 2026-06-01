import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] time = new int[N];
        int[] cost = new int[N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            time[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][2];

        for(int i=N-1; i>=0; i--) {
            if(i == N-1) {
                // 선택하지 않을 경우 
                dp[i][1] = 0;
                // 선택할 경우 
                dp[i][0] = (i + time[i] <= N) ? cost[i] : 0;

                continue;
            }

            // 선택하지 않을 경우
            dp[i][1] = Math.max(dp[i+1][0], dp[i+1][1]);

            // 선택할 경우
            dp[i][0] = (i + time[i] <= N) ? cost[i] : 0; 

            int nextTime = i + time[i];
            if(nextTime < N) dp[i][0] += Math.max(dp[nextTime][0], dp[nextTime][1]);
        }

        System.out.println(Math.max(dp[0][0], dp[0][1]));
    }
}