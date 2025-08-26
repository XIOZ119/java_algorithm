import java.util.*;

class Solution {
    Map<String, Integer> check;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] points, int[][] routes) {
        check = new HashMap<>();
        int robot = routes.length;
        
        for(int i=0; i<robot; i++){
            int[] route = routes[i];
            int time = 0;
            
            for(int j=0; j<route.length-1; j++){
                int startNode = route[j] - 1;
                int endNode = route[j+1] - 1;
                time = findRoute(points[startNode], points[endNode], time);
            }
        }
        
        int answer = 0;
        
        for(int val: check.values()){
            if(val > 1) answer++;
        }
        
        return answer;
    }
    
    int findRoute(int[] start, int[] end, int time){
        int cx = start[0], cy = start[1];
        
        if(time == 0) {
            String key = cx + "," + cy + "," + time;
            check.put(key, check.getOrDefault(key, 0) + 1);
        }
        
        while(cx != end[0]){
            cx += (end[0] > cx ? 1 : -1);
            time++;
            String key = cx + "," + cy + "," + time;
            check.put(key, check.getOrDefault(key, 0) + 1);
        }
        
        while(cy != end[1]){
            cy += (end[1] > cy ? 1 : -1);
            time++;
            String key = cx + "," + cy + "," + time;
            check.put(key, check.getOrDefault(key, 0) + 1);
        }
        
        return time;
    }
}