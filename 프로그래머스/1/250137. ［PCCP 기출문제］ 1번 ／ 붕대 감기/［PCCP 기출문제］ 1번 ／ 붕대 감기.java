class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        int l = attacks.length;
        
        int b = bandage[0]; // 연속 붕대 기간
        int basicRecover = bandage[1]; // 체력 회복
        int addRecover = bandage[2]; // 추가 회복
        
        int ai = 0;
        int success = 0;
        
        for(int i=1; i<=attacks[l-1][0]; i++){
            
            if(attacks[ai][0] == i) {
                answer -= attacks[ai][1];
                success = 0;
                ai++;
            } else {
                success++;
                answer += basicRecover;
            }
            
            if(success == b){
                answer += addRecover;
                success = 0;
            }
            
            if(answer > health) {
                answer = health;
            }
            if(answer <= 0) {
                return -1;
            }
        }
        
        return answer;
    }
}