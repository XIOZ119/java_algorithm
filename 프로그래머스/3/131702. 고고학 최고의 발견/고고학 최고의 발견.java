import java.util.*;

class Solution {
    private static final int[] dx = {0, 0, 0, 1, -1};
    private static final int[] dy = {0, 1, -1, 0, 0};
    
    public int solution(int[][] clockHands) {
        int length = clockHands.length;
        
        int total = 1; 
        for(int i=0; i<length; i++) {
            total *= 4;
        }
        
        int[][] board = new int[length][length];
        
        int answer = Integer.MAX_VALUE;
        
        // 4진수 분해
        for(int mask=0; mask<total; mask++) {
            
            for(int j=0; j<length; j++) {
                System.arraycopy(clockHands[j], 0, board[j], 0, length);
            }
            
            int cnt = 0;
            
            int cur = mask;
            for(int j=0; j<length; j++) {
                int press = cur % 4;
                cur /= 4;
                if(press != 0) {
                    apply(board, length, 0, j, press);
                    cnt += press;
                }
            }
            
            for (int i = 1; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    int need = (4 - board[i - 1][j]) % 4;
                    if (need != 0) {
                        apply(board, length, i, j, need);
                        cnt += need;
                    }
                }
            }

            boolean ok = true;
            for (int j = 0; j < length; j++) {
                if (board[length - 1][j] != 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) answer = Math.min(answer, cnt);
        }

        return answer;
    }
    
    private void apply(int[][] b, int n, int x, int y, int k) {
        for (int dir = 0; dir < 5; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                b[nx][ny] = (b[nx][ny] + k) & 3; // mod 4 (0~3이라 &3 가능)
            }
        }
    }
}