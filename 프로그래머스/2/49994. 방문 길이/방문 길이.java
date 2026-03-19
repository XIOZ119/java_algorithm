class Solution {
    static int[] dx = {-1, 1, 0, 0}; // U, D, R, L
    static int[] dy = {0, 0, 1, -1};
    public int solution(String dirs) {
        boolean[][][] visited = new boolean[11][11][4];
        
        int x = 5; int y = 5;
        
        int answer = 0;
        for(int i=0; i<dirs.length(); i++) {
            char d = dirs.charAt(i);
            int dir = move(d);
            
            if(!isValid(x + dx[dir], y + dy[dir])) continue;
            
            if(!visited[x][y][dir]) {
                answer++;
                visited[x][y][dir] = true;
            }
            
            x += dx[dir]; y += dy[dir];
            visited[x][y][reverse(d)] = true;
        }
        
        return answer;
    }
    
    static int move(char c) {
        if(c == 'U') return 0;
        else if(c == 'D') return 1;
        else if(c == 'R') return 2;
        else return 3;
    }
    
    static int reverse(char c) {
        if(c == 'U') return 1;
        else if(c == 'D') return 0;
        else if(c == 'R') return 3;
        else return 2;
    } 
    
    static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x <= 10 && y <= 10;
    }
}