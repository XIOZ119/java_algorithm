import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int arr[][];
    static int record[][][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        record = new int[N][M][2];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
                record[i][j][0] = Integer.MAX_VALUE;
                record[i][j][1] = Integer.MAX_VALUE;
            }
        }

        bfs();
        int result = Math.min(record[N-1][M-1][0], record[N-1][M-1][1]);
        if(result == Integer.MAX_VALUE) result = -1;

        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    private static void bfs() {
        Queue<int []> queue = new LinkedList<>();

        // x, y, 벽 부순 횟수, 거리
        queue.add(new int[] { 0, 0, 0, 1 });
        record[0][0][0] = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0], cy = cur[1], cz = cur[2], cd = cur[3];
            boolean broke = cz == 0 ? false : true;

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i], ny = cy + dy[i], nd = cd + 1;
                if(!isValid(nx, ny)) continue;

                boolean existWall = arr[nx][ny] == 0 ? false : true;

                if(broke) {
                    if(existWall) continue;
                    if(record[nx][ny][1] <= nd) continue;

                    record[nx][ny][1] = nd;
                    queue.add(new int[] {nx, ny, 1, nd});
                }
                else {
                    if(existWall) {
                        if(record[nx][ny][1] <= nd) continue;

                        record[nx][ny][1] = nd;
                        queue.add(new int[] {nx, ny, 1, nd});
                    }
                    else {
                        if(record[nx][ny][0] <= nd) continue;

                        record[nx][ny][0] = nd;
                        queue.add(new int[] {nx, ny, 0, nd});
                    }
                }
            }

        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 & x < N && y < M;
    }
}