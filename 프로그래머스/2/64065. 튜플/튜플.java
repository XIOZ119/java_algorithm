import java.util.*;

class Solution {
    public int[] solution(String s) {
        String ss = s.substring(2, s.length() - 2);
        String[] arr = ss.split("\\},\\{");
        // ["2", "2,1", "2,1,3", "2,1,3,4"]
        Arrays.sort(arr, (a,b) -> Integer.compare(a.length(), b.length()));
        
        int l = arr.length;
        int[] answer = new int[l];
        HashSet<String> set = new HashSet<>();
        for(int i=0; i<arr.length; i++) {
            String str = arr[i];
            String[] strArr = arr[i].split(",");
            for(int j=0; j<strArr.length; j++) {
                if(set.contains(strArr[j])) continue;
                set.add(strArr[j]);
                answer[i] = Integer.parseInt(strArr[j]);
            }
        }
        
        return answer;
    }
}