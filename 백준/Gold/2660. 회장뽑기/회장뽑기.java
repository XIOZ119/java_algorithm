import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] d;
    static List<Integer>[] graph;
    static int[] scores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        d = new int[N+1][N+1];
        graph = new ArrayList[N+1];
        scores = new int[N+1];

        for(int i = 0; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        while(true) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1) {
                break;
            }

            graph[a].add(b);
            graph[b].add(a);
        }

        for(int i = 1; i < N+1; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
            findScore(i);
        }

        int leaderScore = Integer.MAX_VALUE;
        List<Integer> leaders = new ArrayList<>();

        for(int i = 1; i < N+1; i++) {
            if(leaderScore > scores[i]) {
                leaderScore = scores[i];
                leaders.clear();
                leaders.add(i);
            }
            else if(leaderScore == scores[i]) {
                leaders.add(i);
            }
        }

        Collections.sort(leaders);

        sb.append(leaderScore).append(" ").append(leaders.size()).append("\n");
        for(int leader: leaders) {
            sb.append(leader).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void findScore(int start) {
        Queue<int[]> queue = new LinkedList<>();
        d[start][start] = 0;
        queue.add(new int[] {start, 0});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curIdx = cur[0];
            int curScore = cur[1];

            for(int next: graph[curIdx]) {
                if(d[start][next] <= curScore + 1) continue;

                d[start][next] = curScore + 1;
                queue.add(new int[] {next, curScore + 1});
            }
        }

        for(int i = 1; i < N+1; i++) {
            scores[start] = Math.max(scores[start], d[start][i]);
        }
    }
}