import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        int[] rooms = new int[24 * 60 + 10];
        
        for(int i=0; i<book_time.length; i++) {
            int[] t = convertArray(book_time[i]);
            
            int startT = t[0]; int endT = t[1];
            
            for(int j=startT; j < endT; j++) {
                rooms[j]++;
            }
        }
        
        for(int i=0; i<rooms.length; i++){
            answer = Math.max(answer, rooms[i]);
        }
        
        return answer;
    }
    
    private int[] convertArray(String[] time) {
        return new int[] { splitAndConvertInt(time[0]), splitAndConvertInt(time[1]) + 10 };
    }
    
    private int splitAndConvertInt(String time) {
        String[] timeArray = time.split(":");
        
        return Integer.parseInt(timeArray[0]) * 60 + Integer.parseInt(timeArray[1]);
    }
}