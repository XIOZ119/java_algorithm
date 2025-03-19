import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		HashMap<String, String> map = new HashMap<>();
		
		map.put("M", "MatKor");
		map.put("W", "WiCys");
		map.put("C", "CyKor");
		map.put("A", "AlKor");
		map.put("$", "$clear");
		
		String str = br.readLine();
		
		
		bw.write(map.get(str));
		bw.flush();
		bw.close();
	}
} 