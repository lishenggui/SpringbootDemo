package com.seed.algorithm;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int []arr = {-1,0,2,3,5,1,4};
        sort(arr,0,arr.length-1);
        Arrays.stream(arr).forEach(value -> System.out.println(value));
        System.out.println(arr.toString());

    }

    private static void sort(int[] arr,int start,int end) {
        if(start>=end){
            return;
        }
        int i=start,j=end;
        int pivot=arr[start];
        while (i<j){
            while(arr[j]>=pivot&&i<j){
                j--;
            }
            while(arr[i]<=pivot&&i<j){
                i++;
            }

            if(i<j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[start] = arr[i];
        arr[i] = pivot;
        sort(arr,start,i-1);
        sort(arr,i+1,end);
    }
}
