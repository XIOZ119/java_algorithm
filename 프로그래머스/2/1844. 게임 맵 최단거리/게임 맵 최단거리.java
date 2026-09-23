import java.util.*;

class Solution {
    static int[][] maps;
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] maps) {
        this.maps = maps;
        N = maps.length;
        M = maps[0].length;
        
        move();
        
        return (answer == Integer.MAX_VALUE) ? -1 : answer;
    }
    
    private static void move() {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        que.add(new int[] {0, 0, 1});
        visited[0][0] = true;
        
        while(!que.isEmpty()){
            int[] cur = que.poll();
            int cx = cur[0]; int cy = cur[1]; int cd = cur[2];
            
            if(cx == N-1 && cy == M-1) {
                answer = Math.min(answer, cd);
                continue;
            }
            
            for(int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(!isValid(nx, ny) || visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                que.add(new int[] {nx, ny, cd+1});
            }
        }
    }
    
    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M && maps[x][y] == 1;
    }
}