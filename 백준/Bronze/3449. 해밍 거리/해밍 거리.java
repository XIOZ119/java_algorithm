import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int i=0; i<T; i++) {
            String str1 = br.readLine();
            String str2 = br.readLine();
            int distance = 0;
            
            for(int j=0; j<str1.length(); j++) {
                if(str1.charAt(j) != str2.charAt(j)) {
                    distance++;
                }
            }
            sb.append("Hamming distance is " + distance + ".");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}