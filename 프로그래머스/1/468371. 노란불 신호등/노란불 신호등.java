class Solution {
    /*
        1. 모든 신호등 주기의 LCM(최소공배수) 계산
        2. 시간 t를 1부터 LCM까지 확인
        3. 각 신호등이 t초에 노란불인지 검사
        4. 전부 노란불이면 정답
    */
    public int solution(int[][] signals) {
        int answer = 0;
        
        int totalCycle = 1;
        
        int[] cycles = new int[signals.length];
        int cnt = 0;
        for (int[] signal : signals) {
            int cycle = signal[0] + signal[1] + signal[2];
            cycles[cnt++] = cycle;
            totalCycle = lcm(totalCycle, cycle);
        }
        
        for(int i=1; i<=totalCycle; i++) {
            
            boolean flag = true;
            for(int j=0; j<signals.length; j++) {
                int cycle = cycles[j];
                int red = signals[j][0];
                int yellow = signals[j][1];
                
                if((i % cycle - red) <= 0 || (i % cycle - red - yellow) > 0) {
                    flag = false;
                    break;
                }
            }
            
            if(flag) {
                return i;
            }
        }
        
        return -1;
    }
    
    static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    
    static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}