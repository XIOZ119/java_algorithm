import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] list = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(new int[]{b, i});
            list[b].add(new int[]{a, i});
        }

        long[] dist = new long[N+1];
        PriorityQueue<long []> q = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        Arrays.fill(dist, Long.MAX_VALUE);

        dist[1] = 0;
        q.add(new long[] {1, 0});
        while(!q.isEmpty()){
            long[] cur = q.poll();

            int cLoc = (int) cur[0];
            long cTime = cur[1];

            for(int[] next: list[cLoc]){
                int nLoc = next[0];
                int nTime = next[1];

                if(cTime > dist[cLoc]) continue;

                long nextTime;
                if(cTime <= nTime){
                    nextTime = nTime + 1;
                } else {
                    // 간선 k는 k, k+M, k+2M ... 시간에만 탈 수 있음
                    // k + x*M >= t
                    // x*M >= t-k
                    long diff = cTime - nTime; // t-k
                    // x*M >= diff  (x: 정수) -> 올림 나눗셈 필요
                    long jump = (diff + M - 1) / M; // 올림 나눗셈이고, jump = x시간
                    nextTime = nTime + jump * M + 1; // k + jump * M
                }

                if(dist[nLoc] > nextTime) {
                    dist[nLoc] = nextTime;
                    q.add(new long[]{nLoc, nextTime});
                }
            }

        }

        bw.write(dist[N] + "");
        bw.flush();
        bw.close();
    }
}