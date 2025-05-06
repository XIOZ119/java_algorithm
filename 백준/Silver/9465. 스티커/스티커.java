import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < testCase; tc++) {
            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[2][n];
            int[][] d = new int[2][n];
            
            for(int i=0; i<2; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());

                    if(j == 0) {
                        d[i][j] = arr[i][j];
                    }
                }
            }
            
            for(int i=1; i<n; i++){
                for(int j=0; j<2; j++){
                    if(i == 1 && j == 0) {
                        d[j][i] = d[j+1][i-1] + arr[j][i];
                    } else if (i == 1 && j == 1) {
                        d[j][i] = d[j-1][i-1] + arr[j][i];
                    }else {
                        if(j == 0){
                            d[j][i] = Math.max(d[j+1][i-1] + arr[j][i], d[j][i-2] + arr[j][i]);
                            d[j][i] = Math.max(d[j][i], d[j+1][i-2] + arr[j][i]);
                        } else {
                            d[j][i] = Math.max(d[j-1][i-1] + arr[j][i], d[j][i-2] + arr[j][i]);
                            d[j][i] = Math.max(d[j][i], d[j-1][i-2] + arr[j][i]);
                        }
                    }
                }
            }

            int result = 0;

            for(int i=0; i<2; i++){
                for(int j=0; j<n; j++){
                    result = Math.max(result, d[i][j]);
                }
            }

            sb.append(result + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}  