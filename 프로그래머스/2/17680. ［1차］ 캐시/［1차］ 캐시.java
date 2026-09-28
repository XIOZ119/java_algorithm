import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        HashSet<String> set = new HashSet<>();
        ArrayList<String> list = new ArrayList<>();
        
        for(String c: cities) {
            String city = c.toUpperCase();
            
            if(cacheSize == 0) {
                answer += 5;
                continue; 
            }
            
            if(set.isEmpty()) {
                set.add(city);
                list.add(city);
                answer += 5;
                continue;
            }
            
            if(set.contains(city)) {
                for(int i=0; i<list.size(); i++) {
                    if(!list.get(i).equals(city)) continue;
                    
                    list.remove(i);
                    list.add(city);
                    answer += 1;
                    break;
                }
            } else {
                if(set.size() >= cacheSize) {
                    String str = list.get(0);
                    list.remove(0);
                    set.remove(str);
                }
                
                set.add(city);
                list.add(city);
                answer += 5;
            }
        }
        
        return answer;
    }
}