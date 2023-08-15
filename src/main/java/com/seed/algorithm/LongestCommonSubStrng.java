package com.seed.algorithm;

/**
 * 最长公共子串
 //          a b c b a
 //        b 0 1 1 1 1
 //        c 0 1 2 2 2
 //        f 1 1 2 2 2
 */
public class LongestCommonSubStrng {

    public static void main(String[] args) {
        String a = "1AB2345CD";
        String b = "12345EF";
        System.out.println(solution(a,b));
    }

    public static  String  solution(String a,String b){
        int la = a.length();
        int lb = b.length();
        int [][]dp = new int[la+1][lb+1];
        int max=0;
        int index = 0;
        for (int i = 1; i <=  la; i++) {
            for (int j = 1; j <= lb; j++) {
                if(a.charAt(i-1)==b.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1]+1;
                        if(max<dp[i][j]){
                            max = dp[i][j];
                            index = i;
                        }
                }

            }
        }
        return a.substring(index-max,index);
    }
}
