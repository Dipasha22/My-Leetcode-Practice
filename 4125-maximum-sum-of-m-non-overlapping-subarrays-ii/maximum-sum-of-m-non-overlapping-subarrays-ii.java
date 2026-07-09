import java.util.*;

class Solution {
    int n, l, r;
    long[] prefix;
    static final long NEG = Long.MIN_VALUE / 2;

    public long maximumSum(int[] nums, int m, int l, int r) {
        n = nums.length; this.l = l; this.r = r;
        prefix = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + nums[i];

        long lo = 0, hi = 200000L * n + 10;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            long[] res = compute(mid);
            if (res[1] <= m) hi = mid; else lo = mid + 1;
        }
        long[] finalRes = compute(lo);
        return finalRes[0] + lo * m;
    }

    // returns {bestValue, count} where bestValue = maxSum - lambda*count, using >=1 subarray
    private long[] compute(long lambda) {
        long[] dp1Val = new long[n + 1];
        int[] dp1Cnt = new int[n + 1];
        long[] key = new long[n + 1];
        int[] cnt = new int[n + 1];

        dp1Val[0] = NEG;
        key[0] = -prefix[0];
        cnt[0] = 0;

        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            int newK = i - l;
            if (newK >= 0) {
                while (!dq.isEmpty()) {
                    int back = dq.peekLast();
                    if (key[back] < key[newK] || (key[back] == key[newK] && cnt[back] >= cnt[newK])) {
                        dq.pollLast();
                    } else break;
                }
                dq.addLast(newK);
            }
            int minK = i - r;
            while (!dq.isEmpty() && dq.peekFirst() < minK) dq.pollFirst();

            long best = dp1Val[i - 1];
            int bestCnt = (best == NEG) ? Integer.MAX_VALUE : dp1Cnt[i - 1];

            if (!dq.isEmpty()) {
                int k = dq.peekFirst();
                long cand = key[k] + prefix[i] - lambda;
                int candCnt = cnt[k] + 1;
                if (cand > best || (cand == best && candCnt < bestCnt)) {
                    best = cand; bestCnt = candCnt;
                }
            }
            dp1Val[i] = best;
            dp1Cnt[i] = (best == NEG) ? 0 : bestCnt;

            if (dp1Val[i] > 0) { key[i] = dp1Val[i] - prefix[i]; cnt[i] = dp1Cnt[i]; }
            else { key[i] = -prefix[i]; cnt[i] = 0; }
        }

        return new long[]{dp1Val[n], dp1Cnt[n]};
    }
}