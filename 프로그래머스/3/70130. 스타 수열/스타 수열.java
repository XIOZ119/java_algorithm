class Solution {
    public int solution(int[] a) {
        int n = a.length;
        int[] count = new int[n];

        for (int num : a) {
            count[num]++;
        }

        int answer = 0;

        for (int x = 0; x < n; x++) {
            if (count[x] * 2 <= answer) continue;

            int pair = 0;

            for (int i = 0; i < n - 1; ) {
                if ((a[i] == x || a[i + 1] == x) && a[i] != a[i + 1]) {
                    pair++;
                    i += 2;
                } else {
                    i++;
                }
            }

            answer = Math.max(answer, pair * 2);
        }

        return answer;
    }
}