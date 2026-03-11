class Solution {
    static final int MOD = 1_000_000_007;

    public int solution(int n) {
        long[] dp = new long[Math.max(n + 1, 7)];

        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23;
        dp[5] = 62;
        dp[6] = 170;

        if (n <= 6) return (int) dp[n];

        for (int i = 7; i <= n; i++) {
            dp[i] = (
                    dp[i - 1]
                    + 2L * dp[i - 2]
                    + 6L * dp[i - 3]
                    + dp[i - 4]
                    - dp[i - 6]
            ) % MOD;

            if (dp[i] < 0) dp[i] += MOD;
        }

        return (int) dp[n];
    }
}