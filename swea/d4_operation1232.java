package swea;

import java.io.*;

public class d4_operation1232 {
	
	static Node[] arr;
	
	static class Node {
		String node;
		Node left; 
		Node right;
		
		public Node(String node) {
			this.node = node;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int testCase = 1; testCase <= 10; testCase++) {
			int N = Integer.parseInt(br.readLine());
			arr = new Node[N+1];
			
			// 모든 노드 미리 생성
			for(int i=1; i<N+1; i++) {
				arr[i] = new Node(" ");
			}
			
			for(int i=1; i<N+1; i++) {
				String[] str = br.readLine().split(" ");
				arr[i].node = str[1];
				
				if(str.length > 2) {
					arr[i].left = arr[Integer.parseInt(str[2])];					
				}
				if(str.length > 3) {					
					arr[i].right = arr[Integer.parseInt(str[3])];
				}
			}
			
			System.out.println("#" + testCase + " " + evaluate(arr[1]));
			
		}
	}
	
	private static int evaluate(Node node) {
		if(node.left == null && node.right == null) {
			return Integer.parseInt(node.node); // 숫자로 변환
		}
		
		int leftValue = evaluate(node.left);
		int rightValue = evaluate(node.right);
		
		if(node.node.equals("+")) return leftValue + rightValue;
		else if(node.node.equals("-")) return leftValue - rightValue;
		else if(node.node.equals("*")) return leftValue * rightValue;
		else if(node.node.equals("/")) return leftValue / rightValue;
		
		return 0;
	}

}
