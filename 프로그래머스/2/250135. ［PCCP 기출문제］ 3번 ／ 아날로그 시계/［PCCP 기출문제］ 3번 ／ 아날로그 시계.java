import java.util.*;

/*
second = (720 * t) % 43200;
minute = (12 * t) % 43200;
hour   = t % 43200;

t = 시작초 ~ 끝초-1
    t초의 세 바늘 위치 계산
    t+1초의 세 바늘 위치 계산

    초침-분침이 사이에서 만났는지 확인
    초침-시침이 사이에서 만났는지 확인
*/

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        
        int startTime = h1 * 3600 + m1 * 60 + s1;
        int endTime = h2 * 3600 + m2 * 60 + s2;
        
        long startSec = (startTime * 720L) % 43200;
        long startMin = (startTime * 12L) % 43200;
        long startH = startTime % 43200;
        
        if(startSec == startMin || startSec == startH) {
            answer++;
        }
        
        for(int i=startTime; i<endTime; i++) {
            long sec = (i * 720) % 43200;
            long min = (12 * i) % 43200;
            long h = i % 43200;
            
            long nextSec = ((i+1) * 720) % 43200;
            long nextMin = (12 * (i+1)) % 43200;
            long nextH = (i+1) % 43200;
            
            if(nextSec == 0) nextSec = 43200;
            if(nextMin == 0) nextMin = 43200;
            if(nextH == 0) nextH = 43200;
            
            boolean sm = sec < min && nextSec >= nextMin;
            boolean sh = sec < h && nextSec >= nextH;

            if(sm) answer++;
            if(sh) answer++;

            if(sm && sh && nextSec == nextMin && nextSec == nextH) {
                answer--;
            }
        }
        
        return answer;
    }
}