class Solution {
    public long solution(int price, int money, int count) {
        long sum = 0;
        
        for(int i=1; i<= count; i++) {
            sum += (long) price * i;
        }

        long answer = -1;
        
        if(sum > money) {
            answer = sum - money;            
        } else {
            answer = 0;
        }

        return answer;
    }
}