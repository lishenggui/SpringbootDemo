package com.seed.algorithm;

import java.util.Arrays;

public class BubboSort {
    public static void main(String[] args) {
        int []arr = {-1,0,2,3,5,1,4};
        sort(arr);
        Arrays.stream(arr).forEach(value -> System.out.println(value));
        System.out.println(arr.toString());

    }

    private static void sort(int[] arr) {
        for (int i = arr.length-1; i >=0 ; i--) {
            boolean flag = true;
            for (int j = i-1; j >=0 ; j--) {
                    if(arr[i]<arr[j]){
                        flag = false;
                        int temp = arr[i];
                        arr[i]=arr[j];
                        arr[j]=temp;
                    }
            }
            if(flag){
                break;
            }

        }


    }
}
