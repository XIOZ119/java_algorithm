import java.util.*;

class Solution {
    static char[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int pointX, pointY, startX, startY;
    
    public int solution(String[] board) {
        int height = board.length;
        int width = board[0].length();
        
        arr = new char[height][width];
        
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++){
                arr[i][j] = board[i].charAt(j);
                
                if(board[i].charAt(j) == 'R') {
                    startX = i;
                    startY = j;
                }
                
                if(board[i].charAt(j) == 'G') {
                    pointX = i;
                    pointY = j;
                }
            }
        }
        
        int answer = go(startX, startY);
        return answer;
    }
    
    static int go(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y ,0});
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            int cx = cur[0];
            int cy = cur[1];
            int cd = cur[2];
            System.out.println(cx + " " + cy + " " + cd);
            if(cx == pointX && cy == pointY) return cd;
            
            visited[cx][cy] = true;
            for(int i=0; i<4; i++){
                int[] next = slide(cx, cy, i);
                
                if(visited[next[0]][next[1]])continue;
                queue.add(new int[] {next[0], next[1], cd+1});
            }
        }
        
        return -1;
    }
    
    public static int[] slide(int x, int y, int dir){
        int nx = x;
        int ny = y;
        
        while(true){
            nx += dx[dir];
            ny += dy[dir];
            
            if(!isValid(nx, ny)) break;
            if(arr[nx][ny] == 'D') break;
        }
        
        return new int[] {nx-dx[dir], ny-dy[dir]};
    }
    
    public static boolean isValid(int x, int y){
        return x >= 0 && y >= 0 && x < arr.length && y < arr[0].length;
    }
}