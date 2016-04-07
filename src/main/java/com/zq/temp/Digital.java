package com.zq.temp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Money Zheng on 2016/3/23.
 */
public class Digital {


    public static void main(String[] args) {

        long a =123861232351353278l;
        System.out.println(isSame(a));
    }
    public static int isSame(long digital){

        String str = String.valueOf(digital);
        Map<String,Integer> hashmap = new HashMap<String, Integer>();

        for (int i=0;i<str.length();i++){
            for (int j = i; j < str.length(); j++) {
                String temp = str.substring(i,j);
                if (temp.length() <2 || temp.length()>(str.length()/2)+1){
                    continue;
                }
                if (hashmap.get(temp)!= null){
                    return 1;
                }
                hashmap.put(temp,str.indexOf(temp));
            }
        }

        for(Map.Entry<String,Integer> entry : hashmap.entrySet()){
            System.out.println(entry.getKey() +"  "+ entry.getValue());
        }

        return 0;
    }



}
