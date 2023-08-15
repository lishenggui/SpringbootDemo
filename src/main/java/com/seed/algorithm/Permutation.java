package com.seed.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 全排列
 */
public class Permutation {
    public static void main(String[] args) {
//        String str = "abc";
//        char[] c = str.toCharArray();
//        perm(c,3);

        int[]aa={-1,0,1,6};
        System.out.println(threeSum(aa));
    }

    public static ArrayList<ArrayList<Integer>> threeSum (int[] num) {

        Arrays.sort(num);
        int len=num.length-1;
        ArrayList<ArrayList<Integer>>result = new ArrayList();
        if(len<3){
            return result;
        }
        for(int i=0;i<len-2;i++){
            int start=i+1,end=num.length-1;
            while(start<end){
                if(num[i]+num[start]+num[end]==0){
                    ArrayList<Integer>list = new ArrayList();
                    list.add(num[start]);
                    list.add(num[i]);
                    list.add(num[end]);
                    result.add(list);
                    start++;
                    end--;
                }else if(num[i]+num[start]+num[end]<0){
                    start++;
                }else if(num[i]+num[start]+num[end]>0){
                    end--;
                }
            }
        }
        return result;
    }

    private static void perm(char[] c,int n) {
        if(n==1){
            System.out.println(c);
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(c,i,n-1);
            perm(c,n-1);
            swap(c,i,n-1);
        }
    }

    private static void swap(char[] c, int i, int j) {
        char temp = c[j];
        c[j]=c[i];
        c[i]=temp;
    }
}
