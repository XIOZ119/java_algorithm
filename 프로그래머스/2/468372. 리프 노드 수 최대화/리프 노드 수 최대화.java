class Solution {

    public int solution(int dist_limit, int split_limit) {
        long answer = 1;
        long powerOfTwo = 1; // 2분배 층의 개수 (powerOfTwo = 2^twoDepth)

        for (int twoDepth = 0; powerOfTwo <= split_limit; twoDepth++) {
            long powerOfThree = 1; // 3분배 층의 개수 (powerOfThree = 3^threeDepth)

            for (int threeDepth = 0; powerOfTwo * powerOfThree <= split_limit; threeDepth++) {
                long currentSplit = powerOfTwo * powerOfThree;

                // 완성된 2분배 층 + 3분배 층만 사용하는 경우
                answer = Math.max(answer, calculate(twoDepth, threeDepth, 0, dist_limit));

                // 마지막에 2분배 층을 일부 추가할 수 있는 경우
                if (currentSplit <= split_limit / 2) 
                    answer = Math.max(answer, calculate(twoDepth, threeDepth, 2, dist_limit));

                // 마지막에 3분배 층을 일부 추가할 수 있는 경우
                if (currentSplit <= split_limit / 3) 
                    answer = Math.max(answer, calculate(twoDepth, threeDepth, 3, dist_limit));

                if (powerOfThree > split_limit / 3) break;

                powerOfThree *= 3;
            }

            if (powerOfTwo > split_limit / 2) break;

            powerOfTwo *= 2;
        }

        return (int) answer;
    }

    private long calculate(int twoDepth, int threeDepth, int extraBranch, long distLimit) {
        long nodeCount = 1; // 현재 깊이에 존재하는 노드 수

        /*
         * 2분배 층을 완성한다.
         *
         * 현재 nodeCount개의 노드를 분배 노드로 사용하면
         * 다음 층의 노드는 nodeCount * 2개가 된다.
         */
        for (int i = 0; i < twoDepth; i++) {
            if (distLimit < nodeCount) return 0; 

            distLimit -= nodeCount;
            nodeCount *= 2;
        }

        /*
         * 3분배 층을 완성한다.
         *
         * 현재 nodeCount개의 노드를 분배 노드로 사용하면
         * 다음 층의 노드는 nodeCount * 3개가 된다.
         */
        for (int i = 0; i < threeDepth; i++) {
            if (distLimit < nodeCount) return 0;

            distLimit -= nodeCount;
            nodeCount *= 3;
        }

        // 현재 완성된 트리의 리프 노드 수
        long leafCount = nodeCount;

        /*
         * 마지막 층은 전부 분배하지 않고
         * 남은 dist_limit만큼 일부 노드만 분배한다.
         */
        if (extraBranch != 0 && distLimit > 0) {
            long expandableCount = Math.min(nodeCount, distLimit);

            /*
             * 2분배: 리프 1개 증가
             * 3분배: 리프 2개 증가
             */
            leafCount += expandableCount * (extraBranch - 1);
        }

        return leafCount;
    }
}