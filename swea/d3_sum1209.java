package swea;

import java.io.*;

public class d3_sum1209 {
	 public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	        for(int testCase = 0; testCase < 1; testCase++ ){
	            int test = Integer.parseInt(br.readLine());
	            int max = 0;

	            // 입력
	            int[][] inputArray = new int[100][100];
	            for(int i = 0; i<100; i++) {
	                String[] line = br.readLine().split(" ");      
	                for(int j=0; j<100; j++) {
	                    inputArray[i][j] = Integer.parseInt(line[j]);
	                }
	            }

	            int diaSum = 0;
	            for(int i=0; i<100; i++) {
	                int rowSum = 0;
	                int colSum = 0;
	                for(int j=0; j<100; j++){
	                    rowSum += inputArray[i][j];
	                    colSum += inputArray[j][i];
	                }
	                diaSum += inputArray[i][i];
	                max = Math.max(max, rowSum);
	                max = Math.max(max, colSum);
	            }
	            max = Math.max(max, diaSum);

	            int diaSum2 = 0;
	            for(int i=0; i<100; i++) {
	                diaSum2 += inputArray[i][100-1-i];
	            }

	            max = Math.max(max, diaSum2);

	            bw.write("#" + test + " " + max + "\n");
	        }
	        bw.flush();
	        bw.close();
	    }
}
