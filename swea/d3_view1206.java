package swea;

import java.io.*;

public class d3_view1206 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int test=1; test<=10; test++) {
            int building = Integer.parseInt(br.readLine());

            String[] arr = br.readLine().split(" ");
            int[] intArr = new int[building];
            
            for(int i=0; i<building; i++) {
                intArr[i] = Integer.parseInt(arr[i]);    
            }
            
            int result = 0;
            
            for(int i=0; i<building; i++) {
                int re = 255;
                if(intArr[i] == 0) {
                    continue;
                }
                for(int j=i-2; j<=i+2; j++) {
                    if(j == i || j<0 || j>=building) {
                        continue;
                    }
                    if(intArr[i] > intArr[j]) {
                        int d = intArr[i] - intArr[j];
                        re = Math.min(re, d);
                    } else {
                        re = 0;
                    }
                }
                result += re;
            }
            bw.write("#" + test + " " + result + "\n");
        }
        bw.flush();
        bw.close();
    }
}
