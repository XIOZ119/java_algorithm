import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		List<int []> board = new ArrayList<>();
		
		board.add(new int[] {12, 1600});
		board.add(new int[] {11, 894});
		board.add(new int[] {11, 1327});
		board.add(new int[] {10, 1311});
		board.add(new int[] {9, 1004});
		board.add(new int[] {9, 1178});
		board.add(new int[] {9, 1357});
		board.add(new int[] {8, 837});
		board.add(new int[] {7, 1055});
		board.add(new int[] {6, 556});
		board.add(new int[] {6, 773});
		
		int i = Integer.parseInt(br.readLine());
		
		
		bw.write(board.get(i-1)[0] + " " + board.get(i-1)[1]);
		bw.flush();
		bw.close();
	}
} 