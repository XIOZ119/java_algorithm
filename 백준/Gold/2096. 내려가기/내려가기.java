import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        int[][] dp = new int[N][3];
        int[][] minDp = new int[N][3];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int j = 0; j < 3; j++){
            dp[0][j] = arr[0][j];
            minDp[0][j] = arr[0][j];
        }

        for(int i = 1; i < N; i++){
            for(int j = 0; j < 3; j++){
                if(j == 0) {
                   dp[i][j] = Math.max(arr[i][j]+dp[i-1][j], arr[i][j]+dp[i-1][j+1]);

                   minDp[i][j] = Math.min(arr[i][j]+minDp[i-1][j], arr[i][j]+minDp[i-1][j+1]);
                }
                else if(j == 2){
                    dp[i][j] = Math.max(arr[i][j]+dp[i-1][j], arr[i][j]+dp[i-1][j-1]);

                    minDp[i][j] = Math.min(arr[i][j]+minDp[i-1][j], arr[i][j]+minDp[i-1][j-1]);
                }
                else {
                    dp[i][j] = Math.max(arr[i][j]+dp[i-1][j], arr[i][j]+dp[i-1][j-1]);
                    dp[i][j] = Math.max(dp[i][j], arr[i][j]+dp[i-1][j+1]);

                    minDp[i][j] = Math.min(arr[i][j]+minDp[i-1][j], arr[i][j]+minDp[i-1][j-1]);
                    minDp[i][j] = Math.min(minDp[i][j], arr[i][j]+minDp[i-1][j+1]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0; i < 3; i++){
            min = Math.min(minDp[N-1][i], min);
            max = Math.max(dp[N-1][i], max);
        }

        System.out.println(max + " " + min);
    }
}