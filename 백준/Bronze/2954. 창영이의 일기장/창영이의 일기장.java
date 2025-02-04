import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String str = br.readLine();
        String result = "";
        
        for(int i=0; i< str.length(); i++){
            if(str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u' ){
                result += str.charAt(i);
                i += 2;
            } else {
                result += str.charAt(i) + "";
            }
        }
        System.out.println(sb.append(result));
    }
}