import java.util.*;

class Solution {
    static final int MAX = 105;
    static int[][] board = new int[MAX][MAX];
    static boolean[][] visited = new boolean[MAX][MAX];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = Integer.MAX_VALUE;
        
        // 사각형 전체 1로 채우기
        for(int[] r: rectangle) {
            int x1 = r[0] * 2, y1 = r[1] * 2, x2 = r[2] * 2, y2 = r[3] * 2;
            for(int x = x1; x <= x2; x++) {
                for(int y = y1; y <= y2; y++) {
                    board[x][y] = 1;
                }
            }
        }
        
        // 테두리만 남기고 0으로 지우기 
        for(int[] r: rectangle) {
            int x1 = r[0] * 2, y1 = r[1] * 2, x2 = r[2] * 2, y2 = r[3] * 2;
            for(int x = x1 + 1; x <= x2 - 1; x++) {
                for(int y = y1 + 1; y <= y2 - 1; y++) {
                    board[x][y] = 0;
                }
            }
        }
        
        // BFS 최단거리 
        int sx = characterX * 2, sy = characterY * 2;
        int tx = itemX * 2, ty = itemY * 2;
        
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {sx, sy, 0}); 
        visited[sx][sy] = true;
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
            int cd = cur[2];
            
            if(cx == tx && cy == ty) {
                answer = Math.min(answer, cd);
            }
            
            for(int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i]; 
                
                if(visited[nx][ny]) continue;
                if(board[nx][ny] != 1) continue;
                
                visited[nx][ny] = true;
                queue.add(new int[] {nx, ny, cd + 1});
            }
        }
        
        return answer/2;
    }
}