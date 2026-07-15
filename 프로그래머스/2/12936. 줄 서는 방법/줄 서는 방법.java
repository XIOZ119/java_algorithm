import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int num = n-1;
        long sum = 1;
        for(int i=1; i<n; i++) {
            sum *= (long) i;
        }
        
        boolean[] visited = new boolean[n+1];
        ArrayList<Integer> list = new ArrayList<>();
        
        k--;
        
        for (int pos=0; pos<n; pos++) {
            int div = (int) (k / sum);
            k = k % sum;
            
            int cnt = 0;
            for(int i=1; i<=n; i++) {
                if (!visited[i]) {
                    if (cnt == div) {
                        visited[i] = true;
                        list.add(i);
                        break;
                    }
                    cnt++;
                }
            }
            
            if (num > 0) {
                sum /= num;
                num--;
            }
        }
        
        int[] answer = new int[n];
        for(int i=0; i<n; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}