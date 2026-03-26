import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        HashSet<Character> set = new HashSet<>();
        for (int start = 0; start < N; start++) {
            String inputStr = br.readLine();
            String[] inputArr = inputStr.split(" ");
            boolean flag = false; // 위치 발견
            int[] loc = new int[2];

            for(int i=0; i<inputArr.length; i++){
                String str = inputArr[i];
                char c = Character.toUpperCase(str.charAt(0));

                if(!set.contains(c)){
                    set.add(c);
                    loc[0] = i;
                    loc[1] = 0;
                    flag = true;
                    break;
                }
            }

            loop:
            if(!flag) {
                for(int i=0; i<inputArr.length; i++){
                    String str = inputArr[i];
                    for(int j=1; j<str.length(); j++){
                        char c = Character.toUpperCase(str.charAt(j));
                        if(!set.contains(c)){
                            set.add(c);
                            loc[0] = i;
                            loc[1] = j;
                            flag = true;
                            break loop;
                        }
                    }
                }
            }

            if(!flag){
                for(String s: inputArr){
                    sb.append(s + " ");
                }
            } else {
                for(int i=0; i<inputArr.length; i++){
                    String str = inputArr[i];

                    if(loc[0] != i) {
                        sb.append(str + " ");
                        continue;
                    }

                    for(int j=0; j<str.length(); j++){
                        char c = str.charAt(j);
                        if(loc[1] != j) {
                            sb.append(c);
                            continue;
                        }

                        sb.append("[");
                        sb.append(c);
                        sb.append("]");
                    }
                    sb.append(" ");
                }
            }

            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}