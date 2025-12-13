import java.util.*;

class Solution {
    boolean[][] visited;
    int height = 0; int width = 0;
    int[] dx = {-1, 1, 0, 0}; int[] dy = {0, 0, -1, 1};
    String[] maps;
    
    public int[] solution(String[] maps) {
        this.maps = maps;
        height = maps.length;
        width = maps[0].length();
        
        visited = new boolean[height][width];
        
        ArrayList<Integer> arr = new ArrayList<>();
        
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                String str = maps[i];
                
                if(str.charAt(j) == 'X' || visited[i][j]) continue;
                
                arr.add(findIsland(i, j));
            }
        }
        
        if(arr.isEmpty()) return new int[] {-1};
        
        Collections.sort(arr);
        
        int[] answer = new int[arr.size()];
        for(int i=0; i<arr.size(); i++){
            answer[i] = arr.get(i);
        }
        
        return answer;
    }
    
    private int findIsland(int x, int y) {
        Queue<int []> queue = new LinkedList<>();
        
        visited[x][y] = true;
        
        int days = Integer.parseInt(maps[x].charAt(y) + "");
        queue.add(new int[] {x, y, days});
        
        int result = 0;
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            int cx = cur[0];
            int cy = cur[1];
            int cd = cur[2];
            
            result += cd;
            
            for(int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(!isValid(nx, ny)) continue;
                if(maps[nx].charAt(ny) == 'X' || visited[nx][ny]) continue;
                
                int nd = Integer.parseInt(maps[nx].charAt(ny) + "");
                
                visited[nx][ny] = true;
                queue.add(new int[] {nx, ny, nd});
            }
        }
        
        return result;
    }
    
    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < height && y < width;
    }
}