import java.util.*;

class Solution {
    char[][] arr;
    int[] dx = {-1, 1, 0, 0}; int[] dy = {0, 0, -1, 1};
    int xSize; int ySize;
    
    public int solution(String[] storage, String[] requests) {
        xSize = storage.length; 
        ySize = storage[0].length();
        arr = new char[xSize][ySize];
        
        for(int i=0; i<xSize; i++) {
            String str = storage[i];
            for(int j=0; j<ySize; j++) {
                arr[i][j] = str.charAt(j);
            }
        }
        
        for(String str: requests) {
            if(str.length() == 1) findAtLine(str.charAt(0));
            else findAtAll(str.charAt(0));
        }
        
        int answer = 0;
        for(int i=0; i<xSize; i++){
            for(int j=0; j<ySize; j++) {
                if(arr[i][j] != 'a') answer++;
            }
        }
        
        return answer;
    }
    
    void findAtAll(char c) {
        boolean[][] visited = new boolean[xSize][ySize];
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {0,0});
        visited[0][0] = true;
        if(arr[0][0] == c) arr[0][0] = 'a';
        
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int cx = cur[0]; 
            int cy = cur[1];
            
            for(int i=0; i<4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(!isValid(nx, ny) || visited[nx][ny]) continue;
                if(arr[nx][ny] == c) arr[nx][ny] = 'a';
                
                visited[nx][ny] = true;
                que.add(new int[] {nx, ny});
            }
        }
    }
    
    void findAtLine(char c) {
        char[][] newArr = copyArray();
        boolean[][] visited = new boolean[xSize][ySize];
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {0,0});
        visited[0][0] = true;
        if(arr[0][0] == c) newArr[0][0] = 'a';
        
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int cx = cur[0]; 
            int cy = cur[1];
            
            if(arr[cx][cy] == c) newArr[cx][cy] = 'a';
            
            for(int i=0; i<4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(!isValid(nx, ny) || visited[nx][ny]) continue;
                if(nx == 0 || ny == 0 || nx == xSize-1 || ny == ySize-1 || arr[cx][cy] == 'a') {
                    que.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        
        copyFromNewArray(newArr);
    }
    
    void copyFromNewArray(char[][] newArray) {
        for(int i=0; i<xSize; i++) {
            for(int j=0; j<ySize; j++) {
                arr[i][j] = newArray[i][j];
            }
        }
    }
    
    char[][] copyArray() {
        char[][] newArr = new char[xSize][ySize];
        
        for(int i=0; i<xSize; i++) {
            for(int j=0; j<ySize; j++) {
                newArr[i][j] = arr[i][j];
            }
        }
        
        return newArr;
    }
    
    boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < xSize && y < ySize;
    }
}