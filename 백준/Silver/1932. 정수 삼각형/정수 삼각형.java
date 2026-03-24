import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        long[][] dx = new long[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<=i; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                dx[i][j] = arr[i][j];
            }
        }

        if(N == 1) {
            System.out.println(dx[0][0] + "");
            return;
        }
        dx[1][0] += dx[0][0];
        dx[1][1] += dx[0][0];

        for(int i=1; i<N-1; i++) {
            for(int j=0; j<i+1; j++) {
                dx[i+1][j] = Math.max(dx[i+1][j], dx[i][j] + arr[i+1][j]);
                dx[i+1][j+1] = Math.max(dx[i+1][j+1], dx[i][j] + arr[i+1][j+1]);
            }
        }

        long answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, dx[N-1][i]);
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
    }
}