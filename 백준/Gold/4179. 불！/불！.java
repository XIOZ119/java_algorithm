import java.io.*;
import java.util.*;

public class Main {
    private static int R, C;
    private static char[][] arr;
    private static int[][] fire;
    private static int[][] jihoon;

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        fire = new int[R][C];
        jihoon = new int[R][C];

        int startX = 0, startY = 0;

        for (int i = 0; i < R; i++) {
            Arrays.fill(fire[i], -1);
            Arrays.fill(jihoon[i], -1);
        }

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = str.charAt(j);

                if (c == 'J') {
                    jihoon[i][j] = 0;
                    startX = i;
                    startY = j;
                }

                if (c == 'F') {
                    fire[i][j] = 0;
                }

                arr[i][j] = c;
            }
        }

        // 지훈이가 처음부터 가장자리에 있는지
        if (startX == 0 || startX == R - 1 || startY == 0 || startY == C - 1) {
            bw.write("1\n");
            bw.flush();
            bw.close();
            return;
        }

        // 가장자리가 전부 벽인지
        if (!canEscape()) {
            bw.write("IMPOSSIBLE\n");
            bw.flush();
            bw.close();
            return;
        }

        bfs(fire, "fire");
        bfs(jihoon, "person");

        // 가장자리 체크 -> 불 시간보다 지훈 시간이 빠른 경우 탈출 가능
        int answer = check();

        if (answer == -1) {
            bw.write("IMPOSSIBLE\n");
        } else {
            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static int check() {
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (i == 0 || i == R - 1 || j == 0 || j == C - 1) { // 가장자리 확인
                    if (!isValid(i, j)) continue; // 벽인 경우 무시
                    if (jihoon[i][j] > -1 && (fire[i][j] == -1 || jihoon[i][j] < fire[i][j])) answer = Math.min(jihoon[i][j] + 1, answer);
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private static boolean canEscape() {
        boolean result = false;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if ((i == 0 || i == R - 1 || j == 0 || j == C - 1) && isValid(i, j)) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    private static void bfs(int[][] arr, String type) {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 0) {
                    queue.add(new int[]{i, j, 0});
                }
            }
        }

        while (!queue.isEmpty()) {
            int cur[] = queue.poll();
            int curX = cur[0], curY = cur[1], curD = cur[2];

            for (int dir = 0; dir < 4; dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];
                int nd = curD + 1;

                if (!isValid(nx, ny)) continue;

                if ("person".equals(type)) {
                    // 지훈이 이동 시 불이 이미 도달했거나, 불이 도달할 예정인 칸은 갈 수 없음
                    if (fire[nx][ny] > -1 && fire[nx][ny] <= nd) continue;
                }

                if (arr[nx][ny] > -1) continue;

                queue.add(new int[]{nx, ny, nd});
                arr[nx][ny] = nd;
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C && arr[x][y] != '#';
    }
}
