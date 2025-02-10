import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static ArrayList<Integer>[] arr;
    private static boolean[] visited;
    private static String result = "";
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputConditino = br.readLine().split(" ");
        int N = Integer.parseInt(inputConditino[0]);
        int M = Integer.parseInt(inputConditino[1]);
        int start = Integer.parseInt(inputConditino[2]);

        arr = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i = 1; i <= N; i++ ){
            arr[i] = new ArrayList<>();
        }

        for(int i=1; i<= M; i++){
            String[] inputNode = br.readLine().split(" ");
            int a = Integer.parseInt(inputNode[0]);
            int b = Integer.parseInt(inputNode[1]);

            arr[a].add(b);
            arr[b].add(a);
        }

        // 정렬 (작은 수부터 탐색하기 위해)
        for(int i=1; i<= N; i++) {
            Collections.sort(arr[i]);
        }

        //DFS 연결
        dfs(start);
        bw.write(result);
        bw.write("\n");

        result = "";
        visited = new boolean[N+1];
        
        //BFS 연결
        bfs(start);
        bw.write(result);
        bw.flush();
    }

    private static void dfs(int node) {
        visited[node] = true;
        result += node + " ";
        for(int child: arr[node]) {
            if(!visited[child]){
                dfs(child);
            }
        }
    }

    private static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        // 큐에 시작 노드 삽입 (add 연산)
        queue.add(node);
        // visited 배열에 현재 노드 방문 기록하기
        visited[node] = true;
        
        // while (큐가 비어 있을 때까지)
        while(!queue.isEmpty()) {
            // 큐에서 노드 데이터 가져오기 (poll 연산)
            int now_node = queue.poll();
            // 가져온 노드 출력하기 
            result += now_node + " ";
            // 현재 노드의 연결 노드 중 미방문 노드를 큐에 삽입 (add 연산) 하고 방문 배열에 기록하기 
            for(int child: arr[now_node]) {
                if(!visited[child]){
                    queue.add(child);
                    visited[child] = true;
                }
            }
        }
        
    }
}