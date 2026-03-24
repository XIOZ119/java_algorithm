import java.util.*;

class Solution {
    static HashMap<String, Integer> map;
    
    // 완탐 (조합), 문자열 정렬, HashMap 카운팅 (65-90)
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> list = new ArrayList<>();
        
        for(int k: course) {
            map = new HashMap<>();

            for(int i=0; i<orders.length; i++){
                char[] c = orders[i].toCharArray();
                Arrays.sort(c);
                comb(c, new boolean[c.length], k, 0, 0);
            }    
            
            int max = 0;
            for(int val: map.values()) {
                if(val >= 2) max = Math.max(val, max);
            }
            
            for(String key: map.keySet()) {
                if(map.get(key) == max && max >= 2) {
                    list.add(key);
                }
            }
        }
        
        Collections.sort(list);
        return list.toArray(new String[0]);
    }
    
    static void comb(char[] c, boolean[] visited, int total, int count, int start) {
        
        if(count == total) {
            String str = "";
            for(int i=0; i<visited.length; i++) {
                if(visited[i]) str += c[i];
            }
            
            map.put(str, map.getOrDefault(str, 0) + 1);
            return;
        } 
        
        for(int i=start; i<c.length; i++) {
            visited[i] = true;
            comb(c, visited, total, count+1, i+1);
            visited[i] = false;
        }
    }
}