import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for(int i=0; i<testCase; i++) {
            Stack<Character> stack = new Stack<>();
            String result = "";
            boolean flag = true;

            String input = br.readLine();
            for(int j=0; j<input.length(); j++) {
                char a = input.charAt(j);
                if(a == '(') {
                    stack.push(a);
                } else {
                    if(stack.isEmpty()) {
                        flag = false;
                        break;
                    }
                    char b = stack.pop();
                }
            }
            
            if(stack.isEmpty() == false) {
                flag = false;
            }

            if(flag) {
                bw.write("YES" + "\n");
            } else {
                bw.write("NO" + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}