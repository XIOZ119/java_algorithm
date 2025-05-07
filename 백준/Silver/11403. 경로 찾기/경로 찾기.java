import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int a = Integer.parseInt(st.nextToken());

                if(a == 1) graph[i].add(j);
            }
        }

        int[] result = new int[N];
        
        for(int i=0; i<N; i++){
            Arrays.fill(result, 0);
            Queue<Integer> queue = new LinkedList<>();
            
            queue.add(i);

            while(!queue.isEmpty()) {
                int cur = queue.poll();

                for(int g: graph[cur]){
                    if(result[g] == 0){
                        queue.add(g);
                        result[g] = 1;
                    }
                }
            }
            

            for(int j=0; j<N; j++){
                sb.append(result[j] + " ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}  