import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[] arr = new char[N];
        
        String str = br.readLine();
        for(int i=0; i<N; i++) {
            arr[i] = str.charAt(i);
        }

        int[][] prefix = new int[N+1][3];
        // 0: R, 1: S, 2: P
        for(int i=0; i<N; i++) {
            prefix[i+1][0] = prefix[i][0];
            prefix[i+1][1] = prefix[i][1];
            prefix[i+1][2] = prefix[i][2];

            int idx = getIdx(arr[i]);
            prefix[i+1][idx]++;
        }

        int[] answer = new int[N];
        // 기준
        for(int i=0; i<N; i++) {
            if(i == 0) {
                if(isValid(arr, prefix, i, i+1, N-1)) answer[i] = 1;
                continue;
            } 
            
            if(i == N-1) {
                if(isValid(arr, prefix, i, 0, i-1)) answer[i] = 1;
                continue;
            }

            if(isValid(arr, prefix, i, i+1, N-1) && isValid(arr, prefix, i, 0, i-1)) answer[i] = 1;
        }

        for(int i=0; i<N; i++) {
            System.out.print(answer[i]);
        }
    }

    private static int getIdx(char c) {
        if (c == 'R') return 0;
        if (c == 'S') return 1;
        return 2;
    }

    private static boolean isValid(char[] arr, int[][] prefix, int cur, int start, int end) {
        if(start > end) return true;

        int r = prefix[end + 1][0] - prefix[start][0];
        int s = prefix[end + 1][1] - prefix[start][1];
        int p = prefix[end + 1][2] - prefix[start][2];

        if(arr[cur] == 'R') {
            return s > 0 || p == 0;
        }

        if(arr[cur] == 'S') {
            return p > 0 || r == 0;
        }

        return r > 0 || s == 0;
    }
}
