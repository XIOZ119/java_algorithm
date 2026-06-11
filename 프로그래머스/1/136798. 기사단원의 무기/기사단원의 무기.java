class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        
        for(int i=1; i<=number; i++) {
            int p = cal(i);
            
            if(p > limit) answer += power;
            else answer += p;
        }
        
        return answer;
    }
    
    public int cal(int number) {
        int cnt = 0;
        for(int i=1; i<=number; i++) {
            if(number % i != 0) continue;

            cnt++;
        }
        
        return cnt;
    }
}