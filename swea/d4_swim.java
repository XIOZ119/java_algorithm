package swea;

import java.lang.*;
import java.io.*;
import java.util.*;

public class d4_swim {
	class Main {
	    private static int[][] pool;
	    private static int time;
	    private static boolean[][] visited;
	    private static int endX;
	    private static int endY;
	    private static int N;
	    private static BufferedWriter bw;
	    
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        bw = new BufferedWriter(new OutputStreamWriter(System.out));
	        
	        int testCase = Integer.parseInt(br.readLine());
	        for(int t=1; t<= testCase; t++) {
	            N = Integer.parseInt(br.readLine());
	            
	            pool = new int[N][N];
	            visited = new boolean[N][N];
	            
	            for(int i=0; i<N; i++) {
	                String[] row = br.readLine().split(" ");
	                for(int j=0; j<N; j++) {
	                    pool[i][j] = Integer.parseInt(row[j]);
	                }
	            }

	            String[] start = br.readLine().split(" ");
	            int startX = Integer.parseInt(start[0]);
	            int startY = Integer.parseInt(start[1]);
	            
	            String[] end = br.readLine().split(" ");
	            endX = Integer.parseInt(end[0]);
	            endY = Integer.parseInt(end[1]);
	            
	            int result = bfs(startX, startY);
	            
	            bw.write("#" + t + " " + result + "\n");
	        }
	        bw.flush(); 
	        bw.close();
	    }
	    
	    private static int bfs(int startX, int startY) throws IOException {
	        //  현재 위치 + 현재 시간을 큐에 저장해야함. 
	        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
	            public int compare(int[] a, int[] b) {
	                return a[2] - b[2];
	            }
	        });
	        
	        queue.add(new int[] {startX, startY, 0});
	        
	        visited[startX][startY] = true; 
	        
	        int[] dx = {-1, 1, 0, 0};
	        int[] dy = {0, 0, -1, 1};
	        
	        while(!queue.isEmpty()) {
	            int[] cur = queue.poll();
	            int x = cur[0], y = cur[1], time = cur[2];
	            
	            if(x == endX && y == endY) {
	                return time;
	            }
	            
	            for(int i=0; i<4; i++) {
	                int nextX = x + dx[i];
	                int nextY = y + dy[i];
	                
	                if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < N ) {
	                    if(visited[nextX][nextY] == false) {
	                        int nextTime = time; 
	                        
	                        if(pool[nextX][nextY] == 1) continue;
	                        if(pool[nextX][nextY] == 2) {
	                            if(nextTime%3 == 0) {
	                                nextTime += 3;
	                            } else if(nextTime%3 == 1) {
	                                nextTime += 2;
	                            } else {
	                                nextTime += 1;
	                            }
	                        }
	                        if(pool[nextX][nextY] == 0) {
	                            nextTime += 1;
	                        }
	                        queue.add(new int[] {nextX, nextY, nextTime});
	                        visited[nextX][nextY] = true;
	                    }
	                }
	            }
	        }
	        return -1;
	    }
	}
}
