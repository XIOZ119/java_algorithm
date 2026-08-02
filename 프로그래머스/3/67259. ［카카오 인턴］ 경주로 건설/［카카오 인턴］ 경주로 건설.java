import java.util.*;

class Solution {
    static int[][] board; 
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int W, H;
    static int[][][] answer;
    
    public int solution(int[][] board) {
        this.board = board;
        W = board.length; H = board[0].length;
        answer = new int[W][H][4]; 
        
        for(int i=0; i<W; i++) {
            for(int j=0; j<H; j++) {
                Arrays.fill(answer[i][j], Integer.MAX_VALUE);
            }
        }
        
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {0, 0, 1, 0});
        que.add(new int[] {0, 0, 3, 0});
        answer[0][0][1] = 0;
        answer[0][0][3] = 0;
        
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int cx = cur[0]; int cy = cur[1];
            int cd = cur[2]; int cc = cur[3];
            
            for(int i=0; i<4; i++) {
                int nx = cx + dx[i]; 
                int ny = cy + dy[i];
                int nc = cc;
                
                if(!isValid(nx, ny) || board[nx][ny] == 1) continue;
                
                if(cd / 2 == i / 2) nc += 100;
                else nc += 600;
                
                if(answer[nx][ny][i] <= nc) continue; 
                
                answer[nx][ny][i] = nc;
                que.add(new int[] {nx, ny, i, nc});
            }
        }
  
        return Math.min(Math.min(answer[W-1][H-1][0], answer[W-1][H-1][1]), Math.min(answer[W-1][H-1][2], answer[W-1][H-1][3]));
    }
    
    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < W && y < H;
    }
}