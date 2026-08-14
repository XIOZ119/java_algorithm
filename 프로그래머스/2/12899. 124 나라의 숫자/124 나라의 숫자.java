class Solution {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        
        while(n > 0) {
            int a = n / 3;
            int b = n % 3;
            
            if(b == 0) {
                a--;
                b = 4;
            }
            
            sb.append(b);
            
            n = a;
        }
        
        return sb.reverse().toString();
    }
}