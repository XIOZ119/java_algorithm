import java.io.*;
import java.lang.*;
import java.util.*;

public class Solution {
    
    static class Group {
        int x;
        int y;
        int num; // 미생물 수
        int direction; // 이동방향 : 상(1), 하(2), 좌(3), 우(4)
        boolean alive = true;
        
        Group(int x, int y, int num, int direction){
            this.x = x;
            this.y = y;
            this.num = num;
            this.direction = direction;
        }
    }
    
    static int N, M, K; // N: 셀의 개수, M: 격리 시간, K: 미생물 군집의 개수
    static int[][] map;
    static Group[] groups;
    static int[] dx = {0, -1, 1, 0, 0}, dy = {0, 0, 0, -1, 1};
    static int[][] meet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int testCase = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            map = new int[N][N];
            groups = new Group[K];
            
            for(int i=0; i<K; i++) {
            	st = new StringTokenizer(br.readLine());
            	
            	int r = Integer.parseInt(st.nextToken());
            	int c = Integer.parseInt(st.nextToken());
            	int num = Integer.parseInt(st.nextToken());
            	int dir = Integer.parseInt(st.nextToken());
            	
            	groups[i] = new Group(r, c, num, dir);
            }
            	
            int time = 0;
        	// while 문 
            while(time < M) {
            	time++;
            	
            	// 1시간마다 이동
            	move();
            	
            	// 2개 이상 군집이 한 곳에 모인 경우 합쳐주기
            	for(int i=0; i<N; i++) {
            		for(int j=0; j<N; j++) {
            			if(meet[i][j] > 1) comb(i, j);
            		}
                }
            }
            
            // M 시간 후 남은 미생물 수
            int result = 0;
            for(int i=0; i<K; i++) {
            	Group g = groups[i];
            	
            	if(g.alive) {
            		result += g.num;
            	}
            }
            sb.append("#" + tc + " " + result + "\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    static void comb(int x, int y) {
    	boolean[] visited = new boolean[K];
    	int maxIndex = -1;
    	int maxNum = 0;
    	int sum = 0;
    	
    	for(int i=0; i<K; i++) {
    		Group g = groups[i];
    		
    		if(g.x == x && g.y == y) {
    			if(maxNum < g.num) {
    				maxIndex = i;
    				maxNum = g.num;
    			}
    			sum += g.num;
    			visited[i] = true;
    		}
    	}
    	
    	for(int i=0; i<K; i++) {
    		Group g = groups[i];
    		
    		if(g.x == x && g.y == y) {
    			if(maxIndex == i) {
    				g.num = sum;
    			} else {
    				g.num = 0;
    				g.alive = false;
    				g.x = -1;
    				g.y = -1;
    			}
    		}
    	}
    }
    
    
    static void move() {
    	meet = new int[N][N];
    	
    	for(int i=0; i<K; i++) {
    		Group g = groups[i];
    		
    		if(!g.alive) continue;
    		
    		int nx = g.x + dx[g.direction];
    		int ny = g.y + dy[g.direction];
    		
    		if(!isValid(nx, ny)) continue;
    		
    		if(isDrugCell(nx, ny)) {
    			g.num = g.num / 2;
    			
    			if(g.direction == 1) g.direction = 2;
    			else if(g.direction == 2) g.direction = 1;
    			else if(g.direction == 3) g.direction = 4;
    			else g.direction = 3;
    			
    			if(g.num == 0) {
    				g.alive = false;
    				g.x = -1;
    				g.y = -1;
    			}
    		}
    		
    		g.x = nx;
    		g.y = ny;
    		
    		meet[nx][ny]++;
    	}
    }
    
    static boolean isValid(int x, int y) {
    	return x >= 0 && y >= 0 && x < N && y < N;
    }
    
    static boolean isDrugCell(int x, int y) {
    	return x == 0 || y == 0 || x == N-1 || y == N-1;
    }
}
