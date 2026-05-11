import java.util.*;

public class Main {

    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1};
    static int n;

    static class Info{
        int x;
        int y;
        int max;
        int distance;

        Info(int x, int y, int max, int distance) {
            this.x = x;
            this.y = y;
            this.max = max;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = scanner.nextInt();
        
        int[][][] dp = new int[n][n][k+1];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                Queue<Info> queue = new LinkedList<>();
                
                Info in = new Info(i, j, 0, 1);
                queue.add(in);

                while(!queue.isEmpty()) {
                    Info cur = queue.poll();
                    int c = grid[cur.x][cur.y];

                    if(cur.distance == k) {
                        answer = Math.min(answer, cur.max);
                        continue;
                    }

                    for(int d=0; d<4; d++) {
                        int nx = cur.x + dx[d];
                        int ny = cur.y + dy[d];
                        
                        if(!isValid(nx, ny) || grid[nx][ny] <= c) continue;

                        int m = grid[nx][ny] - c;
                        if(Math.max(m, cur.max) >= answer) continue;
                        if(dp[nx][ny][cur.distance+1] <= Math.max(m, cur.max)) continue;

                        Info next = new Info(nx, ny, Math.max(m, cur.max), cur.distance+1);
                        queue.add(next);
                        dp[nx][ny][next.distance] = next.max;
                    }
                }
            }
        }

        if(answer == Integer.MAX_VALUE) answer = -1;

        System.out.println(answer);
    }

    static boolean isValid(int x, int y) {
        return x > -1 && y > -1 && x < n && y < n;
    }
}