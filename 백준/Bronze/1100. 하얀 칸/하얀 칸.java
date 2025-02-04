import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        
        for(int i=0; true; i++){
            String str = br.readLine();
            
            if(str == null || str.isEmpty()) break;
            
            for(int j=0; j<str.length(); j++){
                if(i%2 == 0){
                    if(j%2 == 0 && str.charAt(j) == 'F') {
                        sum++;
                    }
                } else {
                    if(j%2 != 0 && str.charAt(j) == 'F') {
                        sum++;
                    }
                }
            }
        }
        System.out.println(sb.append(sum));
    }
}