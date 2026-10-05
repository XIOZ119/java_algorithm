import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0}; int[] dy = {0, 0, -1, 1}; // 상, 하, 왼, 오
    int N = 0; int M = 0;
    int[][] board; 
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] board) {
        this.N = board.length;
        this.M = board[0].length; 
        this.board = board; 
        
        dfs(0, 0);
        
        return answer;
    }
    
    public class Car{
        int x;
        int y;
        int dir; 
        int cost;
        
        public Car(int x, int y, int dir, int cost) {
            this.x = x; 
            this.y = y; 
            this.dir = dir; 
            this.cost = cost; 
        }
    }
    
    public void dfs(int x, int y) {
        Queue<Car> que = new LinkedList<>();
        int[][][] visited = new int[N][M][4];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        
        Car car = new Car(x, y, 1, 0);
        visited[x][y][1] = 0; 
        Car car2 = new Car(x, y, 3, 0);
        visited[x][y][3] = 0;
        
        que.add(car);
        que.add(car2); 
        
        while(!que.isEmpty()){
            Car cur = que.poll();
            
            if(cur.x == N-1 && cur.y == M-1) {
                answer = Math.min(cur.cost, answer); 
                continue; 
            }
            
            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nd = (cur.dir == i) ? cur.cost + 100 : cur.cost + 600;
                
                if(!isValid(nx, ny) || visited[nx][ny][i] <= nd) continue;
                
                que.add(new Car(nx, ny, i, nd));
                visited[nx][ny][i] = nd;
            }
        }
    }
    
    public boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M && board[x][y] == 0;
    }
}