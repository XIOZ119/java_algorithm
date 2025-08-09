import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m;
    static int[] pick;
    static boolean[] visited;
    static StringBuilder sb;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n+1];
        pick = new int[m];

        dfs(1, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int start, int depth){
        if(depth == m){
            for(int i=0; i<m; i++){
                sb.append(pick[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1; i<n+1; i++){
            if(!visited[i]){
                visited[i] = true;
                pick[depth] = i;
                dfs(start, depth+1);
                visited[i] = false;
            }
        }
    }
}