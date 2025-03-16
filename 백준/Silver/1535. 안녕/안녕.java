import java.util.*;
import java.io.*;

public class Main {
	private static int[] happy;
	private static int[] power;
	private static int N;
	private static int max;
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        
        power = new int[N];
        happy = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        
        for(int i=0; i<N; i++) {
        	power[i] = Integer.parseInt(st.nextToken());
        	happy[i] = Integer.parseInt(st2.nextToken());
        }
        
        max = 0;
        comb(0, new ArrayList<Integer>());
        
        bw.write(max + "");
        bw.flush();
        bw.close();
    }
    
    private static void comb(int start, List<Integer> list) {
    	
    	int sumPower = 0;
    	int sumHappy = 0;
    	
    	for(int l: list) {
    		sumPower += power[l];
    		sumHappy += happy[l];
    	}
    	
    	if(sumPower < 100) {
    		max = Math.max(sumHappy, max);
    	} else return;
    	
    	for(int i=start; i<N; i++) {
    		list.add(i);
    		comb(i+1, list);
    		list.remove(list.size()-1);
    	}
    }
}