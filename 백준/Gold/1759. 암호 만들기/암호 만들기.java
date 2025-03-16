import java.util.*;
import java.io.*;

public class Main {
	private static int L, C;
	private static char[] alph;
	private static ArrayList<String> result;
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        alph = new char[C];
        
        st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<C; i++) {
        	alph[i] = st.nextToken().charAt(0);
        }
        
        Arrays.sort(alph);
        
        result = new ArrayList<>();
        
        comb(0, new ArrayList<Integer>());
        
        for(String s: result) {
        	bw.write(s + "\n");
        }
        
        bw.flush();
        bw.close();
    }
    
    private static void comb(int start, List<Integer> list) {
    	
    	if(list.size() == L) {
    		int count = 0;
    		for(int l: list) {
    			if(alph[l] == 'a' || alph[l] == 'e' || alph[l] == 'i' || alph[l] == 'o' || alph[l] == 'u') count++;
    		}
    		
    		String str = "";
    		if(count>0 && L-count >= 2) {
    			for(int l: list) {
    				str += alph[l];
    			}
    			result.add(str);
    		} else return;
    	}
    	
    	for(int i=start; i<C; i++) {
    		list.add(i);
    		comb(i+1, list);
    		list.remove(list.size()-1);
    	}
    }
}