import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int n = jobs.length;

        // 1) 도착시간 기준 정렬
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));

        // 2) 실행시간 기준 최소 힙 (start, duration)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        
        long time = 0;   // 현재 시각
        long sum  = 0;   // 총 (완료시각 - 도착시각)
        int i = 0;       // 아직 힙에 넣지 않은 jobs 인덱스
        int done = 0;    // 처리한 작업 수
        
        while (done < n) {
            // 현재 시각까지 도착한 작업을 모두 힙에 투입
            while (i < n && jobs[i][0] <= time) pq.offer(jobs[i++]);

            if (pq.isEmpty()) {
                // 대기중인 작업이 없다면, 다음 작업 시작 시각으로 점프
                time = jobs[i][0];
                continue;
            }

            // 가장 짧은 실행시간 작업부터 처리
            int[] cur = pq.poll();
            time += cur[1];
            sum  += time - cur[0]; // (완료시각 - 도착시각)
            done++;
        }

        return (int)(sum / n);
    }
}