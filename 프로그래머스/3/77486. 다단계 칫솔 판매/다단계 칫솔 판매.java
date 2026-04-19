import java.util.*;

class Solution {
    static HashMap<Integer, Integer> parentMap;
    static HashMap<String, Integer> map;
    static int[] answer;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        // HashMap: 순번 (0: center)
        // 배열: 매출액 저장
        int size = enroll.length + 1;
        
        parentMap = new HashMap<>();
        map = new HashMap<>();
        for(int i=0; i<enroll.length; i++) {
            String str = enroll[i];
            String ref = referral[i];
            
            map.put(str, i+1);
            if(ref.equals("-")) {
                parentMap.put(i+1, 0);
            } else {
                int parent = map.get(ref);
                parentMap.put(i+1, parent);
            }
        }
        
        answer = new int[size];
        for(int i=0; i<seller.length; i++) {
            String sel = seller[i];
            int me = map.get(sel);
            int amo = amount[i] * 100;
            
            divAmount(me, amo);
        }
        
        int[] realAns = new int[size-1];
        for(int i=1; i<answer.length; i++){
            realAns[i-1] = answer[i];
        }
        return realAns;
    }
    
    static void divAmount(int me, int amount) {
        int minus = (int) (amount * 0.1);
        if(minus < 1 || me == 0) {
            answer[me] += amount;
            return;
        }
        
        int parent = parentMap.get(me);
        
        answer[me] += (amount - minus);
        divAmount(parent, minus);
    }
}