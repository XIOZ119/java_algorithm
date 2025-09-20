import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static int[][] arr;
    static int n; static int m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = 0;
    static int max = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j] || arr[i][j] == 0) continue;
                bfs(i, j);
            }
        }

        System.out.println(answer + "");
        System.out.println(max + "");
    }

    static void bfs(int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        visited[x][y] = true;

        int count = 0;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int cx = cur[0]; int cy = cur[1];
            count++;

            for(int i=0; i<4; i++){
                int nx = cx + dx[i]; int ny = cy + dy[i];
                if(!isValid(nx, ny) || visited[nx][ny] || arr[nx][ny] == 0) continue;

                queue.add(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }

        answer++;
        max = Math.max(max, count);
    }

    static boolean isValid(int x, int y){
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}
