import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long S = Long.parseLong(st.nextToken());

        long answer = 0;
        long minCost = Long.MAX_VALUE;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            long c = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            minCost = (minCost == Long.MAX_VALUE) ? c : Math.min(minCost + S, c);

            answer += minCost * y;
        }

        System.out.println(answer);
    }
}
