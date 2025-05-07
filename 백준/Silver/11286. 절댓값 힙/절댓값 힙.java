import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int []>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]){
                    return o1[0] - o2[0]; 
                }
                return o1[1] - o2[1];
            }
        });

        for(int i=0; i<N; i++){
            int input = Integer.parseInt(br.readLine());

            if(input == 0){
                if(pq.isEmpty()){
                    sb.append("0 \n");
                } else {
                    int[] cur = pq.poll();
                    sb.append(cur[0] + "\n");
                }
            } else {
                pq.add(new int[] {input, Math.abs(input)});
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}  