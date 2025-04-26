import java.io.*;
import java.util.StringTokenizer;

public class Main {

    /*
     *  3
        26 40 83
        49 60 57
        13 89 99
     */
    /*
     * DP 
     * 각 집마다 색깔을 결정할 때, 바로 이전 집의 색깔에 영향을 받음.
     * '이전 상태'를 기억하고, 그 위에 쌓아가는 식으로 최소 비용 누적
     * dp[i][0] : i번째 집을 빨강으로 칠할 때 최종 비용
     *   = min(dp[i-1][1], dp[i-1][2]) : i-1 번째 빨강을 제외한 색으로 칠할 때 비용 중 최소 선택 
     *     + arr[i][0] : i번째 집을 빨강으로 칠할 때 비용
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        int[][] dp = new int[N][3];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int home=0; home<N; home++){
            for(int color=0; color<3; color++) {
                if(home == 0) {
                    dp[home][color] = arr[home][color];
                }
                else {
                    if(color == 0) {
                        dp[home][color] = Math.min(dp[home-1][color+1], dp[home-1][color+2]) + arr[home][color];
                    }
                    if(color == 1) {
                        dp[home][color] = Math.min(dp[home-1][color-1], dp[home-1][color+1]) + arr[home][color];
                    }
                    if(color == 2) {
                        dp[home][color] = Math.min(dp[home-1][color-1], dp[home-1][color-2]) + arr[home][color];
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++){
            result = Math.min(result, dp[N-1][i]);
        }
        
        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}  