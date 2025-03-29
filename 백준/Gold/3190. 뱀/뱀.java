import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	static int N;
	// 우, 하, 좌, 상 
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine()); // map 크기
        int apple = Integer.parseInt(br.readLine()); // 사과 개수 
        int[][] map = new int[N][N];
        
        for(int i=0; i<apple; i++) { // 사과 위치 기록 
        	st = new StringTokenizer(br.readLine());
        	
        	int x = Integer.parseInt(st.nextToken())-1;
        	int y = Integer.parseInt(st.nextToken())-1;
        	map[x][y] = 1;
        }
        
        int changeD = Integer.parseInt(br.readLine());
        
        Queue<int[]> snake = new LinkedList<>(); // 뱀의 머리부터 꼬리까지 위치 
        
        int cx = 0, cy = 0; // 현재 뱀 머리 위치 
        int direction = 0; // 방향 오른쪽 
        int curTime = 0; // 시간 기록 
        boolean[][] visited = new boolean[N][N]; // 뱀의 머리부터 꼬리까지 위치 : 자기 자신과 부딪히는 지 파악하기 위함 
        snake.add(new int[] {cx, cy});
        visited[cx][cy] = true;
        boolean end = false;
        
        loopOut:
        for(int i=0; i<changeD; i++) { // 방향바뀔 때까지
        	st = new StringTokenizer(br.readLine());
        	
        	int time = Integer.parseInt(st.nextToken());
        	char d = st.nextToken().charAt(0);
        	
        	while(curTime != time) {
        		curTime++;
        		cx += dx[direction];
        		cy += dy[direction];
        		
        		// 자신의 몸에 부딪힌 경우 or 벽을 넘어간 경우
        		if(!isValid(cx, cy) || visited[cx][cy]) {
        			if(i != changeD-1) { // 남은 입력 버리기 
        				int cnt = changeD-1-i;
        				for(int j=0; j<cnt; j++) {
        					br.readLine();
        				}
        			}
        			end = true;
        			break loopOut;
        		}

    			snake.add(new int[] {cx, cy});
    			visited[cx][cy] = true;
        		
        		if(map[cx][cy] == 0) { // 꼬리 삭제
        			int[] s = snake.poll();
        			visited[s[0]][s[1]] = false;
        		} else {
        			map[cx][cy] = 0;
        		}
        	}
        	
        	if(d == 'L') direction--;
        	else direction++;
        	
        	if(direction == -1) direction = 3;
        	if(direction == 4) direction = 0;
        }
        
        // 방향 바뀌었고 게임 끝나지 않았다면, 방향 그대로 게임 진행 
        if(!end) {
        	while(true) {
        		curTime++;
        		cx += dx[direction];
        		cy += dy[direction];
                
        		// 자신의 몸에 부딪힌 경우 or 벽을 넘어간 경우
        		if(!isValid(cx, cy) || visited[cx][cy]) {
        			break;
        		}
        		
        		snake.add(new int[] {cx, cy});
        		visited[cx][cy] = true;
        		
        		if(map[cx][cy] == 0) { // 꼬리 삭제
        			int[] s = snake.poll();
        			visited[s[0]][s[1]] = false;
        		}
        	}
        }

        bw.write(curTime + "");
        bw.flush();
        bw.close();
    }
    
    private static boolean isValid(int x, int y) {
    	return x > -1 && y > -1 && x < N && y < N; 
    }
}
