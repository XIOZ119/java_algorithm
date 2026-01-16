class Solution {
    public int solution(int n, int[] cores) {
        if(n <= cores.length) return n;
        
        int left = 0;
        int right = 10000 * (n / cores.length + 1);
        int mid = (left+right)/2;
        
        // 이분 탐색 -> 시간 확인 
        while(left < right) {
            int num = cores.length;
            for(int i=0; i<cores.length; i++) {
                int core = cores[i];
                
                num += mid / core;
            }
            
            if(num >= n) {
                right = mid;
                mid = (left + right) / 2;
            } else {
                left = mid + 1;
                mid = (left + right) / 2;
            }
            
        }
        
        // mid-1까지 완료된 작업 수를 먼저 제외하고, mid 시간에 딱 끝나는 코어들을 앞에서부터 확인 
        for(int i=0; i<cores.length; i++) {
            int core = cores[i];

            n -= (mid-1) / core;
        }
        n -= cores.length;
        
        int answer = 0;
        for(int i=0; i<cores.length; i++){
            int core = cores[i];
            
            if(mid % core == 0) n--; 
            if(n == 0) {
                answer = i+1;
                break;
            }
        }
        
        return answer;
        
    }
}