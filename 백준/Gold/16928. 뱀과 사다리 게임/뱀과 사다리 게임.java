import java.io.*;
import java.util.*;

public class Main {

    // [백준] 16928. 뱀과 사다리 게임
    // 정점 1번(시작)에서 정점 100번(도착)까지 
    // 최소 몇 번의 주사위를 던져야 도달할 수 있느냐를 구하는 최단 거리 문제
    // 한 번의 주사위 던짐 = 1 step (가중치가 동일한 edge)
    // => BFS

    static int N, M;
    static HashMap<Integer, Integer> ladder;
    static HashMap<Integer, Integer> snake;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ladder = new HashMap<>();
        snake = new HashMap<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ladder.put(a, b);
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            snake.put(a, b);
        }

        int result = bfs();

        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    static int bfs() {
        int[] visited = new int[101];
        // 위치, 주사위 돌린 횟수
        PriorityQueue<int []> queue = new PriorityQueue<>(new Comparator<int []>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        Arrays.fill(visited, Integer.MAX_VALUE);
        
        queue.add(new int[] {1, 0});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int cp = cur[0];
            int cc = cur[1];

            // cp == 100 종료조건
            if(cp == 100) {
                return cc;
            }

            if(ladder.containsKey(cp)) { // 사다리 만난 경우
                int np = ladder.get(cp);

                if(visited[np] > cc) {
                    visited[np] = cc;
                    queue.add(new int[] {np, cc});
                }
            } 
            else if(snake.containsKey(cp)) { // 뱀 만난 경우
                int np = snake.get(cp);

                if(visited[np] > cc) {
                    visited[np] = cc;
                    queue.add(new int[] {np, cc});
                }
            }
            else {
                for(int i=6; i>=1; i--) {
                    int np = cp + i;
                    int nc = cc + 1;

                    if(np > 100) continue;

                    if(visited[np] > nc){
                        visited[np] = nc;
                        queue.add(new int[] {np, nc});
                    }
                }
            }
        }
        return 0;
    }
}  