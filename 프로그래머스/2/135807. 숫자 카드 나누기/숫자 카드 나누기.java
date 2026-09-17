class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        int a = start(arrayA);
        int b = start(arrayB);
        
        if(a != 1 && isValid(arrayB, a)) answer = a;
        if(b != 1 && isValid(arrayA, b)) answer = Math.max(answer, b);
        
        return answer;
    }
    
    private static boolean isValid(int[] arr, int num) {
        for(int i=0; i<arr.length; i++) {
            if(arr[i] % num == 0) return false;
        }
        
        return true;
    }
    
    private static int start(int[] array) {
        int a = array[0];
        
        for(int i=1; i<array.length; i++) {
            a = gcd(array[i], a);
        }
        
        return a;
    }
    
    private static int gcd(int a, int b) {
        while(b != 0) {
            int max = Math.max(a, b);
            int min = Math.min(a, b);
            
            a = min;
            b = max % min;
        }
        
        return a;
    }
}