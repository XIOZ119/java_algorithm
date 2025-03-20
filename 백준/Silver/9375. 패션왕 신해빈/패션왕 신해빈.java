import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int result;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int testCase = Integer.parseInt(br.readLine());
        
        for(int tc=0; tc<testCase; tc++) {
        	HashMap<String, Integer> map = new HashMap<>();
            n = Integer.parseInt(br.readLine());
            
            for(int i=0; i<n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();
                String style = st.nextToken();
                map.put(style, map.getOrDefault(style, 0) + 1);
            }
            
            result = 1;
            
            for(int i: map.values()) {
            	result *= i+1;
            }
            
            
            sb.append((result-1) + "\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
} 