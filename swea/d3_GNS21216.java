import java.util.*;
import java.io.*;

public class d3_GNS21216 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=10; tc++) {
			int t = Integer.parseInt(br.readLine());
			
			char[][] arr = new char[100][100];
			
			for(int i=0; i<100; i++) {
				String str = br.readLine();
				for(int j=0; j<100; j++) {
					arr[i][j] = str.charAt(j);
				}
			}
			
			int maxCount = 0;
			
			// 가로 기준
			for(int i=0; i<100; i++) {
				for(int j=0; j<100; j++) {
					
					int k = 1;
					int count = 0;
					while(j-k>-1 && j+k<100) {
						if(arr[i][j-k] == arr[i][j+k]) {
							count += 2;
							k++;
						} else break;
					}
					maxCount = Math.max(maxCount, count+1);
					
					k = 1;
					count = 0;
					if(j != 99 && arr[i][j] == arr[i][j+1]) {						
						while(j-k>-1 && j+1+k<100) {
							if(arr[i][j-k] == arr[i][j+1+k]) {
								count += 2;
								k++;
							} else break;
						}
					}
					maxCount = Math.max(maxCount, count+2);
				}
			}
			
			// 세로 기준
			for(int i=0; i<100; i++) {
				for(int j=0; j<100; j++) {
					
					int k = 1;
					int count = 0;

					while(j-k>-1 && j+k<100) {
						if(arr[j-k][i] == arr[j+k][i]) {
							count += 2;
							k++;
						} else break;
					}
					count++;
					maxCount = Math.max(maxCount, count);
					
					k = 1;
					count = 0;
					
					if(j != 99 && arr[j][i] == arr[j+1][i]) {						
						while(j-k>-1 && j+1+k<100) {
							if(arr[j-k][i] == arr[j+1+k][i]) {
								count += 2;
								k++;
							} else break;
						}
					}
					maxCount = Math.max(maxCount, count+2);
				}
			}
			
			sb.append("#" + t + " " + maxCount + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}