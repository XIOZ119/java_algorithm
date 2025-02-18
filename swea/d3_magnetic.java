package swea;

import java.io.*;

public class d3_magnetic {
	private static int[][] arr = new int[100][100];
    private static int count;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int testCase = 1; testCase < 2; testCase++){
            br.readLine();
            count = 0;

            for(int i=0; i<100; i++) {
                String[] row = br.readLine().split(" ");
                for(int j=0; j<100; j++) {
                    arr[i][j] = Integer.parseInt(row[j]);
                }
            }

            for(int i=0; i<100; i++) { 
                boolean flag = false;
                for(int j=0; j<100; j++) {
                    if(arr[j][i] == 1) {
                        flag = true;
                    } else if (arr[j][i] == 2) {
                        if(flag) {
                            count++;
                            flag = false;
                        }  
                    }
                }
                 
            }
            bw.write(count + " ");    
        }
        bw.flush();
        bw.close();
    }
}
