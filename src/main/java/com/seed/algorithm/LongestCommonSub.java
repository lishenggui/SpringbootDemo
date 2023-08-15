package com.seed.algorithm;

/**
 * 最长公共子序列
 //          a b c b a
 //        b 0 1 1 1 1
 //        c 0 1 2 2 2
 //        a 1 1 2 2 3

 */
public class LongestCommonSub {

    public static void main(String[] args) {
        String a = "abc3d5";
        String b = "a1bcd653";
        System.out.println(solution(a,b) );
    }

    public static   int solution(String a,String b){
        int la = a.length();
        int lb = b.length();
        int [][]dp = new int[la+1][lb+1];
        int max=0;
        for (int i = 1; i < la+1; i++) {
            for (int j = 1; j < lb+1; j++) {
                if(a.charAt(i-1)==b.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
                max = dp[i][j];
            }
        }
        return max;
    }
}
