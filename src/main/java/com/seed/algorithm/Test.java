package com.seed.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String str="12\"report/1.jpg\",3\"report/2.png\"";
        List<String>list = new ArrayList<>();
        getAllImages(str,list);


    }

    public static void  getAllImages(String detail, List<String> imagePathList) {
        int index=0;
        while(index<detail.length()){
            int start= detail.indexOf("report/",index);
            int end = detail.indexOf("\"" ,start);
            String str1 = detail.substring(start,end);
            System.out.println(str1);
            index = end;
        }
    }

}
