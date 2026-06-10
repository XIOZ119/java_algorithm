class Solution {
    public int solution(int k, int m, int[] score) {
        int[] cnt = new int[k+1];
        
        for(int i=0; i<score.length; i++) {
            int s = score[i];
            
            cnt[s]++;
        }
        
        int box = score.length / m;

        int answer = 0;
        while(box > 0) {
            int max = m;
            box--;
            
            for(int i=k; i>0; i--) {
                if(cnt[i] == 0) continue;
                if(max > cnt[i]) {
                    max -= cnt[i];
                    cnt[i] = 0;
                } else if (max == cnt[i]) {
                    cnt[i] = 0;
                    answer += (i * m);
                    break;
                } else {
                    cnt[i] -= max;
                    answer += (i * m);
                    break;
                }
            }
        }
        
        return answer;
    }
}