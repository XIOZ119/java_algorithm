import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); 

        int[][] arr = new int[N+1][M+1];
        for(int i=0; i<N+1; i++) {
            Arrays.fill(arr[i], Integer.MAX_VALUE);
        }

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] dy = {-2, -1, 1, 2, -2, -1, 1, 2};

        Queue<int[]> que = new LinkedList<>();
        arr[R][C] = 0;

        que.add(new int[] {R, C, 0});
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int cx = cur[0]; int cy = cur[1];
            int cd = cur[2];

            for(int i=0; i<8; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(!isValid(nx, ny)) continue;
                if(arr[nx][ny] <= cd + 1) continue;

                que.add(new int[] {nx, ny, cd + 1});
                arr[nx][ny] = cd + 1;
            }
        }

        System.out.println(arr[S][K]);
    }

    private static boolean isValid(int x, int y) {
        return x >= 1 && y >= 1 && x <= N && y <= M;
    }
}
