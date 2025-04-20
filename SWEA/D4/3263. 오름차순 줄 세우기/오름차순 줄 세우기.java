import java.io.*;
import java.lang.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int testCase = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=testCase; tc++) {
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N];
            int[] count = new int[N+1];

            int max = 0;
            st = new StringTokenizer(br.readLine());

            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());

                count[arr[i]] = count[arr[i] - 1]+1;
                
                max = Math.max(count[arr[i]], max);
            }

            int result = N - max;
            
            sb.append("#" + tc + " " + result + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

