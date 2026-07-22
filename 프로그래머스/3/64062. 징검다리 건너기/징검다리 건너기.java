import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < stones.length; i++) {

            // 현재 윈도우를 벗어난 인덱스 제거
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // 새 값보다 작거나 같은 값은 앞으로 최대가 될 수 없으므로 제거
            while (!deque.isEmpty()
                    && stones[deque.peekLast()] <= stones[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // 길이 k의 윈도우가 완성된 경우
            if (i >= k - 1) {
                int max = stones[deque.peekFirst()];
                answer = Math.min(answer, max);
            }
        }
        
        return answer;
    }
}