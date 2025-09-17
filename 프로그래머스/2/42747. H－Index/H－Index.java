import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        int h = 0;

        for (int i = 0; i < n; i++) {
            int c = citations[n - 1 - i]; // i번째로 큰 값
            int papers = i + 1;           // 현재까지 논문 수
            if (c >= papers) h = papers;  // 조건 d[i] >= i+1
            else break;                   // 이후는 더 작으니 멈춰도 됨
        }
        return h;
    }
}