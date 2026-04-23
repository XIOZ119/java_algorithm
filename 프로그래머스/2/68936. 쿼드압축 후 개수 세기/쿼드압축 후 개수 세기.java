import java.util.*;

class Solution {
    int N = 0;
    int[][] arr;
    int[] answer;
    
    public int[] solution(int[][] arr) {
        N = arr.length;
        this.arr = arr;
        answer = new int[2];
        
        start();
        check(0, N, 0, N);
        
        return answer;
    }
    
    private void check(int sx, int ex, int sy, int ey) {
        if(ex-sx == 1 || ey-sx == 1) return;
        
        int one = 0;
        int zero = 0;
        for(int i=sx; i<ex; i++) {
            for(int j=sy; j<ey; j++) {
                if(arr[i][j] == 1) one++;
                else zero++;
            }
        }
        
        if(one == 0) {
            answer[0] -= (zero - 1);
            return;
        }
        else if(zero == 0) {
            answer[1] -= (one - 1);
            return;
        }
        
        int xD = (ex - sx) / 2; 
        int yD = (ey - sy) / 2; 
        
        check(sx, ex-xD, sy, ey-yD);
        check(sx, ex-xD, sy+yD, ey);
        check(sx + xD, ex, sy, ey-yD);
        check(sx + xD, ex, sy+yD, ey);
    }
    
    private void start() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(arr[i][j] == 1) answer[1]++;
                else answer[0]++;
            }
        }
    }
}