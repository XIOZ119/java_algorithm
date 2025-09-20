import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        List<String[]> records = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        
        int length = record.length;
        for(int i=0; i<length; i++) {
            String[] parsing = record[i].split(" ");
            
            String action = "";
            String userId = "";
            String name = "";
            
            if(parsing.length == 3) {
                action = parsing[0];
                userId = parsing[1];
                name = parsing[2];
                
                map.put(userId, name);
            } else {
                action = parsing[0];
                userId = parsing[1];
            }
            
            if(!action.equals("Change")) records.add(new String[] {action, userId});
        }
        
        String[] answer = new String[records.size()];
        
        for(int i=0; i<records.size(); i++) {
            String[] answers = records.get(i);
        
            String action = answers[0];
            String userId = answers[1];
            
            String userName = map.get(userId);
            if(action.equals("Enter")) {
                answer[i] = userName + "님이 들어왔습니다.";
            } else {
                answer[i] = userName + "님이 나갔습니다.";
            }
        }
        return answer;
    }
}