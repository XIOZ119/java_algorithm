import java.util.*;
import java.io.*;
 
public class d4_minesweeper1868 {
    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static boolean[][] visited;
    private static int N;
    private static char[][] arr;
    private static ArrayList<int []> zero;
    private static int clickCount;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
         
        int testCase = Integer.parseInt(br.readLine());
         
        for(int tc=1; tc<=testCase; tc++) {
             
            N = Integer.parseInt(br.readLine());
             
            arr = new char[N][N];
            visited = new boolean[N][N];
             
            for(int i=0; i<N; i++) {
                String str = br.readLine();
                for(int j=0; j<N; j++) {
                    arr[i][j] = str.charAt(j);
                }
            }
             
            zero = new ArrayList<>();
             
            // 지뢰가 아닌 곳 중, 주위에 지뢰가 없으면 zero list 추가
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(arr[i][j] == '.') {
                        boolean bomb = true;
                         
                        for(int k=0; k<8; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                             
                            if(isValid(nx, ny) && arr[nx][ny] == '*') {
                                bomb = false;
                                break;
                            }
                        }
                        if(bomb) zero.add(new int[] {i, j});
                    }
                }
            }
             
            clickCount = 0;
             
            // zero list 값이 있을 경우,
            if(!zero.isEmpty()) {
                bombCheck(zero);
            }
             
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(!visited[i][j] && arr[i][j] == '.') {
                        visited[i][j] = true;
                        clickCount++;
                    }
                }
            }
             
            sb.append("#" + tc + " " + clickCount + "\n");
             
        }
         
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
     
    private static void bombCheck(ArrayList<int []> zero) {
        Queue<int []> queue = new LinkedList<>(); 
        Queue<int []> childQ = new LinkedList<>();
         
        // 0 리스트 queue 넣
        for(int[] z: zero) {
            queue.add(z);
        }
         
        while(!queue.isEmpty()) {
            // 주변 알아볼 0 
            int[] cur;
            if(!childQ.isEmpty()) {
                cur = childQ.poll();
            } else {
                cur = queue.poll();
            }
             
            // 방문했던 지점이면 넘어가고, 아니면 count 1증가 및 방문처리
            if(!visited[cur[0]][cur[1]]) {
                clickCount++;
                visited[cur[0]][cur[1]] = true;
            }
 
            // 0의 8방면 탐
            for(int i=0; i<8; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                 
                if(isValid(nx, ny) && !visited[nx][ny] && arr[nx][ny] == '.') {
                    int cnt = 0;
                     
                    // 그 주위의 8방면을 탐색하며 0이 있는지 확인
                    for(int j=0; j<8; j++) {
                        int nnx = nx + dx[j];
                        int nny = ny + dy[j];
                         
                        if(isValid(nnx, nny) && arr[nnx][nny] == '*') cnt++;
                    }
                     
                    visited[nx][ny] = true;
                     
                    if(cnt == 0) {
                        childQ.add(new int[] {nx, ny});
                    }
                }
            }
        }
         
    }
     
    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}