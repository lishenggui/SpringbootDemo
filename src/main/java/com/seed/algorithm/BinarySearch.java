package com.seed.algorithm;

/**
 * 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        int []arr = {-1,0,1,3,5,6,8};
        int result = search1(arr,5,0,6);
        System.out.println(result);
        int result2 = search2(arr,5,0,6);
        System.out.println(result2);
    }

    /**
     * 递归
     * @param arr
     * @param k
     * @param start
     * @param end
     * @return
     */
    private static int search1(int[] arr, int k,int start,int end) {
        if(start<=end){
            int mid = (start+end)/2;
            if(arr[mid]>k){
                return  search1(arr,k,start,mid-1);
            }else if(arr[mid]<k){
                return  search1(arr,k,mid+1,end);
            }else {
                return mid;
            }
        }
        return  -1;
    }

    /**
     *非递归
     * @param arr
     * @param k
     * @param start
     * @param end
     * @return
     */
    private static int search2(int[] arr, int k,int start,int end) {
        while(start<=end){
            int mid = (start+end)/2;
            if(arr[mid]>k){
                end=mid-1;
            }else if(arr[mid]<k){
                start=mid+1;
            }else {
                return mid;
            }
        }
        return  -1;
    }
}
