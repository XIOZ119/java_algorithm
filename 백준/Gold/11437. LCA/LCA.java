import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static ArrayList<Integer>[] tree; 
    private static boolean[] visited;
    private static int[] parent;
    private static int[] depths;
    private static int result;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N+1];
        parent = new int[N+1];
        depths = new int[N+1];
        visited = new boolean[N+1];

        for(int i=0; i<N+1; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i=1; i<N; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1, 0);

        int M = Integer.parseInt(br.readLine());

        for(int i=0; i<M; i++) {
            String[] input = br.readLine().split(" ");

            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            result = 0;
            find(a, b);
            System.out.println(result);
        }
    }

    private static void dfs(int node, int depth) {
        visited[node] = true;
        depths[node] = depth;

        for(int child: tree[node]) {
            if(visited[child] == false) {
                parent[child] = node;
                dfs(child, depth+1);
            }
        }
    }

    private static void find(int a, int b){

        int depthA = depths[a];
        int depthB = depths[b];

        if(depthA > depthB) find(parent[a], b);
        
        else if(depthA < depthB) find(a, parent[b]);

        else {
            if(a == b) {
                result = a;
            } else {
                find(parent[a], parent[b]);
            }
        }
    }
}