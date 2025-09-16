import java.util.*;

class Solution {
    int basicTime, basicFee, unitTime, unitFee = 0;
    
    public int[] solution(int[] fees, String[] records) {
        // 요금
        basicTime = fees[0]; basicFee = fees[1];
        unitTime = fees[2]; unitFee = fees[3];
        
        HashMap<Integer, Integer> map = new HashMap<>();
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        
        for(int i=0; i<records.length; i++) {
            String[] recordsArr = records[i].split(" ");

            // 시간 파싱
            String[] timesArr = recordsArr[0].split(":");
            int time = Integer.parseInt(timesArr[0]) * 60 + Integer.parseInt(timesArr[1]);
            
            // 차 번호
            int carNumber = Integer.parseInt(recordsArr[1]);
            
            if(recordsArr[2].equals("IN")){
                map.put(carNumber, time);
            } else {
                int firstTime = map.remove(carNumber);
                
                int diff = time - firstTime;
                tree.put(carNumber, tree.getOrDefault(carNumber, 0) + diff);
            }
        }
        
        // 출차 안 했을 때 
        if(map.size() > 0) {
            for(int carNumber : map.keySet()) {
                int time = map.get(carNumber);
                int diff = 1439 - time;
                
                tree.put(carNumber, tree.getOrDefault(carNumber, 0) + diff);
            }
        }
        
        int[] answer = new int[tree.size()];
        
        int loc = 0;
        for(int key: tree.keySet()) {
            int t = tree.get(key);
            
            if(t <= basicTime) answer[loc] = basicFee;
            else{
                int exc = t - basicTime;
                int min = exc / unitTime; int sec = exc % unitTime;
                if(sec > 0) min++;
                
                answer[loc] = basicFee + (min * unitFee);
            }
            loc++;
        }
        
        return answer;
    }
}