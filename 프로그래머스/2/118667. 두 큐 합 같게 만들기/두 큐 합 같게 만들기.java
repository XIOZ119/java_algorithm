import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();
        
        long sum1 = 0;
        long sum2 = 0;
        for(int i=0; i<queue1.length; i++) {
            int num = queue1[i];
            que1.add(num);
            sum1 += num;
        }
        for(int i=0; i<queue2.length; i++) {
            int num = queue2[i];
            que2.add(num);
            sum2 += num;
        }
        
        if(sum1 == sum2) return 0;
    
        int answer = 0;
        while(answer <= 2* (queue1.length + queue2.length)) {
            if(sum1 == sum2) return answer;
            else if(sum1 > sum2) {
                int s = que1.poll();
                que2.add(s);
                sum1 -= s; 
                sum2 += s;
                answer++;
            }
            else {
                int s = que2.poll();
                que1.add(s);
                sum1 += s;
                sum2 -= s;
                answer++;
            }
        }
        
        return -1;
    }
}