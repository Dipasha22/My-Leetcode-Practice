class Solution {
    static final long MOD = 1_000_000_007L;
    static final long INV2 = 500000004L; // modular inverse of 2 mod (1e9+7)

    public int minimumCost(int[] nums, int k) {
        long curr = k;
        long ops = 0;   // total operations performed so far (can be large, up to ~1e14)
        long ans = 0;   // running total cost, kept mod MOD

        for (int x : nums) {
            if (curr < x) {
                long diff = x - curr;
                long need = (diff + k - 1) / k; // operations needed right now (ceil division)

                // cost of these operations = sum_{i=ops+1}^{ops+need} i = need*(2*ops+need+1)/2
                // compute mod MOD using modular inverse of 2 to avoid overflow,
                // since need and ops can individually be as large as ~1e14
                long needMod = need % MOD;
                long sumTermMod = ((2 * (ops % MOD)) % MOD + need % MOD + 1) % MOD;
                long costMod = (needMod * sumTermMod) % MOD;
                costMod = (costMod * INV2) % MOD;

                ans = (ans + costMod) % MOD;

                ops += need;
                curr += need * (long) k;
            }
            curr -= x;
        }

        return (int) (ans % MOD);
    }
}