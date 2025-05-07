import java.io.*;
import java.lang.*;
import java.util.*;

public class 민트초코우유 {

    // [코드트리] - [민트초코우유]
    // NxN 격자: 각 칸은 신봉 음식 F_ij(T: 민트, C: 초코, M: 우유)와 신앙심 B_ij를 가짐

    // 1. 아침
    // - 모든 B_ij += 1

    // 2. 점심
    // - 인접한 학생끼리 같은 음식이면 그룹 형성
    // - 대표자 선정: B 큰 > 행 작은 > 열 작은
    // - 대표자 외 구성원은 B -= 1, 대표자는 B += (그룹원 수 - 1)

    // 3. 저녁 (신앙 전파)
    // - 전파 우선순위: 단일 > 이중 > 삼중 음식
    // - 전파자 순서: 대표자 B 큰 > 행 작은 > 열 작은
    // - 간절함 x = B - 1, 방향 = B % 4 (0: 위, 1: 아래, 2: 왼쪽, 3: 오른쪽)
    // - 전파 대상이 같은 음식이면 PASS
    // - 음식 다르면 전파 진행:
    //   > x > y: 강한 전파 (대상 음식 변경, 간절함 -= y+1, 대상 B += 1)
    //   > x <= y: 약한 전파 (대상 음식 병합, 간절함 = 0, 대상 B += x)
    // - 하루에 한 번만 전파당할 수 있음
    
    static int N, T;
    static String[][] drink; // 신봉 음식 배열
    static int[][] trust; // 신앙심 배열
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1}; // 위, 아래, 왼, 오
    static boolean[][] visited;
    
    static PriorityQueue<int[]> leaders = new PriorityQueue<int[]>(new Comparator<int []>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            if(o2[3] == o1[3]) {
                if(o2[2] == o1[2]) {
                    if(o1[0] == o2[0]) {
                        return o1[1] - o2[1];
                    }
                    return o1[0] - o2[0];
                }
                return o2[2] - o1[2]; 
            }
            return o1[3] - o2[3];
        }
    });
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        // 입력
        st = new StringTokenizer(br.readLine());
          
        N = Integer.parseInt(st.nextToken()); // N * N 배열
        T = Integer.parseInt(st.nextToken()); // T일 
        
        drink = new String[N][N]; 
        // T : 민트, C : 초코, M : 우유
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<N; j++) {
                drink[i][j] = str.charAt(j) + "";
            }
        }
        
        trust = new int[N][N]; 
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                trust[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int day = 0;
        while(day != T) {
            day++;

            // 아침 시간 -> 신앙심 1씩 추가 
            morning();
            
            // 점심 시간 -> 신봉 음식 완전히 같은 경우, 그룹 형성 -> 대표자 선정 
            lunch();
            
            // 저녁 시간 : 신앙 전파 
            dinner();
            
            int TCM = 0;
            int TC = 0;
            int TM = 0;
            int CM = 0;
            int T = 0;
            int C = 0;
            int M = 0;
            
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(drink[i][j].equals("TCM")) TCM += trust[i][j];
                    else if(drink[i][j].equals("TC")) TC += trust[i][j];
                    else if(drink[i][j].equals("TM")) TM += trust[i][j];
                    else if(drink[i][j].equals("CM")) CM += trust[i][j];
                    else if(drink[i][j].equals("T")) T += trust[i][j];
                    else if(drink[i][j].equals("C")) C += trust[i][j];
                    else if(drink[i][j].equals("M")) M += trust[i][j];
                }
            }
            
            sb.append(TCM + " " + TC + " " + TM + " " + CM + " " + M + " " + C + " " + T + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    static void dinner() {
        
        // 전파 당함 boolean 배열 ( 방어 상태 ) 
        boolean[][] defense = new boolean[N][N];
        
        while(!leaders.isEmpty()) {
            // 전파 순서 (x, y, 신앙심, 그룹)
            int[] jeondoja = leaders.poll();
            
            int cx = jeondoja[0];
            int cy = jeondoja[1];
            int cTrust = trust[cx][cy];
            
            if(defense[cx][cy]) continue;
            
            trust[cx][cy] = 1; // 신앙심 1만 남기고
            int want = cTrust - 1; // 간절함으로 넘기기
            int direction = cTrust % 4;
            
            int nx = cx;
            int ny = cy;
            
            String jeondojaDrink = drink[cx][cy];
            
            // 0 : 위, 1: 아래, 2: 왼쪽, 3: 오른쪽
            while(want > 0) { // 간절함 0 될 때까지 반복
                if(direction == 0) {
                    nx += dx[0];
                    ny += dy[0];
                } else if(direction == 1) {
                    nx += dx[1];
                    ny += dy[1];
                } else if(direction == 2) {
                    nx += dx[2];
                    ny += dy[2];
                } else {
                    nx += dx[3];
                    ny += dy[3];
                }
                
                if(!isValid(nx, ny)) break;
                if(jeondojaDrink.equals(drink[nx][ny])) continue;
                
                int nTrust = trust[nx][ny];
                defense[nx][ny] = true;
                
                if(want > nTrust) { // 강한 전파
                    drink[nx][ny] = jeondojaDrink;
                    want -= (nTrust + 1); // 전파자 간절함 y+1 만큼 감소
                    trust[nx][ny] += 1; // 전파 대상 신앙심 + 1
                } else { // 약한 전파
                    // T, M, C, CM, TM, TC, TCM
                    String nDrink = drink[nx][ny]; 
                    boolean[] dr = new boolean[3]; // T, C, M
                    for(int i=0; i<jeondojaDrink.length(); i++) {
                        if(jeondojaDrink.charAt(i) == 'T') dr[0] = true;
                        else if(jeondojaDrink.charAt(i) == 'C') dr[1] = true;
                        else dr[2] = true;
                    }
                    for(int i=0; i<nDrink.length(); i++) {
                        if(nDrink.charAt(i) == 'T') dr[0] = true;
                        else if(nDrink.charAt(i) == 'C') dr[1] = true;
                        else dr[2] = true;
                    }
                    
                    String d = "";
                    for(int i=0; i<3; i++) {
                        if(dr[i]) {
                            if(i == 0) d += "T";
                            else if (i == 1) d += "C";
                            else d += "M";
                        }
                    }
                    
                    drink[nx][ny] = d; // 두 개 합친 음식 신봉
                    trust[nx][ny] += want; // 대상의 신앙심 x만큼 증가
                    want = 0; // 전파자 간절함 0 
                }
            } 
        }
    }
    
    static void lunch() {
        visited = new boolean[N][N];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    makeGroup(i, j);
                }
            }
        }
    }
    
    static void makeGroup(int x, int y) {
        String startDrink = drink[x][y];
        Queue<int []> queue = new LinkedList<int[]>();
        // 대표자 선정 배열 (x, y, 신앙심)
        PriorityQueue<int []> pq = new PriorityQueue<int[]>(new Comparator<int []>() {
            @Override
            public int compare(int[] o1, int[] o2) { 
                if(o2[2] == o1[2]) { 
                    if(o2[0] == o1[0]) 
                        return o1[1] - o2[1]; // 열 작은 순서 
                    return o1[0] - o2[0]; // 행 작은 순서
                }
                return o2[2] - o1[2]; // 신앙심 큰 순서
            }
        });
        
        queue.add(new int[] {x, y});
        visited[x][y] = true;
        pq.add(new int[] {x, y, trust[x][y]});
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            int cx = cur[0];
            int cy = cur[1];
            
            for(int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(!isValid(nx, ny) || visited[nx][ny]) continue;
                
                // 같은 그룹일 경우
                if(drink[nx][ny].equals(startDrink)) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                    pq.add(new int[] {nx, ny, trust[nx][ny]});
                }
            }
        }
        
        // 신앙심 전파
        int size = pq.size();
        int[] leader = pq.poll();
        
        int leaderX = leader[0];
        int leaderY = leader[1];

        trust[leaderX][leaderY] += (size-1);
        
        while(!pq.isEmpty()) {
            int[] person = pq.poll();
            
            int personX = person[0];
            int personY = person[1];
            
            trust[personX][personY] -= 1;
        }
        
        // 대표자 음식 그룹도 함께 기록
        String leaderDrink = drink[leaderX][leaderY];
        int group = 0;
        if(leaderDrink.equals("T") || leaderDrink.equals("M") || leaderDrink.equals("C")) {
            group = 1;
        }
        else if (leaderDrink.equals("CM") || leaderDrink.equals("TM") || leaderDrink.equals("TC")) {
            group = 2;
        }
        else if (leaderDrink.equals("TCM")) {
            group = 3;
        }
        
        leaders.add(new int[] {leaderX, leaderY, trust[leaderX][leaderY], group});
    }
    
    static void morning() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                trust[i][j]++;
            }
        }
    }
    
    static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}