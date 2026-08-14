import java.io.*;
import java.util.*;

public class Main {
    static int N; 
    static int answer;
    static boolean[] col, diagonal1, diagonal2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        answer = 0;

        col = new boolean[N];   
        diagonal1 = new boolean[2 * N - 1];
        diagonal2 = new boolean[2 * N - 1];

        dfs(0);

        System.out.println(answer);
    }

    private static void dfs(int row) {
        if(row >= N) {
            answer++;
            return;
        }

        for(int j=0; j<N; j++) {
            if(col[j] || diagonal1[row+j] || diagonal2[row-j+N-1]) continue;
            
            col[j] = true;
            diagonal1[row+j] = true;
            diagonal2[row-j+N-1] = true;

            dfs(row+1);

            col[j] = false;
            diagonal1[row+j] = false;
            diagonal2[row-j+N-1] = false;
        }
    }
}
