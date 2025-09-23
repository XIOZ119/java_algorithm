class Solution {
    char[][] arr;
    int[] dx = {1, 1, 0};
    int[] dy = {0, 1, 1};
    static int m; static int n;
    
    public int solution(int m, int n, String[] board) {
        this.m = m; this.n = n;
        arr = new char[m][n];
        for(int i=0; i<m; i++) {
            String str = board[i];
            for(int j=0; j<n; j++) {
                arr[i][j] = str.charAt(j);
            }
        }
        
        while(true) {
            boolean[][] visited = new boolean[m][n];
            int cnt = 0;
            
            for(int i=0; i<m; i++) {
                for(int j=0; j<n; j++) {
                    char cur = arr[i][j];
                    visited[i][j] = true;
                    cnt++;
                    
                    for(int k=0; k<3; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        
                        if(!isValid(nx, ny) || arr[nx][ny] != cur || arr[nx][ny] == '0') {
                            visited[i][j] = false;
                            cnt--;
                            break;
                        }
                    }
                }
            }
            if(cnt == 0) break;
            if(cnt > 0) {
                for(int i=0; i<m; i++) {
                    for(int j=0; j<n; j++) {
                        if(!visited[i][j]) continue;
                        
                        arr[i][j] = '0';
                        for(int k=0; k<3; k++){
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            
                            arr[nx][ny] = '0';
                        }
                    }
                }
            }
            
            for(int i=m-1; i>-1; i--){
                for(int j=n-1; j>-1; j--) {
                    if(arr[i][j] != '0') continue;
                    
                    int y = i;
                    while(true) {
                        y--;
                        if(!isValid(y, j)) break;
                        if(arr[y][j] != '0') {
                            arr[i][j] = arr[y][j];
                            arr[y][j] = '0';
                            break;
                        }
                    }
                }
            }
        }
        
        int answer = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(arr[i][j] == '0') answer++;
            }
        }
        
        return answer;
    }
    
    static boolean isValid(int x, int y) {
        return x > -1 && y > -1 && x < m && y < n;
    }
}