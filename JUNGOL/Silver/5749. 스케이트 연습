import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] arr = new long[N+2];
        for(int i=1; i<=N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        long answer = 0;
        long[] ans = new long[N+2];
        for(int i=N; i>=0; i--) {
            if(ans[i+1] < arr[i]) ans[i] = ans[i+1] + 1;
            else if(ans[i+1] == arr[i]) ans[i] = ans[i+1];
            else ans[i] = arr[i];

            answer += ans[i];
        }

        System.out.println(answer);
    }
}
