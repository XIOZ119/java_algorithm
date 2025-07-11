class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        String answer = "";
        
        char[] chars = my_string.toCharArray();
        int length = overwrite_string.length();
        
        for(int i=0; i<chars.length; i++) {
            if(i < s) {
                answer += chars[i] + "";
            }
            else if( i >= s && i < s+length) {
                answer += overwrite_string;
                i += length-1;
            }
            else {
                answer += chars[i] + "";
            }
        }
        return answer;
    }
}