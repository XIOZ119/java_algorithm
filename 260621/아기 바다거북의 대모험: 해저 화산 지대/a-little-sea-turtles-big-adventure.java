import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, K;
    private static int[][] sea;
    private static int[][] fire;
    private static ArrayList<Turtle> turtles;
    private static ArrayList<Volcano> volcanos;
    private static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    private static int turn;

    private static class Turtle {
        int x, y;
        boolean flag = false; // true: 죽음
        int turn = -1;

        Turtle(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Volcano {
        int x, y, p;
        boolean flag = false; // 분출 전
        int press = 0; // 압력

        Volcano(int x, int y, int p) {
            this.x = x;
            this.y = y; 
            this.p = p;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        sea = new int[N][N];
        fire = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        turtles = new ArrayList<>();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            sea[x][y] = 2;

            turtles.add(new Turtle(x, y));
        }

        volcanos = new ArrayList<>();
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            volcanos.add(new Volcano(x, y, p));
        }

        // sea 배열: 1-산호초, 2-거북이
            
        // M 마리 거북이
        // 바다 N x N , 목적지 N-1 , N-1 좌표 
        // 시뮬레이션 최대 100턴 

        turn = 1;
        while(turn <= 100) {
            move(); // 10 * bfs

            ArrayList<Volcano> pos = new ArrayList<>();
            ArrayList<Volcano> notPos = new ArrayList<>();
            for(Volcano v: volcanos) {
                v.press += 10;

                if(v.press + fire[v.x][v.y] >= v.p) {
                    v.flag = true;
                    pos.add(v);
                } else {
                    notPos.add(v);
                }
            }

            fireOut(pos);

            while (true) {
                ArrayList<Volcano> erupted = new ArrayList<>();

                for (Volcano v : notPos) {
                    if (v.press + fire[v.x][v.y] >= v.p) {
                        v.flag = true;
                        erupted.add(v);
                    }
                }

                if (erupted.isEmpty()) break;

                fireOut(erupted);
                notPos.removeAll(erupted);
            }

            for(Turtle t: turtles) {
                if(t.flag) continue; 
                if(fire[t.x][t.y] >= 20) t.flag = true;
            }

            reset();

            turn++; 
        }
        
        
        for(Turtle t: turtles) {
            System.out.println(t.turn);
        }
    }

    private static void reset(){
        fire = new int[N][N];

        for(Volcano v: volcanos) {
            if(v.flag) {
                v.flag = false;
                v.press = 0;
            }
        }
    }

    // 열기 전파
    private static void fireOut(ArrayList<Volcano> vol) {
        for(Volcano v: vol) {
            fire[v.x][v.y] += v.p;

            for(int i=0; i<4; i++) {
                int p = v.p / 2; 
                int nx = v.x;
                int ny = v.y;
                
                while(p > 0) {
                    nx += dx[i];
                    ny += dy[i];

                    if(!isValidOut(nx, ny)) break;

                    fire[nx][ny] += p;

                    p /= 2;
                }
            }
        }
    }

    private static class Moving {
        int x, y, cnt, dir;

        // 우(0), 하(1), 좌(2), 상(3)
        Moving(int x, int y, int cnt, int dir) {
            this.x = x; 
            this.y = y;
            this.cnt = cnt;
            this.dir = dir;
        }
    }

    private static void move() {
        for(Turtle t: turtles) {
            if(t.flag || t.turn != -1) continue;

            ArrayList<Moving> pos = new ArrayList<>();
            Queue<Moving> que = new LinkedList<>();
            boolean[][] visited = new boolean[N][N];

            for(int d=0; d<4; d++) {
                int nx = t.x + dx[d];
                int ny = t.y + dy[d];

                if(!isValid(nx, ny) || visited[nx][ny]) continue;

                que.add(new Moving(nx, ny, 1, d)); 
                visited[nx][ny] = true;
            }

            while(!que.isEmpty()) {
                Moving cur = que.poll(); 

                if(cur.x == N-1 && cur.y == N-1) {
                    pos.add(cur);
                    continue;
                }

                for(int d=0; d<4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if(!isValid(nx, ny) || visited[nx][ny]) continue; 

                    que.add(new Moving(nx, ny, cur.cnt+1, cur.dir));
                    visited[nx][ny] = true;
                }
            }

            if(pos.size() > 0) {
                Collections.sort(pos, (a, b) -> {
                    if(a.cnt == b.cnt) {
                        return a.dir - b.dir;
                    }
                    return a.cnt - b.cnt;
                });

                int movDir = pos.get(0).dir;
                sea[t.x][t.y] = 0;
                
                int nx = t.x + dx[movDir];
                int ny = t.y + dy[movDir];

                if(nx == N-1 && ny == N-1) {
                    t.turn = turn;
                    continue;
                }

                t.x = nx;
                t.y = ny;

                sea[nx][ny] = 2;
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x > -1 && y > -1 && x < N && y < N && sea[x][y] == 0;
    }

    private static boolean isValidOut(int x, int y) {
        return x > -1 && y > -1 && x < N && y < N && sea[x][y] != 1; 
    }
}