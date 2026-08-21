import java.util.*;

class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(arr[i], Integer.MAX_VALUE);
        }
        
        for(int i=0; i<drops.length; i++) {
            int x = drops[i][0];
            int y = drops[i][1];
            
            arr[x][y] = i + 1;
        }
        
        int[][] rowArr = new int[m][n-w+1];
        for(int i=0; i<m; i++) {
            Deque<Integer> dq = new ArrayDeque<>();
            
            for(int j=0; j<n; j++) {
                while(!dq.isEmpty() && arr[i][dq.peekLast()] >= arr[i][j]) {
                    dq.pollLast();
                }
                
                dq.addLast(j);
                
                if(dq.peekFirst() <= j - w) {
                    dq.pollFirst(); 
                }
                
                if(j >= w-1) {
                    rowArr[i][j - w + 1] = arr[i][dq.peekFirst()];
                }
            }
        }
        
        int[][] colArr = new int[m-h+1][n-w+1];
        for(int i=0; i<n-w+1; i++) {
            Deque<Integer> dq = new ArrayDeque<>();
            
            for(int j=0; j<m; j++) {
                while(!dq.isEmpty() && rowArr[dq.peekLast()][i] >= rowArr[j][i]) {
                    dq.pollLast();
                }
                
                dq.addLast(j);
                
                if(dq.peekFirst() <= j - h) {
                    dq.pollFirst(); 
                }
                
                if(j >= h-1) {
                    colArr[j - h + 1][i] = rowArr[dq.peekFirst()][i];
                }
            }
        }
        
        int[] answer = new int[2]; 
        int max = Integer.MIN_VALUE;
        for(int i=0; i<colArr.length; i++) {
            for(int j=0; j<colArr[i].length; j++) {
                int num = colArr[i][j];
                if(num < max) continue; 
                
                if(num > max) {
                    max = num;
                    answer[0] = i; answer[1] = j;
                } else {
                    if(answer[0] < i) continue; 
                    if(answer[0] == i && answer[1] < j) continue;
                    
                    max = num;
                    answer[0] = i; answer[1] = j;
                }
            }
        }
        
        return answer;
    }
}