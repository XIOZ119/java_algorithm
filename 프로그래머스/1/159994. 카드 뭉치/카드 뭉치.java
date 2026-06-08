class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int index1 = 0;
        int index2 = 0;
        
        boolean flag = true;
        for(int i=0; i<goal.length; i++) {
            String g = goal[i];
            
            if(index1 < cards1.length && cards1[index1].equals(g)) {
                index1++; 
                continue;
            }
            
            if(index2 < cards2.length && cards2[index2].equals(g)) {
                index2++;
                continue;
            }
            
            flag = false;
            break;
        }
        
        if(flag) return "Yes";
        else return "No";
    }
}