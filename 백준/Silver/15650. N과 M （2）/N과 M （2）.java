import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static StringBuilder sb;
    static boolean[] visited;
    static int[] record;
    static int n, m;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n+1];
        record = new int[m];

        perm(1, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void perm(int start, int depth) {
        if(depth == m){
            for(int i=0; i<m; i++){
                sb.append(record[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=start; i<n+1; i++){
            if(!visited[i]){
                visited[i] = true;
                record[depth] = i;
                perm(i+1, depth+1);
                visited[i] = false;
            }
        }
    }
}