import java.util.*;


class Solution {
    
    class Count{
        int cur = 0;
        
        int bull = 0;
        int single = 0;
        int doub = 0;
        int trip = 0;
    }
    
    public int[] solution(int target) {
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[] {target, 0, 0});
        
        int[] bestDarts = new int[target+1];
        Arrays.fill(bestDarts, Integer.MAX_VALUE);
        int[] bestSB = new int[target+1];
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            int curDarts = cur[0]; int curCount = cur[1]; int curSB = cur[2];
            
            if(bestDarts[curDarts] < curCount) continue;
            else if(bestDarts[curDarts] == curCount) {
                if(bestSB[curDarts] < curSB) bestSB[curDarts] = curSB;
                else continue;
            } else {
                bestDarts[curDarts] = curCount;
                bestSB[curDarts] = curSB;
            }
            
            if(curDarts == 0) continue;
            
            if(curDarts >= 50) {
                queue.add(new int[] {curDarts-50, curCount+1, curSB+1});
            }
            
            if(curDarts / 2 > 20) {
                queue.add(new int[] {curDarts-40, curCount+1, curSB});
            } else if (curDarts / 2 <= 20 && curDarts / 2 > 0) {
                queue.add(new int[] {curDarts % 2, curCount+1, curSB});
            }
            
            if(curDarts / 3 > 20) {
                queue.add(new int[] {curDarts - 60, curCount+1, curSB});
            } else if (curDarts / 3 <= 20 && curDarts / 3 > 0) {
                queue.add(new int[] {curDarts % 3, curCount+1, curSB});
            }
            
            if(curDarts <= 20){
                queue.add(new int[] {0, curCount+1, curSB+1});
            } else {
                queue.add(new int[] {curDarts-20, curCount+1, curSB+1});
            }
        }

        int[] ans = {bestDarts[0], bestSB[0]};
        return ans;
    }
    
}