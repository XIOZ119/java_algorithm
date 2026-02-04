import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> inBridge = new LinkedList<>();
        int time = 0, cur = 0, idx = 0;
    
        // 다리 길이만큼 0으로 초기화
        for (int i = 0; i < bridge_length; i++) inBridge.add(0);
        
        while(idx < truck_weights.length) {
            time++;
            cur -= inBridge.poll();
            
            // 다리 무게 
            if(cur + truck_weights[idx] <= weight){
                inBridge.add(truck_weights[idx]);
                cur += truck_weights[idx];
                idx++;
            } else {
                inBridge.add(0);
            }

        } 
        
        return time + bridge_length;
    }
}