import java.util.*;

// git 
class Solution {
    public double[] solution(int k, int[][] ranges) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(k);
        
        while(k > 1) {
            if(k % 2 == 0) {
                k = k / 2;
            } else {
                k = k*3 + 1;
            }
            
            list.add(k);
        }
        
        double[] loc = new double[list.size()];
        
        for(int i=0; i<list.size(); i++) {
            loc[i] = list.get(i);
        }
        
        double[] area = new double[list.size() - 1];
        
        for(int i=0; i<area.length; i++){
            area[i] = loc[i] + (loc[i+1] - loc[i]) / 2;
        }
        
        double[] answer = new double[ranges.length];
        
        for(int i=0; i<ranges.length; i++) {
            int start = ranges[i][0];
            int end = ranges[i][1];
            if(end < 0) end = loc.length-1 + end;
            else if(end == 0) end = loc.length-1;
            
            if(start > end) answer[i] = -1.0;
            else if(start == end) answer[i] = 0.0;
            else {
                for(int j=start; j<end; j++) {
                    answer[i] += area[j];
                }
            }
        }
        return answer;
    }
}