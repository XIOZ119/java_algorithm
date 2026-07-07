class Solution {
    public int[] solution(int[] sequence, int k) {
        int l = Integer.MAX_VALUE;
        int[] answer = new int[2];
        
        int left = 0;
        int right = 0;
        
        int sum = sequence[left];
        while(left < sequence.length && right < sequence.length) {
            if(sum < k) {
                right++;
                if(right < sequence.length) sum += sequence[right];
            } else if(sum > k) {
                sum -= sequence[left];
                left++;
            } else {
                int d = right - left + 1;
                
                if(d < l || (d == l && answer[0] > left))  {
                    answer[0] = left;
                    answer[1] = right;
                    
                    l = d;
                }
                
                right++;
                if(right < sequence.length) sum += sequence[right];
            }
        }
        
        return answer;
    }
}