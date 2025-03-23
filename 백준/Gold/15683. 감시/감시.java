import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M;
	static int[][] arr;
	static List<int []> cctv;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static int zero;
	static int result;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        
        zero = 0; 
        cctv = new ArrayList<>();
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<M; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        		
        		if(arr[i][j] == 0) zero++;
        		if(arr[i][j] > 0 && arr[i][j] < 6) cctv.add(new int[] {i, j});
        	}
        }
        
        visited = new boolean[N][M];
        result = Integer.MAX_VALUE;

        monitor(0, 0);
        
        if(result == Integer.MAX_VALUE) bw.write(0 + "");
        else bw.write(result + "");
        bw.flush();
        bw.close();
    }

    
    // ccty 번호 별 감시 범위 (dfs) 
    private static void monitor(int count, int monitorCount) {
    	int x = -1; 
    	int y = -1; 
    	int cctvNum = 0;
    	
    	if(cctv.size() != count) {
    		int[] loc = cctv.get(count);
    		
    		x = loc[0];
    		y = loc[1];
    		cctvNum = arr[x][y];
    	}
    	else {
    		result = Math.min(result, zero-monitorCount);
    	}
    	
    	if(cctvNum == 1) {
    		for(int i=0; i<4; i++) {
    			int cnt = 0; // cctv가 감시한 수 
    			List<int []> monitored = new ArrayList<>();

    			int nx = x;
    			int ny = y;
    			
    			while(true) {
    				nx += dx[i];
    				ny += dy[i];
    				
    				if(isValid(nx, ny) && arr[nx][ny] < 6) {
    					if(!visited[nx][ny] && arr[nx][ny] == 0) {
    						monitored.add(new int[] {nx, ny});
    						visited[nx][ny] = true;
    						cnt++;
    					}
    				}
    				else break;
    			}
    			
    			monitor(count+1, monitorCount+cnt);
    			
    			// 감시했던 곳 방문 취소 
    			for(int j=0; j<monitored.size(); j++) {
    				int[] loc = monitored.get(j);
    				
    				visited[loc[0]][loc[1]] = false;
    			}
    		}
    	}
    	
    	if(cctvNum == 2) {
    		for(int i=0; i<2; i++) {
    			int cnt = 0; // cctv가 감시한 수 
    			List<int []> monitored = new ArrayList<>();

    			int nx = x;
    			int ny = y;
    			
    			for(int j=0; j<2; j++) {
    				while(true) {
    					nx += dx[2*i + j];
    					ny += dy[2*i + j];
    					
    					if(isValid(nx, ny) && arr[nx][ny] < 6) {
        					if(!visited[nx][ny] && arr[nx][ny] == 0) {
        						monitored.add(new int[] {nx, ny});
        						visited[nx][ny] = true;
        						cnt++;
        					}
        				}
        				else break;
    				}
    			}
    			
    			monitor(count+1, monitorCount+cnt);
    			
    			// 감시했던 곳 방문 취소 
    			for(int j=0; j<monitored.size(); j++) {
    				int[] loc = monitored.get(j);
    				
    				visited[loc[0]][loc[1]] = false;
    			}
    		}
    	}
    	if(cctvNum == 3) {
    		int[] ix = {0, 3, 0, 2, 1, 3, 1, 2};
    		
    		for(int i=0; i<4; i++) {
    			int cnt = 0; // cctv가 감시한 수 
    			List<int []> monitored = new ArrayList<>();

    			for(int j=0; j<2; j++) {
    				int nx = x;
    				int ny = y;

    				while(true) {
    					nx += dx[ix[2*i + j]];
    					ny += dy[ix[2*i + j]];
    					
    					if(isValid(nx, ny) && arr[nx][ny] < 6) {
        					if(!visited[nx][ny] && arr[nx][ny] == 0) {
        						monitored.add(new int[] {nx, ny});
        						visited[nx][ny] = true;
        						cnt++;
        					}
        				}
        				else break;
    				}
    			}
    			
    			monitor(count+1, monitorCount+cnt);
    			
    			// 감시했던 곳 방문 취소 
    			for(int j=0; j<monitored.size(); j++) {
    				int[] loc = monitored.get(j);
    				
    				visited[loc[0]][loc[1]] = false;
    			}
    		}
    	}
    	if(cctvNum == 4) {    		
    		for(int i=0; i<4; i++) {
    			int cnt = 0; // cctv가 감시한 수 
    			List<int []> monitored = new ArrayList<>();
    			
    			for(int j=0; j<4; j++) {
    				int nx = x;
    				int ny = y;
    				
    				if(j==i) continue;
    				
    				while(true) {
    					nx += dx[j];
    					ny += dy[j];
    					
    					if(isValid(nx, ny) && arr[nx][ny] < 6) {
        					if(!visited[nx][ny] && arr[nx][ny] == 0) {
        						monitored.add(new int[] {nx, ny});
        						visited[nx][ny] = true;
        						cnt++;
        					}
        				}
    					else break;
    				}
    			}
    			
    			monitor(count+1, monitorCount+cnt);
    			
    			// 감시했던 곳 방문 취소 
    			for(int j=0; j<monitored.size(); j++) {
    				int[] loc = monitored.get(j);
    				
    				visited[loc[0]][loc[1]] = false;
    			}
    		}
    	}
    	if(cctvNum == 5) {    		
    		int cnt = 0; // cctv가 감시한 수 
    		List<int []> monitored = new ArrayList<>();

    		for(int i=0; i<4; i++) {
    			int nx = x;
    			int ny = y;

				while(true) {
					nx += dx[i];
					ny += dy[i];
    					
					if(isValid(nx, ny) && arr[nx][ny] < 6) {
    					if(!visited[nx][ny] && arr[nx][ny] == 0) {
    						monitored.add(new int[] {nx, ny});
    						visited[nx][ny] = true;
    						cnt++;
    					}
    				}
    				else break;
				}
    		}
    			
			monitor(count+1, monitorCount+cnt);
			
			// 감시했던 곳 방문 취소 
			for(int j=0; j<monitored.size(); j++) {
				int[] loc = monitored.get(j);
				
				visited[loc[0]][loc[1]] = false;
			}
		}
	}
    
    private static boolean isValid(int x, int y) {
    	return x > -1 && y > -1 && x < N && y < M;
    }
} 