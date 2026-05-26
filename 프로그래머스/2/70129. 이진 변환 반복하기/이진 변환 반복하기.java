class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        
        int zeroCnt = 0;
        int forCnt = 0;
        while(s.length() > 1) {
            forCnt++;

            int cnt = 0;
            for(int i=0; i<s.length(); i++) {
                if(s.charAt(i) == '0') cnt++;
            }
            zeroCnt += cnt;
            
            int oneCnt = s.length() - cnt;
            
            s = Integer.toBinaryString(oneCnt);
        }
        
        return new int[] {forCnt, zeroCnt};
    }
}