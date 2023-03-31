package com.etc;

import net.sf.json.JSONObject;

public class Demo001 {

    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("id",1);
        json.put("name","商品001");
        json.put("price",100);
        json.put("slogan","口号001");

        System.out.println(json);
    }
}
