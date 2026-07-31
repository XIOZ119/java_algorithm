import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int[][] nextArr; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag = true;
        int answer = 0;
        while(flag) {
            flag = false; 

            nextArr = new int[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(nextArr[i][j] != 0) continue;

                    boolean re = bfs(i, j);
                    if(re) flag = true;
                }
            }
            
            if(flag) {
                answer++;

                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        arr[i][j] = nextArr[i][j];
                    }
                } 
            }
        }

        System.out.println(answer);
    }

    private static boolean bfs(int x, int y) {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        int sum = arr[x][y], cnt = 1;
        que.add(new int[]{x, y});
        visited[x][y] = true;
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int cx = cur[0], cy = cur[1];

            for(int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(!isValid(nx, ny) || visited[nx][ny] || nextArr[nx][ny] != 0) continue;
                
                int num = Math.abs(arr[nx][ny] - arr[cx][cy]);
                if(num < L || num > R) continue;
                
                sum += arr[nx][ny];
                cnt++;
                visited[nx][ny] = true;
                que.add(new int[] {nx, ny});
            }
        }

        int amount = sum / cnt;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(visited[i][j]) nextArr[i][j] = amount;
            }
        }
        
        if(cnt > 1) return true;
        
        return false;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}