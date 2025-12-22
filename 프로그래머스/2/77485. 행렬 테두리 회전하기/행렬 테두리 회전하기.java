import java.util.*;

class Solution {
    static int[][] arr; 
    static int[] answer;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        answer = new int[queries.length];
        
        init(rows, columns);
        
        for(int i=0; i<queries.length; i++){
            answer[i] = roll(queries[i][0], queries[i][1], queries[i][2], queries[i][3]);
        }
        
        return answer;
    }
    
    private int roll(int x1, int y1, int x2, int y2) {
        int min = arr[x1][y1];
        int past = arr[x1][y1];
        
        for(int i=y1+1; i<=y2; i++){
            int cur = arr[x1][i];
            arr[x1][i] = past;
            min = Math.min(min, cur);
            past = cur;
        }
        
        for(int i=x1+1; i<=x2; i++){
            int cur = arr[i][y2];
            arr[i][y2] = past;
            min = Math.min(min, cur);
            past = cur;
        }
        
        for(int i=y2-1; i>=y1; i--){
            int cur = arr[x2][i];
            arr[x2][i] = past;
            min = Math.min(min, cur);
            past = cur;
        }
        
        for(int i=x2-1; i>=x1; i--){
            int cur = arr[i][y1];
            arr[i][y1] = past;
            min = Math.min(min, cur);
            past = cur;
        }
        
        return min;
    }
    
    private void init(int rows, int columns) {
        arr = new int[rows+1][columns+1];
        
        int num = 0;
        
        for(int i=0; i<rows+1; i++){
            for(int j=0; j<columns+1; j++){
                if( i==0 || j==0 ) arr[i][j] = 0;
                else arr[i][j] = ++num;
            }
        }
    }
}
