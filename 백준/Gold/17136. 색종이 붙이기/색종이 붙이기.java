import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	
	static int[][] arr;
	static int[] paperNum;
	static int paperResult;
	static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        arr = new int[10][10];
        paperNum = new int[] {0, 5, 5, 5, 5, 5};
        paperResult = Integer.MAX_VALUE;
        visited = new boolean[10][10];
        
        boolean flag = false;
        
        for(int i=0; i<10; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<10; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        		if(arr[i][j] == 1) flag = true;
        	}
        }
        
        if(!flag) {
        	bw.write("0");
        } else {
        	backTrack(0, 0, 0);
        	
        	if(paperResult == Integer.MAX_VALUE) paperResult = -1;

            bw.write(paperResult + "");
        }
        
        bw.flush();
        bw.close();
    }    
    
    private static void backTrack(int x, int y, int paper) {
    	
    	// 색종이 다 붙였을 경우 
    	boolean flag = true;
    	for(int i=0; i<10; i++) {
    		for(int j=0; j<10; j++) {
    			if(arr[i][j] == 1 && !visited[i][j]) {
    				flag = false;
    				break;
    			}
    		}
    	}
    	
    	if(flag) {
    		if(paperResult == 0 || paperResult > paper) {
				paperResult = paper;
				return;
			}
    	}
    	
    	
    	// 아직 색종이 덜 붙였을 경우
    	for(int i=x; i<10; i++) {
    		int startY = 0;
    		if(i == x) startY = y;
    		
    		for(int j=startY; j<10; j++) {
    			if(arr[i][j] != 1 || visited[i][j]) continue;
    			
				for(int size = 5; size >= 1; size--) {
					if(paperNum[size] == 0) continue;
					
					boolean canAttach = true;
					for (int ii = 0; ii < size; ii++) {
					    for (int jj = 0; jj < size; jj++) {
					        if (i + ii >= 10 || j + jj >= 10 || arr[i+ii][j+jj] != 1 || visited[i+ii][j+jj]) {
					            canAttach = false;
					        }
					    }
					}
					if (!canAttach) continue;
					
					paperNum[size]--;
					
					for(int ii = 0; ii < size; ii++) {
						for(int jj = 0; jj < size; jj++) {
							visited[i+ii][j+jj] = true;
						}
					}
					
					backTrack(i, j, paper+1);
					
					paperNum[size]++;
					
					for(int ii = 0; ii < size; ii++) {
					    for(int jj = 0; jj < size; jj++) {
					        visited[i+ii][j+jj] = false; // 색종이 뗀다 (복구)
					    }
					}
				}
				
	    		return;
    		}		
    	}
    }   
}