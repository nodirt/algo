package algo.dynamic;

public class LongestCommonSubsequence {
    
    public <E> int[] find(final E[] a, final E[] b) {
        int[][] len = new int[a.length + 1][b.length + 1];
        
        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < b.length; j++) {
                if (a[i] == b[j]) {
                    len[i][j] = len[i - 1][j - 1] + 1;
                } else {
                    len[i][j] = Math.max(len[i - 1][j], len[i][j - 1]);
                }
            }
        }
        
        int lcsLength = len[a.length - 1][b.length - 1];
        int[] solution = new int[lcsLength];
        // build solution
        {
            int i = a.length;
            int j = b.length;
            int k = lcsLength - 1;
            while (k >= 0) {
                if (len[i][j] == len[i - 1][j]) {
                    i--;
                } else if (len[i][j] == len[i][j - 1]) {
                    j--;
                } else {
                    // match found
                    solution[k] = i;
                    k--;
                    i--;
                    j--;
                }
                assert i >= 0 && j >= 0;
            }
        }
        return solution;
    }
    
}
