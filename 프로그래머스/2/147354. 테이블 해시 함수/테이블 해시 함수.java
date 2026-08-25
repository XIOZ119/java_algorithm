import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        int row = data.length;
        ArrayList<int[]> list = new ArrayList<>();
        
        for(int i=0; i<row; i++) {
            list.add(data[i]);
        }
        
        int co = col - 1;
        Collections.sort(list, (a, b) -> {
            if(a[co] == b[co]) {
                return b[0] - a[0];
            }
            
            return a[co] - b[co];
        });
        
        for(int i=row_begin; i<=row_end; i++) {
            int index = i-1;
            
            int[] cur = list.get(index);
            int sum = 0;
            for(int c: cur) {
                sum += c % i;        
            }
            
            answer ^= sum;
        }
        

        
        return answer;
    }
}