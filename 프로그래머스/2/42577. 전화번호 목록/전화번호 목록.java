import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        ArrayList<String> list = new ArrayList<>();
        for(String phone: phone_book) {
            list.add(phone); 
        }
        
        Collections.sort(list, (a, b) -> {
            return a.length() - b.length();
        });
        
        HashSet<String> set = new HashSet<>(); 
        for(String phone: list) {
            if(set.isEmpty()) {
                set.add(phone); 
                continue;
            }
            
            for(int i=0; i<phone.length(); i++) {
                String sub = phone.substring(0, i+1);
                
                if(set.contains(sub)) {
                    answer = false;
                    break;
                }
            }
            
            set.add(phone);
        }
        
        return answer;
    }
}