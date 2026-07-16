import java.util.*;

class Solution {

    public int solution(int[][] info, int n, int m) {

        // dp[a] = A의 흔적이 a일 때 가능한 B 흔적의 최솟값
        int[] dp = new int[n];

        // 아직 만들 수 없는 상태는 큰 값으로 초기화
        Arrays.fill(dp, Integer.MAX_VALUE);

        // 아무 물건도 처리하지 않은 상태
        dp[0] = 0;

        for (int i = 0; i < info.length; i++) {

            int[] next = new int[n];
            Arrays.fill(next, Integer.MAX_VALUE);

            for (int a = 0; a < n; a++) {

                // 현재 만들 수 없는 상태
                if (dp[a] == Integer.MAX_VALUE) {
                    continue;
                }

                int b = dp[a];

                // 1. 현재 물건을 A가 훔치는 경우
                int nextA = a + info[i][0];

                if (nextA < n) {
                    next[nextA] = Math.min(next[nextA], b);
                }

                // 2. 현재 물건을 B가 훔치는 경우
                int nextB = b + info[i][1];

                if (nextB < m) {
                    next[a] = Math.min(next[a], nextB);
                }
            }

            dp = next;
        }

        // 가능한 A의 흔적 중 가장 작은 값 반환
        for (int a = 0; a < n; a++) {
            if (dp[a] < m) {
                return a;
            }
        }

        return -1;
    }
}