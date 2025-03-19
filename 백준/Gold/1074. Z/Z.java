import java.util.*;
import java.io.*;

public class Main {
	
	private static int r, c;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        int size = (int) Math.pow(2, N);
       
        int location = findLocation(size, 0, 0, 0);
        
        bw.write(location + "");
        bw.flush();
        bw.close();
    }
    
    private static int findLocation(int size, int loc, int checkX, int checkY) { // 나눈 크기, 지금까지 구한 위치, 나눈 크기 + 위치
    	
    	int resize = size / 2;
    	
    	int addLoc = 0;
    	
    	// 위치 확인 
    	if(r < resize + checkX && c < resize + checkY ) { // 제2사분면 
    		addLoc = 0;
    	}
    	else if (r < resize + checkX && c >= resize + checkY) { // 제1사분면 
    		addLoc = resize * resize;
    		checkY = resize + checkY;
    	}
    	else if (r >= resize + checkX && c < resize + checkY) { // 제3사분면 
    		addLoc = resize * resize * 2;
    		checkX += resize;
    	}
    	else if (r >= resize + checkX && c >= resize + checkY) { // 제4사분면 
    		addLoc = resize * resize * 3;
    		checkX = resize + checkX;
    		checkY = resize + checkY;
    	}
    	
    	if(resize != 1) {
    		return findLocation(resize, loc+addLoc, checkX, checkY);
    	} else {
    		return loc+addLoc;
    	}
    }
}