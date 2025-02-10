import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int[][] arr;
    private static boolean[][] visited;
    // 4방향 탐색 (상, 하, 좌, 우)
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    private static int N, M;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String InputCondition[] = br.readLine().split(" ");
        N = Integer.parseInt(InputCondition[0]);
        M = Integer.parseInt(InputCondition[1]);
        
        arr = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        
        for(int i=1; i<=N; i++){
            String node = br.readLine();
            for(int j=1; j<=M; j++){
                arr[i][j] = node.charAt(j-1) - '0';
            }
        }

        bfs(1, 1);
        bw.write(arr[N][M] + "");
        bw.flush();
    }

    private static void bfs(int nodeX, int nodeY) {
        Queue<int []> queue = new LinkedList<>(); 
        visited[nodeX][nodeY] = true;
        queue.add(new int[] {nodeX, nodeY}); 
        while(!queue.isEmpty()) {
            int now[] = queue.poll();
            for(int i=0; i<4; i++){
                int x = dx[i] + now[0];
                int y = dy[i] + now[1];

                if(x>0 && x<N+1 && y>0 && y<M+1){
                    if(arr[x][y] != 0 && !visited[x][y]) {
                        visited[x][y] = true;
                        arr[x][y] = arr[now[0]][now[1]] + 1;
                        queue.add(new int[] {x, y});
                    }
                }
            }
        }
        
    }
}