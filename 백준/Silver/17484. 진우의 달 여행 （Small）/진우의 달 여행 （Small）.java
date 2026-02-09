import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] arr;
    static int[] dx = {1, 1, 1}, dy = {-1, 0, 1};
    static int result = Integer.MAX_VALUE;

    static class Ufa {
        int x;
        int y;
        int sum;
        int dir;

        Ufa(int x, int y, int sum, int dir) {
            this.x = x;
            this.y = y;
            this.sum = sum;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<M; i++){
            bfs(0, i);
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    static void bfs(int x, int y) {
        Queue<Ufa> queue = new LinkedList<>();
        int c = arr[x][y];
        for(int i=0; i<3; i++) {
            queue.add(new Ufa(x, y, c, i));
        }

        while(!queue.isEmpty()) {
            // 만일 x == N 일 경우, 작은 값 갱신
            Ufa cur = queue.poll();

            if(cur.x == N-1) {
                result = Math.min(result, cur.sum);
                continue;
            }

            for(int i=0; i<3; i++) {
                if(cur.dir == i) continue;
                if(result < cur.sum) continue;

                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(!isValid(nx, ny)) continue;

                queue.add(new Ufa(nx, ny, cur.sum + arr[nx][ny], i));
            }

        }

    }

    static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
    
}