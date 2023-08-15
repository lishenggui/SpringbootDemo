package com.seed.algorithm;

import java.util.Arrays;

/**
 * 获取最长递增子序列
 */
public class LengthOfLIS {
    public static void main(String[] args) {
        int []arr = {9,1,3,4,1,5,7,8};
        System.out.println(solution(arr) );
    }

    public static   int solution(int []nums){
        int size = nums.length;
        int []dp = new int[size];
        Arrays.fill(dp,1);
        for (int i = 1; i < size; i++) {
            int max=Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                if(nums[j]<nums[i]){
                    max = Math.max(max,dp[j]);
                }
            }
            if(max>Integer.MIN_VALUE){
                dp[i] = max+1;
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
