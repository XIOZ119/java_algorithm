// 포화 이진트리 개수 : 1, 3, 7, 15 ... : 2^k-1
// 루트가 0 -> 자식들도 0 이어야함
// 개수가 안 맞으면 앞에 0을 붙여서 길이를 맞춤 (단순 패딩 처리)

// 10진수 -> 2진수 : Integer.toBinaryString

// 1. 2진수로 변환
// 2. 글자수가 포화 이진트리 형식에 맞는 지 비교 
// 3. 다르다면 0을 개수만큼 앞에 붙여주기 
// 4. 글자수를 맞췄다면, 중앙을 찾아서 이진 ..? 비교 ..? 해주기
// 5. 루트가 0일 때는 자식도 0이어야함을 확인하기

class Solution {
    public int[] solution(long[] numbers) {
        int size = numbers.length;
        int[] answer = new int[size];
    
        for(int i=0; i<size; i++){
            long number = numbers[i];
            
            // 1. 10진수 -> 2진수 변환
            String binaryNumber = Long.toBinaryString(number);
            
            // 2. 포화 이진트리 수로 맞추기
            int curLength = binaryNumber.length();
            int findLength = 1;
            while(findLength < curLength){
                findLength = findLength * 2 + 1;
            }
            
            int padding = findLength - curLength;
            String paddingNumber = "0".repeat(padding) + binaryNumber;
            
            // 3. 루트가 0, 1 에 따라 자식 비교
            answer[i] = (isValid(paddingNumber)) ? 1 : 0;
        }
        
        return answer;
    }
    
    private boolean isValid(String tree){
        if(tree.length() == 1) return true;
        
        int mid = tree.length() / 2;
        char root = tree.charAt(mid);
        
        String left = tree.substring(0, mid);
        String right = tree.substring(mid + 1);
        
        if(root == '0'){
            if(hasOne(left) || hasOne(right)) 
                return false;
        }
        
        return isValid(left) && isValid(right);
    }
    
    private boolean hasOne(String s){
        return s.contains("1");
    }
}

// 1 0 1 
