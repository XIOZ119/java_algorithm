import java.util.*;
import java.io.*;

public class Main {
    private static int[][] arr;
    private static int N, M;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static ArrayList<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        list = new ArrayList<>();
        
        // 녹지 않은 빙하 (0 이상) list에 따로 저장해놓음
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] > 0) list.add(new int[]{i, j});
            }
        }
        
        int year = 0;
        while (!list.isEmpty()) {
            year++;
            
            // 얼음 녹이는 함수 호출
            meltIcebergs();
            
            // 얼음 나눠졌는지 확인
            if (checkSplit()) break;
        }
        
        bw.write(list.isEmpty() ? "0" : year + "");
        bw.flush();
        bw.close();
    }
    
    // 얼음 녹이는 함수
    private static void meltIcebergs() {
        int[][] melt = new int[N][M];
        ArrayList<int[]> newList = new ArrayList<>();

        // 얼음 주위(4방면)에 0 개수 melt 배열에 저장 
        for (int[] l : list) {
            int cx = l[0], cy = l[1], cnt = 0;
            for (int k = 0; k < 4; k++) {
                int nx = cx + dx[k], ny = cy + dy[k];
                if (isValid(nx, ny) && arr[nx][ny] == 0) cnt++;
            }
            melt[cx][cy] = cnt;
        }
        
        // 얼음 높이 - 주위 0 개수 => 0 이하이면 0, 0 이상이면 값 그대로 수정
        for (int[] l : list) {
            int cx = l[0], cy = l[1];
            arr[cx][cy] = Math.max(0, arr[cx][cy] - melt[cx][cy]);
            if (arr[cx][cy] > 0) newList.add(new int[]{cx, cy});
        }
        
        list = newList;
    }
    
    // 얼음 덩어리 2개 이상인지 확인
    private static boolean checkSplit() {
        if (list.isEmpty()) return false;

        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(list.get(0));
        visited[list.get(0)[0]][list.get(0)[1]] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            count++;
            
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
                if (isValid(nx, ny) && !visited[nx][ny] && arr[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        
        // 얼음 개수와 한 덩이의 개수 같은 지 반환
        return count != list.size();
    }
    
    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
