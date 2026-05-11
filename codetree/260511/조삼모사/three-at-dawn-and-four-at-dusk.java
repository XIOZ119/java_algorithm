import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] arr; 
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[] visited = new boolean[N+1];
        comb(1, visited, 0);

        System.out.println(answer);
    }

    static void comb(int start, boolean[] visited, int size) {
        if(size == N / 2) {
            int red = 0;
            int blue = 0;

            for(int i=1; i<=N; i++) {
                if(visited[i]) {
                    for(int j=i+1; j<=N; j++) {
                        if(visited[j]) {
                            red += arr[i][j];
                            red += arr[j][i];
                        }
                    }
                } else {
                    for(int j=i+1; j<=N; j++) {
                        if(!visited[j]) {
                            blue += arr[i][j];
                            blue += arr[j][i];
                        }
                    }
                }
            }

            answer = Math.min(answer, Math.abs(red - blue));
            return;
        }

        for(int i=start; i<=N; i++) {
            visited[i] = true;
            comb(i+1, visited, size + 1);
            visited[i] = false;
        }
    }
}