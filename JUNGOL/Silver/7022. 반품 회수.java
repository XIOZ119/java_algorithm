import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] loc = new int[N];
        int[] time = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            loc[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        int answer = Math.max(loc[N-1], time[N-1]);

        for(int i=N-2; i>=0; i--) {
            answer += (loc[i+1] - loc[i]);

            if(answer < time[i]) answer = time[i];
        }

        answer += (loc[0] - 0);

        System.out.println(answer);
    }
}
