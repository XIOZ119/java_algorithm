import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        
        Queue<Integer> que = new LinkedList<>();

        for(int i=1; i<=num; i++) {
            que.offer(i);
        }
        
        int count = 0;
        
        while(num != 1) {
            if(count%2 == 0) {
                que.poll();
                num--;
            } else {
                int a = que.poll();
                que.offer(a);
            }
            count++;
        }

        bw.write(que.peek() + " ");
        bw.flush();
        bw.close();
    }
}