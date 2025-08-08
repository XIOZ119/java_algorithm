import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static List<Integer>[] arr;
    static boolean[] visited;
    static StringBuilder sb;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n+1];

        for(int i=0; i<n+1; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);
        }

        for(int i=1; i<n+1; i++){
            Collections.sort(arr[i]);
        }

        visited = new boolean[n+1];
        dfs(v);
        
        sb.append("\n");

        visited = new boolean[n+1];
        bfs(v);
        
        bw.write(sb.toString());
        bw.flush();
    }

    static void dfs(int start){
        visited[start] = true;
        sb.append(start + " ");

        for(int a: arr[start]){
            if(visited[a]) continue;

            dfs(a);
        }
    }

    static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int cur = queue.poll();
            sb.append(cur + " ");
            
            for(int next: arr[cur]){
                if(visited[next]) continue;

                visited[next] = true;
                queue.add(next);
            }
        }
    }
}