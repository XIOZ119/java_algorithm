import java.util.*;

class Solution {
    public int[] solution(int n) {
        int[][] arr = new int[n+1][n+1];
        
        int[] dx = {1, 0, -1};
        int[] dy = {0, 1, -1};
        
        int x = 0;
        int y = 1;
        
        int num = 1;
        int direction = 0;
        int count = 1;
        
        for(int i=n; i>0; i--) {
            if(direction == 3) direction = 0;
            
            for (int k = 0; k < i; k++) {
                x += dx[direction];
                y += dy[direction];
                arr[x][y] = num++;
            }
            direction = (direction + 1) % 3;
        }
        
        List<Integer> answers = new ArrayList<>();
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=i; j++) {
                answers.add(arr[i][j]);
            }
        }
        
        int[] answer = new int[answers.size()];
        for(int i=0; i<answers.size(); i++) {
            answer[i] = answers.get(i);
        }
        
        return answer;
    }
}