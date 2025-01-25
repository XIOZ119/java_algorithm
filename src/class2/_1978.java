import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int num = Integer.parseInt(bf.readLine());
        String st = bf.readLine();
        String[] stArr = st.split(" ");
        int[] num2 = new int[num];
        
        for(int i=0; i < num; i++){
            num2[i] = Integer.parseInt(stArr[i]);
        }
        
        int count = 0; 
        
        for(int i=0; i < num; i++){
            if (num2[i] > 1) {
                int fail = 0;
                for(int j=2; j<num2[i]; j++){
                    if(num2[i] % j == 0) {
                         fail++;
                         break;
                    } 
                }
                if(fail == 0) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}