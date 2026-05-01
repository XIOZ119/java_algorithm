import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        if(cacheSize == 0) {
            return cities.length * 5;
        }
        
        ArrayList<String> list = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        
        for(String str: cities) {
            String cityName = str.toUpperCase();
            
            if(list.isEmpty()) {
                list.add(cityName);
                set.add(cityName);
                answer += 5;
                continue;
            }
            
            if(set.contains(cityName)) {
                answer += 1;
                for(int i=0; i<list.size(); i++) {
                    String city = list.get(i);
                    if(cityName.equals(city)) {
                        list.remove(i);
                        break;
                    }
                }
            } else {
                answer += 5;
                set.add(cityName);
                
                if(list.size() >= cacheSize) {
                    String city = list.get(0);
                    list.remove(0);
                    set.remove(city);
                }
            }
            list.add(cityName);
        }
        
        return answer;
    }
}