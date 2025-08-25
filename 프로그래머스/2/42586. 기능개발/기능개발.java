import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int length = progresses.length;
        int day = 0;
        int cnt = 0;
        List<Integer> count = new ArrayList<>();
        
        for(int i=0; i<length; i++) {
            int cur = progresses[i] + speeds[i] * day;
            
            if(cur < 100) {
                // 지금까지 배포된 거 저장 & 초기화
                if(i != 0) {
                    count.add(cnt);
                    cnt = 0;
                }
                
                int left = (100 - cur) % speeds[i] == 0 ? (100 - cur) / speeds[i] : (100 - cur) / speeds[i] + 1;
                day += left;
                cnt ++;
            } else {
                cnt++;
            }
        }
        count.add(cnt);
        
        int[] answer = new int[count.size()];
        
        for(int i=0; i<count.size(); i++){
            answer[i] = count.get(i);
        }
        
        return answer;
    }
}