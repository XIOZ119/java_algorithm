import java.util.*;
import java.io.*;

public class d4_findRoad1219 {
    private static ArrayList<Integer>[] arrList = new ArrayList[100];
    private static int result;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int testCase=1; testCase <= 10; testCase++){
            String[] strArr = br.readLine().split(" ");
            int pair = Integer.parseInt(strArr[1]);
            String[] inputArr = br.readLine().split(" ");
            result = 0;
           
            for(int i=0; i<100; i++) {
                arrList[i] = new ArrayList<>();
            }
            
            for(int i=0; i<pair; i++) {
                int a = Integer.parseInt(inputArr[2*i]);
                int b = Integer.parseInt(inputArr[2*i+1]);

                arrList[a].add(b);
            }

            dfs(0);
            
            bw.write("#" + testCase + " " + result + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(int i) {
        for(int child: arrList[i]) {
            if(child == 99) {
                result = 1;
                break;
            }
            dfs(child);
        }
    }
}
