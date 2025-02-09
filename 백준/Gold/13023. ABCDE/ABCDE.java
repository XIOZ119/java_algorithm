import java.util.*;
import java.io.*;

class Main {
    private static boolean[] visited;
    private static ArrayList<Integer>[] arr;
    private static int result = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputConditionArr = br.readLine().split(" ");

        int N = Integer.parseInt(inputConditionArr[0]);
        int M = Integer.parseInt(inputConditionArr[1]);

        // 입력에 맞게 초기화 
        visited = new boolean[N];
        arr = new ArrayList[N];

        for(int i = 0; i < N; i++){
            arr[i] = new ArrayList<>();
        }

        // 간선 입력 처리
        for(int i = 0; i < M; i++){
            String[] inputNodeArr2 = br.readLine().split(" ");

            int a = Integer.parseInt(inputNodeArr2[0]);
            int b = Integer.parseInt(inputNodeArr2[1]);

            arr[a].add(b);
            arr[b].add(a);
        }

        // 모든 노드에 대해 DFS 실행
        for(int i = 0; i < N; i++) {
            if(result == 1) break; // 정답을 찾았다면 탐색 종료
            
            if(!visited[i]){
                dfs(i, 1); // DFS 호출
            }
        }

        bw.write(result + "\n"); // 결과 출력
        bw.flush();
    }

    public static void dfs(int node, int depth){
        if(depth == 5) { // depth가 5일 때 (문제에서 요구하는 5명 관계)
            result = 1; // 관계가 형성되면 1로 설정
            return;
        }
        
        visited[node] = true; // 현재 노드를 방문 처리

        for(int child: arr[node]) { 
            if(!visited[child]) { // 자식 노드를 방문하지 않았다면
                dfs(child, depth + 1); // 재귀 호출
                if(result == 1) return; // 결과가 1이면 더 이상 탐색하지 않음
            }
        }

        visited[node] = false; // DFS 탐색 완료 후 방문 처리 취소
    }
}