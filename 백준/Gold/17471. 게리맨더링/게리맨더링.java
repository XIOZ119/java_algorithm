import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static ArrayList<Integer>[] graph;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i < N+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        graph = new ArrayList[N+1];

        for(int i=0; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());

            // 양방향 그래프
            for(int j=0; j<m; j++) {
                int a = Integer.parseInt(st.nextToken());
                graph[i].add(a);
                graph[a].add(i);
            }
        }

        answer = Integer.MAX_VALUE;
        comb(new boolean[N+1], 0, 0);
        
        if(answer == Integer.MAX_VALUE){
            bw.write("-1");
        } else {
            bw.write(answer + "");
        }
        bw.flush();
        bw.close();
    }
    
    private static void comb(boolean[] color, int count, int start) {

        if(count == N) return;

        // 그래프로 이어진 곳인지 확인하기
        if(count != 0){
            if(linked(color)){
                int red = 0;
                int blue = 0;
                for(int i=1; i<N+1; i++) {
                    if(color[i]) red += arr[i];
                    else blue += arr[i];
                }
    
                int r = Math.abs(red - blue);
                answer = Math.min(answer, r);
            }
        }
        

        for(int i=start; i<N+1; i++) {
            if(!color[i]){
                color[i] = true;
                comb(color, count+1, i+1);
                color[i] = false;
            }
        }
    }

    private static boolean linked(boolean[] color){
        boolean red = true, blue = true;
        int r = 0, b = 0;

        for(int i=1; i<N+1; i++) {
            if(color[i] && r == 0) {
                red = isConnected(i, color);
                r++;
            }
            if(!color[i] && b == 0) {
                blue = isConnected(i, color);
                b++;
            }
        }
        
        return red && blue;
    }

    private static boolean isConnected(int start, boolean[] color) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int c: graph[cur]){
                if(color[c] != color[start] || visited[c]) continue;

                queue.add(c);
                visited[c] = true;
            }
        }

        for(int i=1; i<N+1; i++) {
            if(color[i] == color[start]) {
                if(!visited[i]) return false;
            }
        }

        return true;
    }
}
