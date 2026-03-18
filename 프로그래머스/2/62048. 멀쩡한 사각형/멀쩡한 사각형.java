class Solution {
    public long solution(int w, int h) {
        long answer = (long) w * h;
        
        answer -= ((w+h) - gcd(w, h));
        return answer;
    }
    
    static long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}