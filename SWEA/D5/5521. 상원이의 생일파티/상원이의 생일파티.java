import java.io.*;
import java.util.*;

public class Solution {

    static int N, M;
    static ArrayList<Integer>[] graph;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=testCase; tc++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N];

            for(int i=0; i<N; i++) {
                graph[i] = new ArrayList<>();
            }

            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                graph[a].add(b);
                graph[b].add(a);
            }

            result = 0;
            bfs();
            
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    } 

    static void bfs() {
        Queue<int []> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];
        queue.add(new int[] {0, 0});
        visited[0] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int cIndex = cur[0];
            int cDepth = cur[1];

            if(cDepth == 2) continue;

            for(int c: graph[cIndex]){ 
                if(!visited[c]) { 
                    visited[c] = true; 
                    queue.add(new int[] {c, cDepth+1}); 
                    result++;
                }
            }
        }
        
    }
}
