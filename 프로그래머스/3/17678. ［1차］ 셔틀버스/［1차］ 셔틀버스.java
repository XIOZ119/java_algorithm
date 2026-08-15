import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        int hour = 9;
        int min = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        
        for(String str: timetable) {
            String[] arr = str.split(":");
            int h = Integer.parseInt(arr[0]);
            int mi = Integer.parseInt(arr[1]);
            
            pq.add(new int[]{h, mi});
        }

        for(int i=0; i<n; i++) {
            min += (i == 0) ? 0 : t;
            
            if(min >= 60) {
                min -= 60;
                hour++;
            }
            
            int cnt = 0; 
            
            if(i == n-1) {
                int lastHour = 0;
                int lastMin = 0;
                int lastCnt = 0;
                
                for(int k=0; k<m; k++) {
                    if(pq.isEmpty()) break;
                    
                    int[] l = pq.poll(); 
                    
                    if(l[0] < hour || (l[0] == hour && l[1] <= min)) {
                        lastHour = l[0];
                        lastMin = l[1];
                        lastCnt++;
                    } else {
                        break;
                    }
                }
                
                if(lastCnt < m) {
                    answer = (hour < 10) ? "0" + hour + ":" : hour + ":";
                    answer = answer + ((min < 10) ? "0" + min + "" : min + "");
                    break;
                } else {
                    lastMin--;
                    if(lastMin < 0) {
                        lastHour--;
                        lastMin = 59;
                    }
                    
                    answer = (lastHour < 10) ? "0" + lastHour + ":" : lastHour + ":";
                    answer = answer + ((lastMin < 10) ? "0" + lastMin + "" : lastMin + "");
                }
                
            }
            
            for(int j=0; j<pq.size(); j++) {
                if(cnt >= m) break;
                int[] l = pq.peek();
                
                if(l[0] < hour || (l[0] == hour && l[1] <= min)) {
                    cnt++;
                    pq.poll();
                }
            }
        }
        
        return answer;
    }
}