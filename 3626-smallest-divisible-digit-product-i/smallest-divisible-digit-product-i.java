class Solution {
    int findDigitsProd(int num) {
        int prod = 1;

        while (num > 0) {
            prod = prod * (num % 10);

            if (prod == 0)
                return 0;

            num /= 10;
        }

        return prod;
    }

    public int smallestNumber(int n, int t) {
        for (int num = n; ; num++) {
            if (findDigitsProd(num) % t == 0) {
                return num;
            }
        }
    }
}