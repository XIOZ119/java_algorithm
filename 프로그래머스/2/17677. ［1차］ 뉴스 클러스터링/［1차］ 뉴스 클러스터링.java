import java.util.*;

class Solution {
    static String STR1, STR2;
    static HashMap<String, Integer> map1, map2;
    static ArrayList<String> list;
    
    public int solution(String str1, String str2) {
        
        // 문자열 = 대문자
        STR1 = str1.toUpperCase();
        STR2 = str2.toUpperCase();
        
        // 문자열에 해당하는 해시맵
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        
        // 겹치지 않는 문자열 보관 
        list = new ArrayList<>();
        
        // 두글자씩 끊어 다중집합 저장하기 
        save(STR1, map1); save(STR2, map2);
        
        int min = 0; int max = 0;
        
        for(int i=0; i<list.size(); i++) {
            String l = list.get(i);
            
            if(map1.containsKey(l) && map2.containsKey(l)) {
                int a = map1.get(l);
                int b = map2.get(l);
                
                // 교집합 
                min += Math.min(a, b);
                // 합집합
                max += Math.max(a, b);
            } 
            else if(map1.containsKey(l) && !map2.containsKey(l)){
                max += map1.get(l);
            }
            else if(!map1.containsKey(l) && map2.containsKey(l)){
                max += map2.get(l);
            }
        }
        
        float answer = 0;
        if(min == 0 && max == 0) return 65536;
        if(min == 0 || max == 0) return 0;
        
        float div = (float)min / (float)max;
        
        if(div != 0) answer = div * 65536;
        if(div == 0) answer = 65536;
        
        return (int)answer;
    }
    
    private static void save(String str, HashMap<String, Integer> map) {
        
        for(int i=0; i<str.length()-1; i++){
            char a = str.charAt(i);
            char b = str.charAt(i+1);
            
            if(a < 65 || a > 90 || b < 65 || b > 90) continue;
            
            String A = a + ""; String B = b + "";
            String cur = A + B;
            
            if(!map1.containsKey(cur) && !map2.containsKey(cur)) list.add(cur);
            
            if(map.containsKey(cur)) {
                int count = map.get(cur);
                map.put(cur, count+1);
            } else {
                map.put(cur, 1);
            }
        }
    }
}