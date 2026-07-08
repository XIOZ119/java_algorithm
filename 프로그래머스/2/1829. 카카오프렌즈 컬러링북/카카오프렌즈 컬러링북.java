import java.util.*;

class Solution {
    private static boolean[][] visited;
    private static int[][] picture;
    private static int m, n;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        visited = new boolean[m][n];
        this.picture = picture;
        this.m = m;
        this.n = n; 
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(visited[i][j] || picture[i][j] == 0) continue;
                
                int cnt = bfs(i, j, picture[i][j]);
                numberOfArea++;
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        
        return answer;
    }
    
    private static int bfs(int x, int y, int num) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {x, y});
        
        int cnt = 0;
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int cx = cur[0]; int cy = cur[1];
            
            for(int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(!isValid(nx, ny) || picture[nx][ny] != num || visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                que.add(new int[] {nx, ny});
                cnt++;
            }
        }
        
        return cnt;
    }
    
    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }
}