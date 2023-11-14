package com.seed.redis;

import com.alibaba.fastjson.JSONObject;
import com.seed.db.mongo.Article;

public class RedisUtil {
    public static void main(String[] args) {
        Article article = new Article(){{setArticleName("aaa");setContent("111");setId("1");}};

        String newProjectJson= JSONObject.toJSONString(article);
        System.out.println(newProjectJson);
    }
}
