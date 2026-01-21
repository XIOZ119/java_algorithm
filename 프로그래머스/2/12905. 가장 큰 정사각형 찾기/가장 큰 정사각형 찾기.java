import java.util.*;

class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        int R = board.length;
        int C = board[0].length;
        int[][] count = new int[R][C];
        
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(board[i][j] == 0) continue;

                if (i == 0 || j == 0 ) {
                    count[i][j] = 1;
                } else {
                    count[i][j] = Math.min(count[i-1][j-1], count[i-1][j]);
                    count[i][j] = Math.min(count[i][j], count[i][j-1]);
                    count[i][j]++;
                }
                
                answer = Math.max(answer, count[i][j]);
            }
        }
        
        return answer*answer;
    }
}