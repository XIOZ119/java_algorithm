import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] prev = new int[M];
        for(int i=0; i<M; i++){
            if(i == 0) prev[i] = arr[0][i];
            else prev[i] = prev[i-1] + arr[0][i];
        }

        for(int i=1; i<N; i++) {
            // left 왼 -> 오
            int[] left = new int[M];
            for(int j=0; j<M; j++){
                if(j == 0) left[j] = prev[j] + arr[i][j];
                else left[j] = Math.max(prev[j], left[j-1]) +  arr[i][j];
            }

            // right 오 -> 왼
            int[] right = new int[M];
            for(int j=M-1; j>=0; j--){
                if(j == M-1) right[j] = prev[j] + arr[i][j];
                else right[j] = Math.max(prev[j], right[j+1]) +  arr[i][j];
            }

            for(int j=0; j<M; j++){
                prev[j] = Math.max(left[j], right[j]);
            }
        }

        bw.write(prev[M-1] + "\n");
        bw.flush();
        bw.close();
    }
}