import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1};
    static int h = 0;
    static int w = 0;
    static char[][] arr;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(String[] board) {
        this.h = board.length; 
        this.w = board[0].length(); 
        this.arr = new char[h][w];
        
        int startX = 0;
        int startY = 0;
        
        for(int i=0; i<h; i++) {
            String str = board[i];
            for(int j=0; j<w; j++) {
                arr[i][j] = str.charAt(j);
                
                if(arr[i][j] == 'R') {
                    startX = i; 
                    startY = j;
                }
            }
        }
        
        bfs(startX, startY);
        
        return (answer == Integer.MAX_VALUE) ? -1 : answer;
    }
    
    public static void bfs(int x, int y) {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[h][w];
        
        que.add(new int[] {x, y, 0});
        visited[x][y] = true;
        
        while(!que.isEmpty()) {
            int[] cur = que.poll(); 
            int cx = cur[0]; 
            int cy = cur[1]; 
            int cd = cur[2];
            
            if(arr[cx][cy] == 'G') {
                answer = Math.min(answer, cd); 
                continue;
            }
            
            for(int i=0; i<4; i++) {
                int nx = cx;
                int ny = cy;
                                    
                while(true) {
                    nx += dx[i];
                    ny += dy[i];
                    
                    if(!isValid(nx, ny)) break;
                }
                nx -= dx[i];
                ny -= dy[i];
                
                if(nx == cx && ny == cy) continue;
                if(visited[nx][ny]) continue;
                
                que.add(new int[] {nx, ny, cd + 1});
                visited[nx][ny] = true;
            }
        }
        
        
    }
    
    public static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < h && y < w && arr[x][y] != 'D';
    }
}