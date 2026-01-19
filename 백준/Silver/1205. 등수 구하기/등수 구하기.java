import java.io.*;
import java.util.*;

public class Main {
    private static int N, P;
    private static long score;
    private static long[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        score = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        rank = new long[P];
        Arrays.fill(rank, -1);

        if(N != 0) st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            rank[i] = Long.parseLong(st.nextToken());
        }

        int answer = Integer.MAX_VALUE;
        for(int i=0; i<rank.length; i++) {
            if(i == rank.length -1 && rank[i] == score) {
                answer = -1;
            } else if(rank[i] <= score) {
                answer = Math.min(answer, i+1);
            }
        }

        if(answer > P || answer == Integer.MAX_VALUE) answer = -1;

        bw.write(answer + "");
        bw.flush();
        bw.close();
    }
}