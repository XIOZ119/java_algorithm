import java.util.*;

class Solution {
    int xSize;
    int ySize;
    int[][] maze;
    int answer = Integer.MAX_VALUE;
    int[] dx = {-1, 1, 0, 0}; int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] maze) {
        xSize = maze.length;
        ySize = maze[0].length;
        this.maze = maze;
        
        int redStartX = -1; int redStartY = -1;
        int blueStartX = -1; int blueStartY = -1;
        
        for(int i=0; i<xSize; i++) {
            for(int j=0; j<ySize; j++){
                if(maze[i][j] == 1) {
                    redStartX = i;
                    redStartY = j;
                }
                else if(maze[i][j] == 2) {
                    blueStartX = i;
                    blueStartY = j;
                }
            }
        }
        
        boolean[][] redVisited = new boolean[xSize][ySize];
        redVisited[redStartX][redStartY] = true;
        boolean[][] blueVisited = new boolean[xSize][ySize];
        blueVisited[blueStartX][blueStartY] = true;
        
        dfs(redStartX, redStartY, blueStartX, blueStartY, redVisited, blueVisited, 0);
        
        if(answer == Integer.MAX_VALUE) answer = 0;
        return answer;
    }
    
    void dfs(int redX, int redY, int blueX, int blueY, boolean[][] redVisited, boolean[][] blueVisited, int count) {
                
        if(maze[redX][redY] == 3 && maze[blueX][blueY] == 4) {
            answer = Math.min(answer, count);
            return;
        }
        
        if(maze[redX][redY] == 3) {
            for(int i=0; i<4; i++) {
                int nx = blueX + dx[i];
                int ny = blueY + dy[i];
                
                if(!isValid(nx, ny) || (nx == redX && ny == redY) || blueVisited[nx][ny]) continue;
                blueVisited[nx][ny] = true;
                dfs(redX, redY, nx, ny, redVisited, blueVisited, count+1);
                blueVisited[nx][ny] = false;
            }
        } else if(maze[blueX][blueY] == 4) {
            for(int i=0; i<4; i++) {
                int nx = redX + dx[i];
                int ny = redY + dy[i];
                
                if(!isValid(nx, ny) || (nx == blueX && ny == blueY)  || redVisited[nx][ny]) continue;
                
                redVisited[nx][ny] = true;
                dfs(nx, ny, blueX, blueY, redVisited, blueVisited, count+1);
                redVisited[nx][ny] = false;
            }
        } else {
            for(int i=0; i<4; i++) {
                int nx = redX + dx[i];
                int ny = redY + dy[i];
                
                if(!isValid(nx, ny) || redVisited[nx][ny]) continue;
                redVisited[nx][ny] = true;
                
                for(int j=0; j<4; j++) {
                    int bnx = blueX + dx[j];
                    int bny = blueY + dy[j];
                
                    if(!isValid(bnx, bny) || (nx == bnx && ny == bny) || blueVisited[bnx][bny]) continue;
                    if((nx == blueX && ny == blueY) && (bnx == redX && bny == redY)) continue;
                    blueVisited[bnx][bny] = true;
                    dfs(nx, ny, bnx, bny, redVisited, blueVisited, count+1);
                    blueVisited[bnx][bny] = false;
                }
                redVisited[nx][ny] = false;
            }
        }
    }
    
    boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < xSize && y < ySize && maze[x][y] != 5;
    }
}