import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int studentNum; 
    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1 ~ N 
        N = Integer.parseInt(br.readLine());
        studentNum = N*N; 

        boolean[][] likeNum = new boolean[studentNum+1][studentNum+1];
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<studentNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            queue.add(a);
            for(int j=0; j<4; j++) {
                int b = Integer.parseInt(st.nextToken());

                likeNum[a][b] = true;
            }
        }

        int[][] arr = new int[N+1][N+1];
        int[][] empty = new int[N+1][N+1];
        
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                for(int k=0; k<4; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];
                    
                    if(!isValid(ni, nj)) continue;
                    empty[i][j]++;
                }
            }
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll(); 

            ArrayList<int[]> candi = new ArrayList<>();
            int aroundCnt = 0;

            // 인접한 친구 수 계산 
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    // 현재 위치 i, j
                    if(arr[i][j] != 0) continue;
                    
                    int cnt = 0;
                    for(int k=0; k<4; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];

                        if(isValid(ni, nj) && arr[ni][nj] != 0) {
                            int student = arr[ni][nj];

                            if(likeNum[cur][student]) cnt++;
                        }
                    }

                    if(cnt == aroundCnt) {
                        candi.add(new int[] {i, j});
                    } else if(cnt > aroundCnt) {
                        candi = new ArrayList<>();
                        candi.add(new int[] {i, j});
                        aroundCnt = cnt;
                    }
                }
            }

            Collections.sort(candi, (a, b) -> {
                // 비어있는 칸의 수가 더 많은 곳
                if(empty[a[0]][a[1]] == empty[b[0]][b[1]]) {
                    // 행 번호 작은 곳
                    if(a[0] == b[0]) {
                        // 열 번호 작은 곳
                        return a[1] - b[1];
                    }
                    return a[0] - b[0];
                }
                return empty[b[0]][b[1]] - empty[a[0]][a[1]];
            });

            int[] result = candi.get(0); 

            arr[result[0]][result[1]] = cur;
            empty[result[0]][result[1]] = 0;
            
            for(int i=0; i<4; i++) {
                int nx = result[0] + dx[i];
                int ny = result[1] + dy[i];

                if(!isValid(nx, ny) || arr[nx][ny] != 0) continue;
                empty[nx][ny]--;
            }
        }

        int answer = 0;
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                int student = arr[i][j];

                int cnt = 0;
                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(!isValid(nx, ny)) continue;

                    int other = arr[nx][ny];
                    if(likeNum[student][other]) cnt++;
                }

                if(cnt == 1) answer += 1;
                else if(cnt == 2) answer += 10;
                else if(cnt == 3) answer += 100;
                else if(cnt == 4) answer += 1000;
            }
        }

        System.out.println(answer);
    }

    static boolean isValid(int x, int y) {
        return x > 0 && y > 0 && x <= N && y <= N;
    }
}