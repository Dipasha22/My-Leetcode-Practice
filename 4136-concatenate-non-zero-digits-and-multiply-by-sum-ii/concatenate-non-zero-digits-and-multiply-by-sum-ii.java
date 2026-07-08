class Solution {
       static final int MOD = 1_000_000_007;
    public int[] sumAndMultiply(String s, int[][] queries) {
         int n = s.length();

        ArrayList<Integer> posList = new ArrayList<>();
        ArrayList<Integer> digitList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                posList.add(i);
                digitList.add(d);
            }
        }

        int m = digitList.size();

        int[] pos = new int[m];
        int[] digits = new int[m];

        for (int i = 0; i < m; i++) {
            pos[i] = posList.get(i);
            digits[i] = digitList.get(i);
        }

        long[] pow10 = new long[m + 1];
        pow10[0] = 1;
        for (int i = 1; i <= m; i++)
            pow10[i] = (pow10[i - 1] * 10) % MOD;

        long[] pref = new long[m + 1];
        int[] prefSum = new int[m + 1];

        for (int i = 0; i < m; i++) {
            pref[i + 1] = (pref[i] * 10 + digits[i]) % MOD;
            prefSum[i + 1] = prefSum[i] + digits[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;

            if (left > right) {
                ans[i] = 0;
                continue;
            }

            int len = right - left + 1;

            long x = (pref[right + 1]
                    - pref[left] * pow10[len]) % MOD;

            if (x < 0) x += MOD;

            long sum = prefSum[right + 1] - prefSum[left];

            ans[i] = (int) ((x * sum) % MOD);
        }

        return ans;
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] > target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}