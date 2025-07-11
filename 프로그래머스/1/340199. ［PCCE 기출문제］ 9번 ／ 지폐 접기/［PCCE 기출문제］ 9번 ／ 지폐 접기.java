class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        int ww = wallet[0];
        int wh = wallet[1];
        int bw = bill[0];
        int bh = bill[1];
        
        while(true) {
            
            if(bw <= ww && bh <= wh) break;
            if(bw <= wh && bh <= ww) break;
            
            if(bw <= bh) {
                bh = bh / 2;
            } else {
                bw = bw / 2;
            }
            
            answer++;
        }
        
        return answer;
    }
}