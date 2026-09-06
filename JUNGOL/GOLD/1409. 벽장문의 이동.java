import java.io.*;
import java.util.*;

public class Main{
    private static int N;
    private static boolean[] arr;
    private static int answer = Integer.MAX_VALUE;
    private static int[] order;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;

        arr[a] = true;
        arr[b] = true;

        int cnt = Integer.parseInt(br.readLine());
        order = new int[cnt];

        for(int i=0; i<cnt; i++){
            int num = Integer.parseInt(br.readLine()) - 1;
            order[i] = num;
        }

        dfs(0, 0);

        System.out.println(answer);
    }

    private static void dfs(int index, int cnt) {
        if(index >= order.length) {
            answer = Math.min(answer, cnt);
            return;
        }

        int cur = order[index]; 

        if(arr[cur]) dfs(index+1, cnt);
        else if(cur == 0) {
            // 오른쪽만 탐색 
            int count = 1;
            boolean flag = false;
            for(int i=cur+1; i<arr.length; i++) {
                if(!arr[i]) count++; 
                else {
                    flag = true;
                    break; 
                }
            }

            if(flag) {
                arr[cur] = true;
                arr[cur+count] = false;

                dfs(index+1, cnt+count);

                arr[cur] = false;
                arr[cur+count] = true;
            }
        } else if(cur == N-1) {
            // 왼쪽만 탐색
            int count = 1;
            boolean flag = false;
            for(int i=cur-1; i>=0; i--) {
                if(!arr[i]) count++; 
                else {
                    flag = true;
                    break; 
                }
            }

            if(flag) {
                arr[cur] = true;
                arr[cur-count] = false;

                dfs(index+1, cnt+count);    

                arr[cur] = false;
                arr[cur-count] = true;
            }

        } else {
            // 양쪽 탐색
            int count = 1;
            boolean flag = false;
            for(int i=cur+1; i<arr.length; i++) {
                if(!arr[i]) count++; 
                else {
                    flag = true;
                    break; 
                }
            }

            if(flag) {
                arr[cur] = true;
                arr[cur+count] = false;

                dfs(index+1, cnt+count);

                arr[cur] = false;
                arr[cur+count] = true; 
            }

            
            count = 1;
            flag = false;
            for(int i=cur-1; i>=0; i--) {
                if(!arr[i]) count++; 
                else {
                    flag = true;
                    break; 
                }
            }

            if(flag) {
                arr[cur] = true;
                arr[cur-count] = false;

                dfs(index+1, cnt+count);

                arr[cur] = false;
                arr[cur-count] = true;
            }
        }

        return;
    }
}
