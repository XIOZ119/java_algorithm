import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] clothes = new int[n+1];
        Arrays.fill(clothes, 1);
        
        for(int i=0; i<lost.length; i++) {
            int number = lost[i];
            clothes[number]--;
        }
        
        for(int i=0; i<reserve.length; i++){
            int number = reserve[i];
            clothes[number]++;
        }
        
        for(int i=1; i<n+1; i++) {
            if(clothes[i] == 1) continue;
            if(clothes[i] == 0) {
                if(clothes[i-1] > 1) {
                    clothes[i-1]--;
                    clothes[i]++;
                }
                else if(i != n && clothes[i+1] > 1) {
                    clothes[i+1]--;
                    clothes[i]++;
                }
            }
        }
        
        int answer = 0;
        for(int i=1; i<n+1; i++) {
            if(clothes[i] > 0) {
                answer++;                
            }
        }
        
        return answer;
    }
}

// 1 2 3 4 5 
// 1 0 2 0 1