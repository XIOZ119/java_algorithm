class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int range = 2*w + 1;
        for(int i=0; i<stations.length; i++) {
            int left = (i == 0) ? 1 : stations[i-1] + w + 1;
            int right = stations[i] - w - 1;
            
            if(left <= right) {
                int count = right - left + 1;
                int q = count / range;
                int r = count % range;
                if(r != 0) q += 1;

                answer += q;
            }
                
            if(i == stations.length - 1 && stations[i] + w + 1 <= n) {
                left = stations[i] + w + 1;
                right = n;
                
                int count = right - left + 1;
                int q = count / range;
                int r = count % range;
                if(r != 0) q += 1;

                answer += q;
            }
        }

        return answer;
    }
}