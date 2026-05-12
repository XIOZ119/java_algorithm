import java.util.*;

class Solution {
    static int mineralCnt;
    static int pickCnt;
    static int[] picks;
    static String[] minerals;
    static int answer = Integer.MAX_VALUE;
    
    // dia, iron, stone 
    public int solution(int[] picks, String[] minerals) {
        this.picks = picks;
        this.minerals = minerals;
        
        mineralCnt = minerals.length / 5; 
        mineralCnt += (minerals.length % 5 == 0) ? 0 : 1;
        
        for(int i=0; i<picks.length; i++) {
            pickCnt += picks[i];      
        }
        
        ArrayList<Integer> list = new ArrayList<>(); 
        perm(0, list, new int[3]);
        
        return answer;
    }
    
    static void calc(ArrayList<Integer> list) {
        int sum = 0;
        int cnt = 0;
        for(String s: minerals) {
            if(cnt / 5 >= list.size()) break;
            int pick = list.get(cnt / 5);
            
            if(pick == 0) {
                sum += 1;
            } else if(pick == 1) {
                if(s.equals("diamond")) sum += 5;
                else sum += 1;
            } else {
                if(s.equals("diamond")) sum += 25; 
                else if(s.equals("iron")) sum += 5;
                else sum += 1;
            }
            cnt++;
        }
        
        answer = Math.min(answer, sum); 
    }
     
    static void perm(int start, ArrayList<Integer> list, int[] arr) {
        if(list.size() == mineralCnt || list.size() == pickCnt) {
            calc(list);
            return;
        }
        
        for(int i=0; i<3; i++) {
            if(arr[i] >= picks[i]) continue;
            
            list.add(i);
            arr[i]++;
            perm(i, list, arr);
            list.remove(list.size() - 1);
            arr[i]--;
        }
    }
}