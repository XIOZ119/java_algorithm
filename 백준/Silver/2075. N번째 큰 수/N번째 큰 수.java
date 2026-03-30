import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());

                if(pq.size() == N && pq.peek() < num) pq.poll();
                else if(pq.size() == N && pq.peek() > num) continue;

                pq.add(num);
            }
        }

        int answer = pq.poll();
        bw.write(answer + "");
        bw.flush();
        bw.close();
    }
}