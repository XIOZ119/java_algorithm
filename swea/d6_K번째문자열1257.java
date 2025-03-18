import java.util.*;
import java.io.*;

public class d6_K번째문자열1257 {
	
	static ArrayList<String> suffixArr;
	static TreeSet<String> subString;
	private static String str;
	static int[] LCP;
	
	// HashSet, 정렬 -> 시간초과 -> TreeSet 으로 해결 가능 
	// or 접미어 배열 -> LCP 활용
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=testCase; tc++) {
			int K = Integer.parseInt(br.readLine()); // K번째 오는 수 
			
			str = br.readLine(); // 문자열 
			
			suffixArr = new ArrayList<String>();
			subString = new TreeSet<String>();
			getSuffixArr(str);
			
			LCP = new int[suffixArr.size()];
			setLCP();
			
			getSubString();
			
			ArrayList<String> result = new ArrayList<>(subString);
			
			sb.append("#" + tc + " ");
			if(result.size() > K-1) {
				sb.append(result.get(K-1) + "\n");
			} else {
				sb.append("none" + "\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	// 접미어 배열 생성 
	static void getSuffixArr(String str) {
		String s = "";
		for(int i=str.length()-1; i>-1; i--) {
			s = str.charAt(i) + s;
			suffixArr.add(s);
		}
		Collections.sort(suffixArr);
	}
	
	// 부분 문자열 생성 및 LCP 배열 생성
	static void setLCP() {
		for(int i=1; i<str.length(); i++) {
			int idx = 0;
			int cnt = 0;
			
			String str1 = suffixArr.get(i - 1);
			String str2 = suffixArr.get(i);
			
			while(true) {
				// str1 혹은 str2 의 문자열 길이 끝까지 도달했을 경우,
				// 같은 위치에서 str1 문자와 str2 문자가 서로 다를 경우, 중단.
				if(idx >= str1.length() || idx >= str2.length() || str1.charAt(idx) != str2.charAt(idx)) {
					LCP[i] = cnt;
					break;
				} else {
					cnt++;
					idx++;
				}
			}
		}
	}
	
	// 접미어 배열을 순회하며 부분 문자열 생성 => 중복되지 않는 부분 문자열 저장
	static void getSubString() {
		for(int i=0; i<suffixArr.size(); i++) {
			String s= "";
			for(int j=0; j<suffixArr.get(i).length(); j++) {
				s += suffixArr.get(i).charAt(j);
				if(j>=LCP[i])
					subString.add(s);
			}
		}
	}
}