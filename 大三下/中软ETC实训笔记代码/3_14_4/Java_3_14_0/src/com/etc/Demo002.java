package com.etc;

import net.sf.json.JSONArray;

public class Demo002 {

    public static void main(String[] args) {
        JSONArray json = new JSONArray();
        json.add("烤鸡爪");
        json.add("烤地瓜");
        json.add("烤羊腰子");
        json.add(2,"烤土豆");

        System.out.println(json);
    }
}
