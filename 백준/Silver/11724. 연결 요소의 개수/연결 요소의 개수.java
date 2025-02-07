import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static boolean[] visited;
    private static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 받기
        String str = br.readLine();
        String[] arr = str.split(" ");
        
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);

        // 변수 초기화 
        visited = new boolean[N+1];
        graph = new ArrayList[N + 1];
        int answer = 0;

        // 정점 -> 연결된 노드 저장할 리스트 배열 생성
        for(int i=1; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        // 정점 받고 저장
        for(int i=1; i<M+1; i++) {
            String str2 = br.readLine();
            String[] arr2 = str2.split(" ");

            int a = Integer.parseInt(arr2[0]);
            int b = Integer.parseInt(arr2[1]);

            graph[a].add(b);
            graph[b].add(a);
        }

        for(int i=1; i<N+1; i++) {
            if(!visited[i]) {
                dfs(i);
                answer++;
            } 
        }
        bw.write(answer + "\n");
        bw.close();
    }

    public static void dfs(int node) {
        visited[node] = true;
    
        for(int con : graph[node]) {
            if(!visited[con]) {
                dfs(con);
            }
        }
    }
}