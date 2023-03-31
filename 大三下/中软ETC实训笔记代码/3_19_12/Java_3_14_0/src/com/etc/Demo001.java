package com.etc;

import net.sf.json.JSONObject;

public class Demo001 {

    public static void main(String[] args) {
        //创建JSONObject
        JSONObject json = new JSONObject();
        json.put("id",1);
        json.put("name","商品001");
        json.put("price",100);
        json.put("slogan","口号001");

        System.out.println(json);

        //JSONObject解析
        int id = json.getInt("id");
        String name = json.getString("name");
        int price = json.getInt("price");
        String slogan = json.getString("slogan");

        System.out.println(id);
        System.out.println(name);
        System.out.println(price);
        System.out.println(slogan);
    }
}
