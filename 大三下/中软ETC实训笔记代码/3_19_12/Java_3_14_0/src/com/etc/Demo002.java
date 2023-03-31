package com.etc;

import net.sf.json.JSONArray;

public class Demo002 {

    public static void main(String[] args) {
        //JSONArray的创建
        JSONArray json = new JSONArray();
        json.add("烤鸡爪");
        json.add("烤地瓜");
        json.add("烤羊腰子");
        json.add(2,"烤土豆");

        System.out.println(json);

        //JSONArray解析
        String food = json.getString(0);
        String food1 = json.getString(1);
        String food2 = json.getString(2);
        String food3 = json.getString(3);

        System.out.println(food);
        System.out.println(food1);
        System.out.println(food2);
        System.out.println(food3);
    }
}
