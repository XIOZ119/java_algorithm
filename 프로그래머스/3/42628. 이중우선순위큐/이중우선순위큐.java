import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int size = operations.length;
        TreeSet<Integer> set = new TreeSet<>();
        
        for(int i=0; i<size; i++) {
            String str = operations[i];
            String[] tokens = str.split(" ");
            
            if(tokens[0].equals("I")){
                set.add(Integer.parseInt(tokens[1]));
            }
            if(tokens[0].equals("D")){
                if(set.size() == 0) continue;
                if(tokens[1].equals("-1")){
                    set.pollFirst();
                }
                if(tokens[1].equals("1")){
                    set.pollLast();
                }
            }
        }
        
        if(set.size() == 0) {
            return new int[] {0, 0};
        }
        if(set.size() == 1) {
            int num = set.pollFirst();
            return new int[] {num, num};
        }
        
        return new int[] {set.pollLast(), set.pollFirst()};
    }
}