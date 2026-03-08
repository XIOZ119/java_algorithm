// m: 서버 증설에 필요한 이용자 수
// k: 서버 유지 시간

import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int count = 0;
        int time = 0;
        Queue<Integer> que = new LinkedList<>();
        
        for(int i=0; i<players.length; i++) {
            time++;
            while(!que.isEmpty()) {
                int t = que.peek();
                if(t <= time) {
                    que.poll();
                }
                else break;
            }
            
            int need = players[i] / m;
            int curServer = que.size();
            
            if(need <= curServer) {
                continue;
            } else {
                int mustAdd = need - curServer;
                for(int j=0; j<mustAdd; j++) {
                    que.add(time + k);
                    count++;
                }
            }
            
        }
        
        return count;
    }
}
