import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N];
        int[] arr2 = new int[N];
        int[] result = new int[N];
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	arr2[i] = arr[i];
        }
        
        Arrays.sort(arr2);
        result[0] = 0;
        map.put(arr2[0], 0);
        
        for(int i=1; i<N; i++) {
        	if(arr2[i-1] == arr2[i]) {
        		result[i] = result[i-1];
        		map.put(arr2[i], result[i]);
        	}
        	else {
        		result[i] = result[i-1]+1;
        		map.put(arr2[i], result[i]);
        	}
        }
        
        for(int i=0; i<N; i++) {
        	int num = map.get(arr[i]);
        	bw.write(num + " ");
        }
        bw.flush();
        bw.close();
    }
}