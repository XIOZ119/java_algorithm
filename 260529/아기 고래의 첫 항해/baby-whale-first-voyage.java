import java.util.*;
import java.io.*;

public class Main {
    static int N, r, c, d;
    static int[][] arr;
    static boolean[][] visited;
    // 1 상, 2 하, 3 좌, 4 우 
    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1};
    static StringBuilder sb;

    static int[][] priority = {
        {0, 2, 3, 1}, // 위
        {1, 3, 2, 0}, // 아래
        {2, 1, 0, 3}, // 왼쪽
        {3, 0, 1, 2}  // 오른쪽
    };

    public static class Loc {
        int x;
        int y; 
        int d;
        int cnt;

        Loc(int x, int y, int d, int cnt) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cnt = cnt;
        } 
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        d = Integer.parseInt(st.nextToken()) - 1;

        arr = new int[N][N];
        visited = new boolean[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Loc loc = new Loc(r, c, d, 0);
        visited[r][c] = true;
        sb.append((r+1) + " " + (c+1) + "\n");

        while(true) {
            // 1단계: 인접 탐험
            boolean moved = explore(loc);

            if(!moved) {
                // 2단계: 가장 가까운 바다로 이동
                Loc next = findNearestSea(loc);

                if(next == null) {
                    break;
                }

                loc.x = next.x;
                loc.y = next.y;
                loc.d = next.d;
                visited[loc.x][loc.y] = true;
                sb.append((loc.x + 1) + " " + (loc.y + 1) + "\n");
            }
        }

        System.out.println(sb.toString());
    }

    static Loc findNearestSea(Loc loc) {
        Queue<Loc> que = new LinkedList<>();
        boolean[][] bfsVisited = new boolean[N][N];

        que.add(new Loc(loc.x, loc.y, loc.d, 0));
        bfsVisited[loc.x][loc.y] = true;

        ArrayList<Loc> candidates = new ArrayList<>();

        while(!que.isEmpty()) {
            Loc now = que.poll();
            
            if(!visited[now.x][now.y]) candidates.add(now);

            int[] bfsPriority = {2, 1, 3, 0}; // 좌, 하, 우, 상

            for(int i = 0; i < 4; i++) {
                int dir = bfsPriority[i];

                int nx = now.x + dx[dir];
                int ny = now.y + dy[dir];

                if(!isValid(nx, ny)) continue;
                if(bfsVisited[nx][ny]) continue;

                bfsVisited[nx][ny] = true;
                que.add(new Loc(nx, ny, dir, now.cnt+1));
            }
        }

        if(candidates.isEmpty()) return null;

        candidates.sort((a, b) -> {
            if(a.cnt != b.cnt) return a.cnt - b.cnt;
            if(a.x != b.x) return a.x - b.x;
            return a.y - b.y;
        });

        return candidates.get(0);
    }

    static boolean explore(Loc loc) {
        for(int i=0; i<4; i++) {
            int nd = priority[loc.d][i];

            int nx = loc.x + dx[nd];
            int ny = loc.y + dy[nd];

            if(!isValid(nx, ny)) continue;
            if(visited[nx][ny]) continue;

            visited[nx][ny] = true;
            loc.x = nx;
            loc.y = ny; 
            loc.d = nd;

            sb.append((nx+1) + " " + (ny+1) + "\n");

            return true;
        }

        return false;
    }

    static boolean isValid(int x, int y) {
        return x > -1 && y > -1 && x < N && y < N && arr[x][y] == 0;
    }
}