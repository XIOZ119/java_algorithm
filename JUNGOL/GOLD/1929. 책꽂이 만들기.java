import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>(); 

        for(int i=0; i<N; i++) {
            long num = Long.parseLong(br.readLine());
            pq.add(num);
        }

        while(pq.size() > 1) {
            long a = pq.poll();
            long b = pq.poll();

            long sum = a+b;

            answer += sum;

            pq.add(sum);
        }

        System.out.println(answer);
    }
}
